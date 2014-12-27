package com.teefun.bean.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.teefun.bean.Matchmaking;
import com.teefun.model.Player;
import com.teefun.model.Queue;
import com.teefun.model.QueueState;
import com.teefun.model.teeworlds.TeeworldsServer;
import com.teefun.service.teeworlds.TeeworldsServerHandler;
import com.teefun.service.websocket.WebSocketHandler;

/**
 * Default impl for {@link Matchmaking}.
 *
 * @author Rajh
 */
@Component
public class MatchmakingImpl implements Matchmaking {

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MatchmakingImpl.class);

	/**
	 * Teeworlds server handler.
	 */
	@Resource
	private TeeworldsServerHandler teeworldsServerHandler;

	/**
	 * Websocket handler.
	 */
	@Resource
	private WebSocketHandler websocketHandler;

	/**
	 * List of available queues.
	 */
	private final List<Queue> availableQueues = new CopyOnWriteArrayList<Queue>();

	@Override
	public List<Queue> getQueues() {
		return this.availableQueues;
	}

	@Override
	public List<Queue> getQueues(final Player player) {
		final List<Queue> queues = new ArrayList<Queue>();
		for (final Queue queue : this.availableQueues) {
			if (queue.containsPlayer(player)) {
				queues.add(queue);
			}
		}
		return queues;
	}

	@Override
	public void joinQueue(final Player player, final Queue queue) {
		LOGGER.debug(String.format("Add player '%s' to queue '%s'.", player.getName(), queue.getName()));
		queue.addPlayer(player);
		this.checkQueue(queue);
	}

	@Override
	public void quitQueue(final Player player, final Queue queue) {
		if (QueueState.WAITING_PLAYERS == queue.getQueueState()) {
			LOGGER.debug(String.format("Remove player '%s' from queue '%s'.", player.getName(), queue.getName()));
			queue.removePlayer(player);
		}
		this.checkQueue(queue);
	}

	@Override
	public void quitAllQueues(final Player player) {
		LOGGER.debug(String.format("Remove player '%s' from all queues.", player.getName()));
		for (final Queue queue : this.availableQueues) {
			queue.removePlayer(player);
			this.checkQueue(queue);
		}
	}

	@Override
	public Queue getQueueByName(final String name) {
		for (final Queue queue : this.availableQueues) {
			if (name.equals(queue.getName())) {
				return queue;
			}
		}
		return null;
	}

	@Override
	public void addQueue(final Queue queue) {
		if (!this.availableQueues.contains(queue)) {
			this.availableQueues.add(queue);
			this.websocketHandler.queueCreated(queue);
		}
	}

	@Override
	public void removeQueue(final Queue queue) {
		if (this.availableQueues.contains(queue)) {
			this.availableQueues.remove(queue);
			this.websocketHandler.queueDeleted(queue);
		}
	}

	@Override
	public boolean isInQueue(final Player player) {
		for (final Queue queue : this.availableQueues) {
			if (queue.containsPlayer(player)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check queue status.
	 *
	 * @param queue the queue
	 */
	@Override
	public void checkQueue(final Queue queue) {
		switch (queue.getQueueState()) {
		case SUSPENDED:
			break;
		case IN_GAME:
			break;
		case WAITING_PLAYERS:
			if (queue.isFull()) {
				queue.setQueueState(QueueState.WAITING_SERVER);
				LOGGER.debug(String.format("Queue '%s' is waiting for a server'.", queue.getName()));
				// FIXME factor this method. How to autocheck queue ? Task ? Event ?
				if (this.teeworldsServerHandler.hasServerAvailable()) {
					LOGGER.debug(String.format("Queue '%s' has found a server.", queue.getName()));
					final TeeworldsServer server = this.teeworldsServerHandler.createAndBorrowServer(queue.makeConfig());
					queue.setServer(server);
					queue.setQueueState(QueueState.WAITING_READY);
					queue.startTimer();
					this.websocketHandler.gameReady(queue);
					LOGGER.debug(String.format("Queue '%s' has borrowed a server.", queue.getName()));
				}
			}
			break;
		case WAITING_READY:
			// TODO check if all ready or ready timed out
			// TODO if all ready
			if (queue.isEveryoneReady()) {
				this.teeworldsServerHandler.startServer(queue.getServer());
				queue.setQueueState(QueueState.IN_GAME);
				queue.getServer().getConfig().getPassword();
				this.websocketHandler.gameStarted(queue);
				LOGGER.debug(String.format("Queue '%s' has started its game.", queue.getName()));
			}

			// TODO if timedout
			if (queue.hasEveryResponse() && !queue.isEveryoneReady() || queue.hasTimedOut()) {
				this.teeworldsServerHandler.freeServer(queue.getServer());
				queue.setQueueState(QueueState.WAITING_PLAYERS);
				this.websocketHandler.gameAborted(queue);
				queue.setServer(null);
				queue.removeLeavers();
				LOGGER.debug(String.format("Queue '%s' has terminated. Players were not ready.", queue.getName()));
			}
			break;
		case WAITING_SERVER:
			// FIXME Thread-Safe ?
			if (this.teeworldsServerHandler.hasServerAvailable()) {
				LOGGER.debug(String.format("Queue '%s' has found a server.", queue.getName()));
				final TeeworldsServer server = this.teeworldsServerHandler.createAndBorrowServer(queue.makeConfig());
				queue.setServer(server);
				queue.setQueueState(QueueState.WAITING_READY);
				this.websocketHandler.gameReady(queue);
				LOGGER.debug(String.format("Queue '%s' has borrowed a server.", queue.getName()));
			}
			break;
		case GAME_OVER:
			if (queue.isPermanent()) {
				LOGGER.debug(String.format("Queue '%s' has terminated and has been reset.", queue.getName()));
				queue.reset();
			} else {
				LOGGER.debug(String.format("Queue '%s' has terminated.", queue.getName()));
				this.removeQueue(queue);
			}
			break;
		}
		this.websocketHandler.queueUpdated(queue);
	}

	@Override
	public void onServerFree(final TeeworldsServer server) {
		// TODO use an event system -_-
		for (final Queue queue : this.availableQueues) {
			if (queue.getServer() == server) {
				queue.setQueueState(QueueState.GAME_OVER);
			}
			this.checkQueue(queue);
		}
	}
}
