/**
 *
 */
package com.teefun.db.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.teefun.db.dao.QueueDAO;
import com.teefun.db.entity.QueueEntity;

/**
 * Default {@link QueueDAO}.
 *
 * @author Rajh
 *
 */
@Repository
public class QueueDAOImpl implements QueueDAO {

	/**
	 * The entity manager.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(final QueueEntity entity) {
		this.entityManager.persist(entity);
	}

	@Override
	public List<QueueEntity> list() {
		return null;
	}
}
