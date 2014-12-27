/**
 *
 */
package com.teefun.events.event;

import com.teefun.model.Queue;

/**
 * A queue has been deleted event.
 *
 * @author Rajh
 *
 */
public class QueueDeletedEvent {

	/**
	 * The queue modified.
	 */
	private final Queue queue;

	/**
	 * Default constructor.
	 *
	 * @param queue the queue
	 */
	public QueueDeletedEvent(final Queue queue) {
		this.queue = queue;
	}

	/**
	 * @return the {@link #queue}
	 */
	public Queue getQueue() {
		return this.queue;
	}
}
