package com.teefun.task;

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
		try {
			final List<Queue> queues = this.matchmaking.getQueues();
			for (final Queue queue : queues) {
				for (final Player player : queue.getPlayers()) {
					if (!player.isActive()) {
						LOGGER.debug(String.format("Player '%s' is inactive, removing it.", player.getName()));
						this.matchmaking.quitQueue(player, queue);
					}
				}
			}
		} catch (final Exception ex) {
			LOGGER.error("Error in removeLeavers task.", ex);
		}

	}
}
