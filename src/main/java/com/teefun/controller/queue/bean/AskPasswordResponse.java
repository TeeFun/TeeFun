/**
 *
 */
package com.teefun.controller.queue.bean;

import java.io.Serializable;

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
	@NotBlank
	private String serverName;

	@JsonProperty
	@NotBlank
	private String password;

	/**
	 * Constructor
	 */
	public AskPasswordResponse(final String serverName, final String password) {
		this.serverName = serverName;
		this.password = password;
	}

	/**
	 * @return the {@link #serverName}
	 */
	public String getServerName() {
		return this.serverName;
	}

	/**
	 * @param serverName the {@link #serverName} to set
	 */
	public void setServerName(final String serverName) {
		this.serverName = serverName;
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
