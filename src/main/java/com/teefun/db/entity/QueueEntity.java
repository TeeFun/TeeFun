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
 * A Queue entity in DB.
 *
 * @author Choupom
 *
 */
@Entity
@Table(name = "Queue")
public class QueueEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@Column
	private int maxSize;

	@Column
	private String map;

	@Column
	private String gametype;

	@Column
	private int scoreLimit;

	@Column
	private int timeLimit;

	@Column
	private boolean active;

	/**
	 * @return the {@link #id}
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param id the {@link #id} to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the {@link #name} to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the {@link #maxSize}
	 */
	public int getMaxSize() {
		return this.maxSize;
	}

	/**
	 * @param maxSize the {@link #maxSize} to set
	 */
	public void setMaxSize(final int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * @return the {@link #map}
	 */
	public String getMap() {
		return this.map;
	}

	/**
	 * @param map the {@link #map} to set
	 */
	public void setMap(final String map) {
		this.map = map;
	}

	/**
	 * @return the {@link #gametype}
	 */
	public String getGametype() {
		return this.gametype;
	}

	/**
	 * @param gametype the {@link #gametype} to set
	 */
	public void setGametype(final String gametype) {
		this.gametype = gametype;
	}

	/**
	 * @return the {@link #scoreLimit}
	 */
	public int getScoreLimit() {
		return this.scoreLimit;
	}

	/**
	 * @param scoreLimit the {@link #scoreLimit} to set
	 */
	public void setScoreLimit(final int scoreLimit) {
		this.scoreLimit = scoreLimit;
	}

	/**
	 * @return the {@link #timeLimit}
	 */
	public int getTimeLimit() {
		return this.timeLimit;
	}

	/**
	 * @param timeLimit the {@link #timeLimit} to set
	 */
	public void setTimeLimit(final int timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * @return the {@link #active}
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * @param active the {@link #active} to set
	 */
	public void setActive(final boolean active) {
		this.active = active;
	}

}