package com.teefun.task;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.teefun.bean.Matchmaking;
import com.teefun.model.Player;
import com.teefun.model.Queue;

/**
 * Task to check inactive player and remove them from queues.
 *
 * @author Rajh
 *
 */
@Component
public class CheckPlayersTask {

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckPlayersTask.class);

	/**
	 * Matchmaking system.
	 */
	@Resource
	private Matchmaking matchmaking;

	/**
	 * Remove leavers (not receiving keep alive packets).
	 */
	@Scheduled(fixedRate = 15 * 1000)
	public void removeLeavers() {
		LOGGER.trace("Removing inactive players ...");
		final List<Queue> queues = Collections.synchronizedList(this.matchmaking.getQueues());
		synchronized (queues) {
			for (final Queue queue : queues) {
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

	}
}
