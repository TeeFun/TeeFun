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
		}
	}

	@Override
	public void removeQueue(final Queue queue) {
		if (this.availableQueues.contains(queue)) {
			this.availableQueues.remove(queue);
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
	private void checkQueue(final Queue queue) {
		switch (queue.getQueueState()) {
		case SUSPENDED:
			return;
		case IN_GAME:
			return;
		case WAITING_PLAYERS:
			if (queue.isFull()) {
				LOGGER.debug(String.format("Queue '%s' ready. Starting the game.", queue.getName()));
				queue.setQueueState(QueueState.WAITING_SERVER);
			}
			return;
		case WAITING_READY:
			// TODO check if all ready or ready timed out
			// TODO if all ready
			this.teeworldsServerHandler.startServer(queue.getServer());
			queue.setQueueState(QueueState.IN_GAME);
			queue.getServer().getConfig().getPassword();
			// TODO send serverConfig.getPassword() to all players.

			// TODO if timedout
			// this.teeworldsServerHandler.freeServer(queue.getServer());
			// queue.setQueueState(QueueState.GAME_OVER);
			return;
		case WAITING_SERVER:
			if (this.teeworldsServerHandler.hasServerAvailable()) {
				final TeeworldsServer server = this.teeworldsServerHandler.createAndBorrowServer(queue.makeConfig());
				queue.setServer(server);
				queue.setQueueState(QueueState.WAITING_READY);
			}
			return;
		case GAME_OVER:
			if (queue.isPermanent()) {
				// TODO reset
			} else {
				this.removeQueue(queue);
			}
		}
	}
}
