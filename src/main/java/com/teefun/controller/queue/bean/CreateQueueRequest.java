/**
 *
 */
package com.teefun.controller.queue.bean;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Request for creating a queue.
 *
 * @author Rajh
 *
 */
@JsonRootName("CreateQueueRequest")
public class CreateQueueRequest implements Serializable {

	/**
	 * SUID.
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty
	@NotBlank
	private String name;

	@JsonProperty
	@NotNull
	@Min(1)
	private Integer maxSize;

	@JsonProperty
	@NotBlank
	private String map;

	@JsonProperty
	@NotBlank
	private String gametype;

	@JsonProperty
	private Integer scoreLimit;

	@JsonProperty
	private Integer timeLimit;

	@JsonProperty
	@NotNull
	private Boolean permanent;

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
	public Integer getMaxSize() {
		return this.maxSize;
	}

	/**
	 * @param maxSize the {@link #maxSize} to set
	 */
	public void setMaxSize(final Integer maxSize) {
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
	public Integer getScoreLimit() {
		return this.scoreLimit;
	}

	/**
	 * @param scoreLimit the {@link #scoreLimit} to set
	 */
	public void setScoreLimit(final Integer scoreLimit) {
		this.scoreLimit = scoreLimit;
	}

	/**
	 * @return the {@link #timeLimit}
	 */
	public Integer getTimeLimit() {
		return this.timeLimit;
	}

	/**
	 * @param timeLimit the {@link #timeLimit} to set
	 */
	public void setTimeLimit(final Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * @return the {@link #permanent}
	 */
	public Boolean getPermanent() {
		return this.permanent;
	}

	/**
	 * @param permanent the {@link #permanent} to set
	 */
	public void setPermanent(final Boolean permanent) {
		this.permanent = permanent;
	}

}
