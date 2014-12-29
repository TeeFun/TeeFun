/**
 *
 */
package com.teefun.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Rajh
 *
 */
/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 *
 * @author pankaj
 *
 */
@Entity
@Table(name = "ExempleEntity")
public class ExempleEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}