/**
 *
 */
package com.teefun.events.event;

import com.teefun.model.Queue;

/**
 * A queue ready question has timed out.
 *
 * @author Rajh
 *
 */
public class QueueReadyTimedOutEvent {

	/**
	 * The queue modified.
	 */
	private final Queue queue;

	/**
	 * Default constructor.
	 *
	 * @param queue the queue
	 */
	public QueueReadyTimedOutEvent(final Queue queue) {
		this.queue = queue;
	}

	/**
	 * @return the {@link #queue}
	 */
	public Queue getQueue() {
		return this.queue;
	}
}
