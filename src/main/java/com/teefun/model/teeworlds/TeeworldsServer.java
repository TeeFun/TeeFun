/**
 *
 */
package com.teefun.model.teeworlds;

import java.util.concurrent.TimeUnit;

/**
 * A Teeworlds Server.
 *
 * @author Rajh
 *
 */
public class TeeworldsServer {

	/**
	 * Server time to live. Default 30 min.
	 */
	private static final Long SERVER_TTL = TimeUnit.MINUTES.toMillis(2);

	/**
	 * Server configuration.
	 */
	private final TeeworldsConfig config;

	/**
	 * When the server started.
	 */
	private final Long startTime;

	/**
	 * Server UUID.
	 */
	private final String serverId;

	/**
	 * The process.
	 */
	private final Process process;

	/**
	 * Default constructor.
	 *
	 * @param config the configuration used
	 * @param startTime when the server started
	 * @param process the process associated
	 * @param serverId the server UUID
	 */
	public TeeworldsServer(final TeeworldsConfig config, final Long startTime, final Process process, final String serverId) {
		super();
		this.config = config;
		this.startTime = startTime;
		this.process = process;
		this.serverId = serverId;
	}

	/**
	 * @return the {@link #config}
	 */
	public TeeworldsConfig getConfig() {
		return this.config;
	}

	/**
	 * @return the {@link #startTime}
	 */
	public Long getStartTime() {
		return this.startTime;
	}

	/**
	 * @return the {@link #process}
	 */
	public Process getProcess() {
		return this.process;
	}

	/**
	 * @return the {@link #serverId}
	 */
	public String getServerId() {
		return this.serverId;
	}

	/**
	 * @return false if the server has timed out
	 */
	public boolean isActive() {
		return this.process.isAlive() && !this.asTimedOut();
	}

	private boolean asTimedOut() {
		return System.currentTimeMillis() - this.startTime < SERVER_TTL;
	}

	/**
	 * Force shutdown the server
	 */
	public void shutdown() {
		this.process.destroy();
	}

}
