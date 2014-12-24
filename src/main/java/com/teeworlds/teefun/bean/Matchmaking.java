package com.teeworlds.teefun.bean;

import java.util.List;

import com.teeworlds.teefun.model.Player;
import com.teeworlds.teefun.model.Queue;

/**
 * Class to handle the matchmaking system.
 *
 * @author Rajh
 */
public interface Matchmaking {

	/**
	 * Remove inactive players from queues.
	 */
	void removeInactivePlayers();

	/**
	 * @return the list of active players
	 */
	List<Player> activePlayers();

	/**
	 * Active a player.
	 *
	 * @param player the player
	 */
	void addPlayer(Player player);

	/**
	 * Disable a player.
	 *
	 * @param player the player
	 */
	void removePlayer(Player player);

	/**
	 * @return the list of available queues
	 */
	List<Queue> getQueues();

	/**
	 * Get a queue by name.
	 *
	 * @param name the name
	 * @return the queue
	 */
	Queue getQueueByName(String name);

	/**
	 * Join a queue by its name.
	 *
	 * @param player the player
	 * @param queue the queue
	 */
	void joinQueue(Player player, Queue queue);

	/**
	 * Leave a queue by its name.
	 *
	 * @param player the player
	 * @param queue the queue
	 */
	void leaveQueue(Player player, Queue queue);

	/**
	 * Leave all queues.
	 *
	 * @param player the player
	 */
	void leaveAllQueues(Player player);

	/**
	 * Add an available queue.
	 *
	 * @param queue the queue
	 */
	void addQueue(Queue queue);

	/**
	 * Remove a queue.
	 *
	 * @param queue the queue
	 */
	void removeQueue(Queue queue);

}
