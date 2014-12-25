/**
 *
 */
package com.teeworlds.teefun.service.teeworlds.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.teeworlds.teefun.model.teeworlds.TeeworldsConfig;
import com.teeworlds.teefun.model.teeworlds.TeeworldsServer;
import com.teeworlds.teefun.service.teeworlds.TeeworldsServerHandler;

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
	 * List of currently running servers.
	 */
	private final List<TeeworldsServer> runningServers = new ArrayList<TeeworldsServer>();

	@Override
	public TeeworldsServer createServer(final TeeworldsConfig configuration) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Start the server.
	 *
	 * @param configFile the config file
	 */
	private void startServer(final File configFile) {
		final Runtime runtime = Runtime.getRuntime();
		try {
			final Process process = runtime.exec("monappli");
		} catch (final IOException e) {
			LOGGER.error("Error while running server.", e);
		}
	}

}
