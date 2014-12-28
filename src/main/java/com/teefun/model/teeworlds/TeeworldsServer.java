/**
 *
 */
package com.teefun.model.teeworlds;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teefun.util.ProcessUtil;

/**
 * A Teeworlds Server.
 *
 * @author Rajh
 *
 */
public class TeeworldsServer {

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TeeworldsServer.class);

	/**
	 * Server time to live in minutes.
	 */
	private final Long serverTtl;

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
	private Process process;

	/**
	 * Default constructor.
	 *
	 * @param config the configuration used
	 * @param serverId the server UUID
	 * @param startTime when the server started
	 * @param serverTtl the server time to live
	 */
	public TeeworldsServer(final TeeworldsConfig config, final String serverId, final Long startTime, final Long serverTtl) {
		super();
		this.config = config;
		this.startTime = startTime;
		this.serverId = serverId;
		this.serverTtl = serverTtl;
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
	 * @return true if the server has started
	 */
	public boolean hasStarted() {
		return this.process != null;
	}

	/**
	 * @return true if the server has stopped
	 */
	public boolean hasStopped() {
		// FIXME
		LOGGER.debug("Server running : " + ProcessUtil.isRunning(this.process));
		return false;
	}

	/**
	 * @return true if the server has timed out and should be killed
	 */
	public boolean hasTimedOut() {
		return System.currentTimeMillis() - this.startTime >= TimeUnit.MINUTES.toMillis(this.serverTtl);
	}

	/**
	 * Force shutdown the server
	 */
	public void shutdown() {
		this.process.destroy();
	}

	/**
	 * @param process the {@link #process} to set
	 */
	public void setProcess(final Process process) {
		this.process = process;
	}

}
