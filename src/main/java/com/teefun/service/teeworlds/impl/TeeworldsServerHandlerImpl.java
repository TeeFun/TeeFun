/**
 *
 */
package com.teefun.service.teeworlds.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.SocketUtils;

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
	private static final String TEEWORLDS_SERVER_PATH = "/opt/teeworlds/teeworlds_srv";

	/**
	 * Pattern for config filename generation.
	 */
	private static final String CONFIG_FILENAME_PATTERN = "/opt/teeworlds/configs/server-%s.cfg";

	/**
	 * Pattern for log filename generation.
	 */
	private static final String LOG_FILENAME_PATTERN = "/opt/teeworlds/logs/log-%s.cfg";

	/**
	 * Min port available.
	 */
	private static final int TEEWORLDS_MIN_PORT = 27015;

	/**
	 * Max port available.
	 */
	private static final int TEEWORLDS_MAX_PORT = 27020;

	/**
	 * Start server script.
	 */
	private static final String TEEWORLDS_CLEANUP_SERVERS_SCRIPT = "/opt/teeworlds/cleanup_servers.sh";

	/**
	 * Number of maximum running servers.
	 */
	private static final Integer MAX_SERVER_AVAILABLE = 2;

	/**
	 * List of currently borrowed servers.
	 */
	private final List<TeeworldsServer> borrowedServers = new CopyOnWriteArrayList<TeeworldsServer>();

	/**
	 * Clean servers at startup. For remaining servers.
	 *
	 * @throws IOException
	 */
	@PostConstruct
	@PreDestroy
	public void cleanupServers() throws IOException {
		try {
			LOGGER.debug("Cleaning zombie servers.");
			new ProcessBuilder(TEEWORLDS_CLEANUP_SERVERS_SCRIPT).start();
		} catch (final Exception exception) {
			LOGGER.error("Error while cleaning zombie servers.", exception);
		}
	}

	@Override
	public TeeworldsServer createAndBorrowServer(final TeeworldsConfig configuration) {
		if (this.borrowedServers.size() >= MAX_SERVER_AVAILABLE) {
			throw new TeeFunRuntimeException("Maximum server size reached.");
		}

		final String serverId = this.generateUUID();
		try {
			final Integer port = SocketUtils.findAvailableUdpPort(TEEWORLDS_MIN_PORT, TEEWORLDS_MAX_PORT);
			configuration.setVariable("sv_port", port);
		} catch (final IllegalStateException exception) {
			LOGGER.error("Could not find any port.", exception);
			throw new TeeFunRuntimeException("Could not find any port.", exception);
		}
		configuration.setVariable("logfile", String.format(LOG_FILENAME_PATTERN, serverId));
		configuration.generatePassword();

		final TeeworldsServer server = new TeeworldsServer(configuration, System.currentTimeMillis(), serverId);

		this.borrowedServers.add(server);

		LOGGER.debug("Created server : " + server.getServerId() + ". " + this.getNbFreeServers() + "/" + MAX_SERVER_AVAILABLE + " .");
		return server;
	}

	@Override
	public void freeServer(final TeeworldsServer server) {
		this.borrowedServers.remove(server);
	}

	@Override
	public void startServer(final TeeworldsServer server) {
		if (!this.borrowedServers.contains(server)) {
			final String msg = "Trying to start an unknown server. Please make you you borrowed it first and it did not timedout";
			LOGGER.error(msg);
			throw new TeeFunRuntimeException(msg);
		}
		try {
			final Path configPath = Paths.get(String.format(CONFIG_FILENAME_PATTERN, server.getServerId()));
			server.getConfig().generateConfigFile(configPath);
			final Process process = new ProcessBuilder(TEEWORLDS_SERVER_PATH, "-f", configPath.toAbsolutePath().toString()).start();
			server.setProcess(process);

			LOGGER.debug("Server : " + server.getServerId() + " started.");
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
	public List<TeeworldsServer> getBorrowedServers() {
		return this.borrowedServers;
	}

	@Override
	public boolean hasServerAvailable() {
		return this.borrowedServers.size() < MAX_SERVER_AVAILABLE;
	}

	@Override
	public Integer getNbFreeServers() {
		return MAX_SERVER_AVAILABLE - this.borrowedServers.size();
	}

}
