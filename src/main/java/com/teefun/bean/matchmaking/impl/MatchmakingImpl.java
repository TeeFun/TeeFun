package com.teefun.bean.matchmaking.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.teefun.bean.matchmaking.Matchmaking;
import com.teefun.events.event.GameAbortedEvent;
import com.teefun.events.event.GameReadyEvent;
import com.teefun.events.event.GameStartedEvent;
import com.teefun.events.event.PlayerModifiedEvent;
import com.teefun.events.event.PlayerReadyEvent;
import com.teefun.events.event.QueueCreatedEvent;
import com.teefun.events.event.QueueDeletedEvent;
import com.teefun.events.event.QueueModifiedEvent;
import com.teefun.events.event.QueueReadyTimedOutEvent;
import com.teefun.events.event.ServerFreeEvent;
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
	 * Time in seconds before the ready request timeout.
	 */
	@Value("${queue.ready.ttl}")
	private Long READY_TIMEOUT;

	/**
	 * Teeworlds server handler.
	 */
	@Resource
	private TeeworldsServerHandler teeworldsServerHandler;

	/**
	 * Event bus.
	 */
	@Resource
	private EventBus eventBus;

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
		this.eventBus.post(new QueueModifiedEvent(queue));
	}

	@Override
	public void quitQueue(final Player player, final Queue queue) {
		if (QueueState.WAITING_PLAYERS == queue.getState()) {
			LOGGER.debug(String.format("Remove player '%s' from queue '%s'.", player.getName(), queue.getName()));
			queue.removePlayer(player);
		}
		this.eventBus.post(new QueueModifiedEvent(queue));
	}

	@Override
	public void quitAllQueues(final Player player) {
		LOGGER.debug(String.format("Remove player '%s' from all queues.", player.getName()));
		for (final Queue queue : this.availableQueues) {
			queue.removePlayer(player);
			this.eventBus.post(new QueueModifiedEvent(queue));
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
	public Queue getQueueById(final Integer id) {
		for (final Queue queue : this.availableQueues) {
			if (id == queue.getId()) {
				return queue;
			}
		}
		return null;
	}

	@Override
	public void addQueue(final Queue queue) {
		if (!this.availableQueues.contains(queue)) {
			this.availableQueues.add(queue);
			this.eventBus.post(new QueueCreatedEvent(queue));
		}
	}

	@Override
	public void removeQueue(final Queue queue) {
		if (this.availableQueues.contains(queue)) {
			this.availableQueues.remove(queue);
			this.eventBus.post(new QueueDeletedEvent(queue));
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
	 * Listen queue modified event.
	 *
	 * @param queueModifiedEvent the event
	 */
	@Subscribe
	public void onQueueModified(final QueueModifiedEvent queueModifiedEvent) {
		final Queue queue = queueModifiedEvent.getQueue();
		boolean queueModified = false;
		switch (queue.getState()) {
		case SUSPENDED:
			break;
		case IN_GAME:
			break;
		case WAITING_PLAYERS:
			if (queue.isFull()) {
				LOGGER.debug(String.format("Queue '%s' is waiting for a server'.", queue.getName()));
				queue.setState(QueueState.WAITING_SERVER);
				queueModified = true;
			}
			break;
		case WAITING_READY:
			if (queue.isEveryoneReady()) {
				this.teeworldsServerHandler.startServer(queue.getServer());
				queue.setState(QueueState.IN_GAME);
				LOGGER.debug(String.format("Queue '%s' has started its game.", queue.getName()));
				this.eventBus.post(new GameStartedEvent(queue));
				queueModified = true;
			}

			if (queue.hasEveryResponse() && !queue.isEveryoneReady()) {
				this.cancelQueueReady(queue);
				LOGGER.debug(String.format("Queue '%s' has terminated. At least one player was not ready.", queue.getName()));
				this.eventBus.post(new GameAbortedEvent(queue));
				queueModified = true;
			}
			break;
		case WAITING_SERVER:
			// FIXME Thread-Safe ?
			if (this.teeworldsServerHandler.hasServerAvailable()) {
				LOGGER.debug(String.format("Queue '%s' has found a server.", queue.getName()));
				final TeeworldsServer server = this.teeworldsServerHandler.createAndBorrowServer(queue.makeConfig());
				queue.setServer(server);
				queue.setState(QueueState.WAITING_READY);
				this.startReadyTimer(queue);
				LOGGER.debug(String.format("Queue '%s' has borrowed a server.", queue.getName()));
				this.eventBus.post(new GameReadyEvent(queue));
				queueModified = true;
			}
			break;
		case GAME_OVER:
			if (queue.isPermanent()) {
				LOGGER.debug(String.format("Queue '%s' has terminated and has been reset.", queue.getName()));
				queue.reset();
				queueModified = true;
			} else {
				LOGGER.debug(String.format("Queue '%s' has terminated.", queue.getName()));
				this.removeQueue(queue);
			}
			break;
		}
		if (queueModified) {
			// Care to loop here. We are firing an event inside the same event
			this.eventBus.post(new QueueModifiedEvent(queue));
		}
	}

	/**
	 * Cancel queue ready.
	 *
	 * @param queue the queue
	 */
	private void cancelQueueReady(final Queue queue) {
		this.teeworldsServerHandler.freeServer(queue.getServer());
		queue.setServer(null);
		queue.setState(QueueState.WAITING_PLAYERS);
		queue.removeLeavers();
	}

	/**
	 * When queue ready has timed out.
	 *
	 * @param queueReadyTimedOutEvent the event
	 */
	@Subscribe
	public void onQueueReadyTimedout(final QueueReadyTimedOutEvent queueReadyTimedOutEvent) {
		final Queue queue = queueReadyTimedOutEvent.getQueue();
		this.cancelQueueReady(queue);
		this.eventBus.post(new QueueModifiedEvent(queue));
		LOGGER.debug(String.format("Queue '%s' has terminated. At least one player was not ready.", queue.getName()));
		this.eventBus.post(new QueueModifiedEvent(queue));
	}

	/**
	 * Start ready timer on a queue.
	 *
	 * @param queue the queue
	 */
	private void startReadyTimer(final Queue queue) {
		final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.schedule(new Runnable() {
			@Override
			public void run() {
				if (queue.getState() == QueueState.WAITING_READY && !queue.hasEveryResponse()) {
					MatchmakingImpl.this.eventBus.post(new QueueReadyTimedOutEvent(queue));
				}
			}
		}, this.READY_TIMEOUT, TimeUnit.SECONDS);
	}

	/**
	 * Listen to server free event.
	 *
	 * @param serverFreeEvent the event
	 */
	@Subscribe
	public void onServerFree(final ServerFreeEvent serverFreeEvent) {
		for (final Queue queue : this.availableQueues) {
			if (queue.getServer() == serverFreeEvent.getServer()) {
				queue.setState(QueueState.GAME_OVER);
				this.eventBus.post(new QueueModifiedEvent(queue));
			}
		}
	}

	/**
	 * A player has been modified. Update all queue which contains it.<br/>
	 * FIXME : should only update the player instead of all queues
	 *
	 * @param playerModifiedEvent the event
	 */
	@Subscribe
	public void onPlayerModified(final PlayerModifiedEvent playerModifiedEvent) {
		for (final Queue queue : this.getQueues(playerModifiedEvent.getPlayer())) {
			this.eventBus.post(new QueueModifiedEvent(queue));
		}
	}

	/**
	 * A player has been ready. Update the queue.<br/>
	 *
	 * @param playerReadyEvent the event
	 */
	@Subscribe
	public void onPlayerReady(final PlayerReadyEvent playerReadyEvent) {
		this.eventBus.post(new QueueModifiedEvent(playerReadyEvent.getQueue()));
	}

	@Override
	public void setPlayerReady(final Player player, final Queue queue, final Boolean isReady) {
		if (queue.setPlayerReady(player, isReady)) {
			LOGGER.debug("Player " + player + " is ready(" + isReady + ") on queue : " + queue);
		}
	}

}
