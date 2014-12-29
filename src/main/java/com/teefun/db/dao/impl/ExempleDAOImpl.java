/**
 *
 */
package com.teefun.db.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.teefun.db.dao.ExempleDAO;
import com.teefun.db.entity.ExempleEntity;

/**
 * @author Rajh
 *
 */
public class ExempleDAOImpl implements ExempleDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(final ExempleEntity entity) {
		this.entityManager.persist(entity);
	}

	@Override
	public List<QueueEntity> getQueue(final int queueId) {
		final String queryString = "SELECT Q FROM QueueEntity Q WHERE Q.id = queueId";
		final TypedQuery<QueueEntity> query = this.entityManager.createQuery(queryString);
		return query.getResultList();
	}

	@Override
	public List<PlayerEntity> getPlayer(final int playerId) {
		final String queryString = "SELECT P FROM PlayerEntity P WHERE P.id = playerId";
		final TypedQuery<PlayerEntity> query = this.entityManager.createQuery(queryString);
		return query.getResultList();
	}

	@Override
	public List<PlayerQueueEntity> getQueuePlayers(final int queueId) {
		final String queryString = "SELECT PQ FROM PlayerQueueEntity PQ WHERE PQ.queueId = queueId";
		final TypedQuery<PlayerQueueEntity> query = this.entityManager.createQuery(queryString);
		return query.getResultList();
	}
}
