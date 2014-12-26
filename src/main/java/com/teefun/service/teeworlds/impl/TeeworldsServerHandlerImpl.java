/**
 *
 */
package com.teefun.service.teeworlds.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.teefun.exception.TeeFunRuntimeException;
import com.teefun.model.teeworlds.TeeworldsConfig;
import com.teefun.model.teeworlds.TeeworldsServer;
import com.teefun.service.teeworlds.TeeworldsServerHandler;

/**
 * Default impl for {@link TeeworldsServerHandler}.
 *
 * @author Rajh
 *
 */
@Service
public class TeeworldsServerHandlerImpl implements TeeworldsServerHandler {

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TeeworldsServerHandlerImpl.class);

	/**
	 * Start server script.
	 */
	private static final String TEEWORLDS_START_SERVER_SCRIPT = "/opt/teeworlds/start_server.sh";

	/**
	 * Start server script.
	 */
	private static final String TEEWORLDS_CLEANUP_SERVERS_SCRIPT = "/opt/teeworlds/cleanup_servers.sh";

	/**
	 * Number of maximum running servers.
	 */
	private static final Integer MAX_SERVER_AVAILABLE = 2;

	/**
	 * List of currently running servers.
	 */
	private final List<TeeworldsServer> runningServers = new ArrayList<TeeworldsServer>();

	/**
	 * Clean servers at startup. For remaining servers.
	 *
	 * @throws IOException
	 */
	@PostConstruct
	public void cleanupServers() throws IOException {
		LOGGER.debug("Cleaning zombie servers.");
		new ProcessBuilder(TEEWORLDS_CLEANUP_SERVERS_SCRIPT).start();
	}

	@Override
	public TeeworldsServer createServer(final TeeworldsConfig configuration) {
		if (this.runningServers.size() >= MAX_SERVER_AVAILABLE) {
			throw new TeeFunRuntimeException("Maximum server size reached.");
		}
		final TeeworldsServer server = this.startServer(configuration);
		this.runningServers.add(server);
		return server;
	}

	/**
	 * Start the server.
	 *
	 * @param configFile the configuration
	 * @return the server
	 */
	private TeeworldsServer startServer(final TeeworldsConfig configuration) {
		try {
			final String serverId = this.generateUUID();
			final Path configPath = configuration.generateConfigFile();
			final Process process = new ProcessBuilder(TEEWORLDS_START_SERVER_SCRIPT, configPath.toAbsolutePath().toString(), serverId).start();
			final TeeworldsServer server = new TeeworldsServer(configuration, System.currentTimeMillis(), process, serverId);
			LOGGER.debug("Server : " + serverId + " started.");
			return server;
		} catch (final IOException e) {
			LOGGER.error("Error while running server.", e);
			throw new TeeFunRuntimeException("Error while running server.", e);
		}
	}

	/**
	 * Generate an UUID for the server.
	 *
	 * @return the UUID
	 */
	private String generateUUID() {
		return UUID.randomUUID().toString();
	}

	@Override
	public List<TeeworldsServer> getRunningServers() {
		return this.runningServers;
	}

	@Override
	public boolean hasServerAvailable() {
		return this.runningServers.size() < MAX_SERVER_AVAILABLE;
	}

}
