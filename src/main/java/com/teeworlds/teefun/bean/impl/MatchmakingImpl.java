package com.teeworlds.teefun.bean.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.teeworlds.teefun.bean.Matchmaking;
import com.teeworlds.teefun.model.Player;
import com.teeworlds.teefun.model.Queue;

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
	 * List of available queues.
	 */
	private final List<Queue> availableQueues = new ArrayList<Queue>();

	@Override
	public void removeInactivePlayers() {
		LOGGER.trace("Removing inactive players ...");
		for (final Queue queue : this.availableQueues) {
			final Iterator<Player> playerIter = queue.getPlayers().iterator();
			while (playerIter.hasNext()) {
				final Player player = playerIter.next();
				if (!player.isActive()) {
					LOGGER.debug(String.format("Player '%s' is inactive, removing it.", player.getName()));
					playerIter.remove();
				}
			}
		}

	}

	@Override
	public List<Queue> getQueues() {
		return this.availableQueues;
	}

	@Override
	public void joinQueue(final Player player, final Queue queue) {
		LOGGER.debug(String.format("Add player '%s' to queue '%s'.", player.getName(), queue.getName()));
		queue.addPlayer(player);
	}

	@Override
	public void quitQueue(final Player player, final Queue queue) {
		LOGGER.debug(String.format("Remove player '%s' from queue '%s'.", player.getName(), queue.getName()));
		queue.removePlayer(player);
	}

	@Override
	public void quitAllQueues(final Player player) {
		LOGGER.debug(String.format("Remove player '%s' from all queues.", player.getName()));
		for (final Queue queue : this.availableQueues) {
			queue.removePlayer(player);
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
			if (queue.getPlayers().contains(player)) {
				return true;
			}
		}
		return false;
	}

}
