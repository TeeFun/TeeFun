/**
 *
 */
package com.teefun.db.dao;

import java.util.List;

import com.teefun.db.entity.QueueEntity;

/**
 * DAO for queues.
 *
 * @author Rajh
 *
 */
public interface QueueDAO {

	/**
	 * Save a queue.
	 *
	 * @param entity the entity
	 */
	public void save(QueueEntity entity);

	/**
	 * Get list of queue.
	 *
	 * @return the list of entities
	 */
	public List<QueueEntity> list();

	/**
	 * Get a queue entity by name
	 *
	 * @param name the name
	 * @return the queue entity if exist
	 */
	public QueueEntity getByName(String name);

	/**
	 * Remove a queue entity.
	 *
	 * @param entity the entity
	 */
	public void remove(QueueEntity entity);
}
