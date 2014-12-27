/**
 *
 */
package com.teefun.events.event;

import com.teefun.model.Queue;

/**
 * A queue has been created event.
 *
 * @author Rajh
 *
 */
public class QueueCreatedEvent {

	/**
	 * The queue modified.
	 */
	private final Queue queue;

	/**
	 * Default constructor.
	 *
	 * @param queue the queue
	 */
	public QueueCreatedEvent(final Queue queue) {
		this.queue = queue;
	}

	/**
	 * @return the {@link #queue}
	 */
	public Queue getQueue() {
		return this.queue;
	}
}
