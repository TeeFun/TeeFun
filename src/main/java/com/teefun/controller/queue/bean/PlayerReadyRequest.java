/**
 *
 */
package com.teefun.controller.queue.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Request for player ready.
 *
 * @author Rajh
 *
 */
@JsonRootName("CreateQueueRequest")
public class PlayerReadyRequest {

	@JsonProperty
	private final String queueId;

	@JsonProperty
	private final Boolean ready;

	public PlayerReadyRequest(final String queueId, final Boolean ready) {
		super();
		this.queueId = queueId;
		this.ready = ready;
	}

	/**
	 * @return the {@link #queueId}
	 */
	public String getQueueId() {
		return this.queueId;
	}

	/**
	 * @return the {@link #ready}
	 */
	public Boolean getReady() {
		return this.ready;
	}

}
