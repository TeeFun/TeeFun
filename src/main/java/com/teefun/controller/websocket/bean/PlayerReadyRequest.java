/**
 *
 */
package com.teefun.controller.websocket.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Request for player ready.
 *
 * @author Rajh
 *
 */
@JsonRootName("CreateQueueRequest")
public class PlayerReadyRequest implements Serializable {

	/**
	 * SUID.
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty
	@NotNull
	private Integer queueId;

	@JsonProperty
	@NotNull
	private Boolean ready;

	/**
	 * @return the {@link #queueId}
	 */
	public Integer getQueueId() {
		return this.queueId;
	}

	/**
	 * @param queueId the {@link #queueId} to set
	 */
	public void setQueueId(final Integer queueId) {
		this.queueId = queueId;
	}

	/**
	 * @return the {@link #ready}
	 */
	public Boolean getReady() {
		return this.ready;
	}

	/**
	 * @param ready the {@link #ready} to set
	 */
	public void setReady(final Boolean ready) {
		this.ready = ready;
	}

}
