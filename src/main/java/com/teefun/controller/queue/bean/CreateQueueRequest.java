/**
 *
 */
package com.teefun.controller.queue.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Request for creating a queue.
 *
 * @author Rajh
 *
 */
@JsonRootName("CreateQueueRequest")
public class CreateQueueRequest {

	@JsonProperty
	private final String name;

	@JsonProperty
	private final Integer maxSize;

	@JsonProperty
	private final String map;

	@JsonProperty
	private final String gametype;

	@JsonProperty
	private final Integer scoreLimit;

	@JsonProperty
	private final Integer timeLimit;

	@JsonProperty
	private final Boolean permanent;

	public CreateQueueRequest(final String name, final Integer maxSize, final String map, final String gametype, final Integer scoreLimit, final Integer timeLimit, final Boolean permanent) {
		super();
		this.name = name;
		this.maxSize = maxSize;
		this.map = map;
		this.gametype = gametype;
		this.scoreLimit = scoreLimit;
		this.timeLimit = timeLimit;
		this.permanent = permanent;
	}

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the {@link #maxSize}
	 */
	public Integer getMaxSize() {
		return this.maxSize;
	}

	/**
	 * @return the {@link #map}
	 */
	public String getMap() {
		return this.map;
	}

	/**
	 * @return the {@link #gametype}
	 */
	public String getGametype() {
		return this.gametype;
	}

	/**
	 * @return the {@link #scoreLimit}
	 */
	public Integer getScoreLimit() {
		return this.scoreLimit;
	}

	/**
	 * @return the {@link #timeLimit}
	 */
	public Integer getTimeLimit() {
		return this.timeLimit;
	}

	/**
	 * @return the {@link #permanent}
	 */
	public Boolean getPermanent() {
		return this.permanent;
	}

}
