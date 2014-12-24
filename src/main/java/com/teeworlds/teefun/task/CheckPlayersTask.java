package com.teeworlds.teefun.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.teeworlds.teefun.bean.Matchmaking;

/**
 * Task to check inactive player and remove them from queues.
 * 
 * @author Rajh
 * 
 */
@Component
public class CheckPlayersTask {

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
		this.matchmaking.removeInactivePlayers();
	}
}
