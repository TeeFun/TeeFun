package com.teefun.bean.matchmaking;

import java.util.List;

import com.teefun.model.Player;
import com.teefun.model.Queue;

/**
 * Class to handle the matchmaking system.
 *
 * @author Rajh
 */
public interface Matchmaking {

	/**
	 * @return the list of available queues
	 */
	List<Queue> getQueues();

	/**
	 * Get all queue which the player has join.
	 *
	 * @param player the player
	 * @return the list of available queues
	 */
	List<Queue> getQueues(Player player);

	/**
	 * Get a queue by name.
	 *
	 * @param name the name
	 * @return the queue
	 */
	Queue getQueueByName(String name);

	/**
	 * Get a queue by id.
	 *
	 * @param id the id
	 * @return the queue
	 */
	Queue getQueueById(Integer id);

	/**
	 * Join a queue by its name.
	 *
	 * @param player the player
	 * @param queue the queue
	 */
	void joinQueue(Player player, Queue queue);

	/**
	 * Quit a queue by its name.
	 *
	 * @param player the player
	 * @param queue the queue
	 */
	void quitQueue(Player player, Queue queue);

	/**
	 * Quit all queues.
	 *
	 * @param player the player
	 */
	void quitAllQueues(Player player);

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

	/**
	 * Is a player in any queue.
	 *
	 * @param player the player
	 * @return true if the player is at least in one queue
	 */
	boolean isInQueue(Player player);

	/**
	 * Set a player ready state.
	 *
	 * @param player the player
	 * @param queue the queue
	 * @param isReady the ready response
	 */
	void setPlayerReady(Player player, Queue queue, Boolean isReady);

}
