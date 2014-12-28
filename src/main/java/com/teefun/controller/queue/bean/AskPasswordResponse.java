/**
 *
 */
package com.teefun.controller.queue.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Reponse for giving password to players in queue.
 *
 * @author Choupom
 *
 */
@JsonRootName("AskPasswordResponse")
public class AskPasswordResponse implements Serializable {

	/**
	 * SUID.
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty
	@NotNull
	private Integer queueId;

	@JsonProperty
	@NotBlank
	private String password;

	/**
	 * Constructor
	 */
	public AskPasswordResponse(final Integer queueId, final String password) {
		this.queueId = queueId;
		this.password = password;
	}

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
	 * @return the {@link #password}
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password the {@link #password} to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

}
