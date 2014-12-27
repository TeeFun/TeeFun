/**
 *
 */
package com.teefun.events.event;

import com.teefun.model.Queue;

/**
 * Game has been aborted event.
 *
 * @author Rajh
 *
 */
public class GameAbortedEvent {

	/**
	 * The queue modified.
	 */
	private final Queue queue;

	/**
	 * Default constructor.
	 *
	 * @param queue the queue
	 */
	public GameAbortedEvent(final Queue queue) {
		this.queue = queue;
	}

	/**
	 * @return the {@link #queue}
	 */
	public Queue getQueue() {
		return this.queue;
	}
}
