/**
 *
 */
package com.teefun.events.event;

import com.teefun.model.Queue;

/**
 * Game is ready event (waiting for player to be ready).
 *
 * @author Rajh
 *
 */
public class GameReadyEvent {

	/**
	 * The queue modified.
	 */
	private final Queue queue;

	/**
	 * Default constructor.
	 *
	 * @param queue the queue
	 */
	public GameReadyEvent(final Queue queue) {
		this.queue = queue;
	}

	/**
	 * @return the {@link #queue}
	 */
	public Queue getQueue() {
		return this.queue;
	}
}
