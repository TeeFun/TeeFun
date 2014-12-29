/**
 *
 */
package com.teefun.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Choupom
 *
 */
@Entity
@Table(name = "PlayerQueue")
public class PlayerQueueEntity {

	@Id
	@Column(name = "queueId")
	private int queueId;

	@Id
	@Column(name = "playerId")
	private int playerId;

	public int getQueueId() {
		return this.queueId;
	}

	public void setQueueId(final int queueId) {
		this.queueId = queueId;
	}

	public int getPlayerId() {
		return this.playerId;
	}

	public void setPlayerId(final int playerId) {
		this.playerId = playerId;
	}

}