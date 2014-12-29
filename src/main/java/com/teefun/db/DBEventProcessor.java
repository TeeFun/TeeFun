/**
 *
 */
package com.teefun.db;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.teefun.db.service.QueueService;
import com.teefun.events.event.QueueCreatedEvent;
import com.teefun.events.event.QueueDeletedEvent;

/**
 * Process events for DB in this class due to Transactional creating a proxy preventing the events to work correctly.
 *
 * @author Rajh
 *
 */
@Component
public class DBEventProcessor {

	/**
	 * Queue service.
	 */
	@Resource
	private QueueService queueService;

	/**
	 * Persist queue on queue created.
	 *
	 * @param queueCreatedEvent the event
	 */
	@Subscribe
	public void onQueueCreated(final QueueCreatedEvent queueCreatedEvent) {
		this.queueService.saveQueue(queueCreatedEvent.getQueue());
	}

	/**
	 * Remove from DB queue on queue deleted.
	 *
	 * @param queueCreatedEvent the event
	 */
	@Subscribe
	public void onQueueDeleted(final QueueDeletedEvent queueDeletedEvent) {
		this.queueService.removeQueue(queueDeletedEvent.getQueue());
	}

}
