/**
 *
 */
package com.teefun.db.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
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

	/**
	 * Query builder.
	 */
	private CriteriaBuilder builder;

	/**
	 * Create builder.
	 */
	@PostConstruct
	public void initBuilder() {
		this.builder = this.entityManager.getCriteriaBuilder();
	}

	@Override
	@Transactional
	public void save(final QueueEntity entity) {
		this.entityManager.persist(entity);
	}

	@Override
	public List<QueueEntity> list() {
		final CriteriaQuery<QueueEntity> criteria = this.builder.createQuery(QueueEntity.class);
		final Root<QueueEntity> queue = criteria.from(QueueEntity.class);
		criteria.select(queue);
		return this.entityManager.createQuery(criteria).getResultList();
	}

	@Override
	public QueueEntity getByName(final String name) {
		final CriteriaQuery<QueueEntity> criteria = this.builder.createQuery(QueueEntity.class);
		final Root<QueueEntity> queueRoot = criteria.from(QueueEntity.class);
		final EntityType<QueueEntity> QueueEntity_ = this.entityManager.getMetamodel().entity(QueueEntity.class);
		criteria.select(queueRoot);
		final Predicate namePredicate = this.builder.like(queueRoot.<String> get(QueueEntity_.getName()), name);
		criteria.where(namePredicate);
		try {
			return this.entityManager.createQuery(criteria).getSingleResult();
		} catch (final NoResultException ex) {
			return null;
		}
	}

	@Override
	@Transactional
	public void remove(final QueueEntity entity) {
		this.entityManager.remove(entity);
	}
}
