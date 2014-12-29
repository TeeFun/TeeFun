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
 * @author Choupom
 *
 */
@Entity
@Table(name = "Queue")
public class QueueEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "maxSize")
	private int maxSize;

	@Column(name = "map")
	private String map;

	@Column(name = "gametype")
	private String gametype;

	@Column(name = "scoreLimit")
	private int scoreLimit;

	@Column(name = "timeLimit")
	private int timeLimit;

	@Column(name = "permanent")
	private boolean permanent;

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

	public String getMap() {
		return this.map;
	}

	public void setMap(final String map) {
		this.map = map;
	}

	public String getGametype() {
		return this.gametype;
	}

	public void setGametype(final String gametype) {
		this.gametype = gametype;
	}

	public int getScoreLimit() {
		return this.scoreLimit;
	}

	public void setScoreLimit(final int scoreLimit) {
		this.scoreLimit = scoreLimit;
	}

	public int getTimeLimit() {
		return this.timeLimit;
	}

	public void setTimeLimit(final int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public boolean getPermanent() {
		return this.permanent;
	}

	public void setPermanent(final boolean permanent) {
		this.permanent = permanent;
	}

}