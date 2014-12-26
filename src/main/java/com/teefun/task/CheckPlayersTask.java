package com.teefun.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.teefun.bean.Matchmaking;

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
	@Scheduled(fixedRate = 15000)
	public void removeLeavers() {
		LOGGER.trace("Removing inactive players ...");
		this.matchmaking.removeInactivePlayers();
	}
}
