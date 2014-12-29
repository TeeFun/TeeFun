/**
 *
 */
package com.teefun.db.service;

import java.util.List;

import com.teefun.model.Queue;

/**
 * Queue service to access dao.
 *
 * @author Rajh
 *
 */
public interface QueueService {

	/**
	 * @return all the queues
	 */
	List<Queue> getQueues();

	/**
	 * Persist queues. Should be moved into a service.
	 */
	void saveQueues(final List<Queue> queues);

	/**
	 * Persist queue.
	 */
	void saveQueue(final Queue queue);

	/**
	 * Delete queue.
	 */
	void removeQueue(final Queue queue);

}
