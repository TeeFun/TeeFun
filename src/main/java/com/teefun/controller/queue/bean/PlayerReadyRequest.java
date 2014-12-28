/**
 *
 */
package com.teefun.controller.queue.bean;

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
	private Boolean isReady;

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
	 * @return the {@link #isReady}
	 */
	public Boolean getIsReady() {
		return this.isReady;
	}

	/**
	 * @param isReady the {@link #isReady} to set
	 */
	public void setIsReady(final Boolean isReady) {
		this.isReady = isReady;
	}

}
