/**
 *
 */
package com.teefun.db.dao;

import java.util.List;

import com.teefun.db.entity.ExempleEntity;

/**
 * @author Rajh
 *
 */
public interface ExempleDAO {

	public void save(ExempleEntity entity);

	public List<ExempleEntity> list();
}
