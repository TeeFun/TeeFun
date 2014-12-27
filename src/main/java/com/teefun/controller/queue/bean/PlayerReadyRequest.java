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
	private final String queueName;

	@JsonProperty
	private final Boolean ready;

	public PlayerReadyRequest(final String queueName, final Boolean ready) {
		super();
		this.queueName = queueName;
		this.ready = ready;
	}

	/**
	 * @return the {@link #queueName}
	 */
	public String getQueueName() {
		return this.queueName;
	}

	/**
	 * @return the {@link #ready}
	 */
	public Boolean getReady() {
		return this.ready;
	}

}
