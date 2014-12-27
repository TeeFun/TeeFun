/**
 *
 */
package com.teefun.model.teeworlds;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;

import com.teefun.util.ProcessUtil;

/**
 * A Teeworlds Server.
 *
 * @author Rajh
 *
 */
public class TeeworldsServer {

	/**
	 * Server time to live in minutes.
	 */
	@Value("${teeworlds.server.ttl}")
	private static Long SERVER_TTL;

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
	 * @param startTime when the server started
	 * @param process the process associated
	 * @param serverId the server UUID
	 */
	public TeeworldsServer(final TeeworldsConfig config, final Long startTime, final String serverId) {
		super();
		this.config = config;
		this.startTime = startTime;
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
	 * @return true if the server has started
	 */
	public boolean hasStarted() {
		return this.process != null;
	}

	/**
	 * @return true if the server has stopped
	 */
	public boolean hasStopped() {
		return ProcessUtil.isRunning(this.process);
	}

	/**
	 * @return true if the server has timed out and should be killed
	 */
	public boolean hasTimedOut() {
		return System.currentTimeMillis() - this.startTime >= TimeUnit.MINUTES.toMillis(SERVER_TTL);
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
