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
	public List<ExempleEntity> list() {
		final TypedQuery<ExempleEntity> query = this.entityManager.createQuery("SELECT e FROM ExempleEntity e", ExempleEntity.class);
		return query.getResultList();
	}
}
