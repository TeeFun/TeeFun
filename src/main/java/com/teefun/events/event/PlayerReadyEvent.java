/**
 *
 */
package com.teefun.events.event;

import com.teefun.model.Player;
import com.teefun.model.Queue;

/**
 * When a player is ready on a queue event.
 *
 * @author Rajh
 *
 */
public class PlayerReadyEvent {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The queue.
	 */
	private final Queue queue;

	/**
	 * Default constructor.
	 *
	 * @param player the player
	 * @param queue the queue
	 */
	public PlayerReadyEvent(final Player player, final Queue queue) {
		this.player = player;
		this.queue = queue;
	}

	/**
	 * @return the {@link #player}
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * @return the {@link #queue}
	 */
	public Queue getQueue() {
		return this.queue;
	}

}
