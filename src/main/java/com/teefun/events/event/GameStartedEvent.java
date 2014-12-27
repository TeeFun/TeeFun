/**
 *
 */
package com.teefun.events.event;

import com.teefun.model.Queue;

/**
 * Game started event.
 *
 * @author Rajh
 *
 */
public class GameStartedEvent {

	/**
	 * The queue modified.
	 */
	private final Queue queue;

	/**
	 * Default constructor.
	 *
	 * @param queue the queue
	 */
	public GameStartedEvent(final Queue queue) {
		this.queue = queue;
	}

	/**
	 * @return the {@link #queue}
	 */
	public Queue getQueue() {
		return this.queue;
	}
}
