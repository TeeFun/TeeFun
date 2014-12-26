/**
 *
 */
package com.teefun.task;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.teefun.model.teeworlds.TeeworldsServer;
import com.teefun.service.teeworlds.TeeworldsServerHandler;

/**
 * Task used to shutdown server which timed out.
 *
 * @author Rajh
 *
 */
@Component
public class CheckServerTask {

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckServerTask.class);

	/**
	 * Teeworlds servers.
	 */
	@Resource
	private TeeworldsServerHandler teeworldsServerHandler;

	/**
	 * Remove leavers (not receiving keep alive packets).
	 */
	@Scheduled(fixedRate = 1 * 60 * 1000)
	public void freeServers() {
		LOGGER.trace("Free servers...");
		final List<TeeworldsServer> runningServers = this.teeworldsServerHandler.getBorrowedServers();
		for (final TeeworldsServer server : runningServers) {
			if (server.hasTimedOut()) {
				LOGGER.debug("Server has timed out force shutdown : " + server.getServerId());
				server.shutdown();
				this.teeworldsServerHandler.freeServer(server);
			}
			if (server.hasStarted() && server.hasStopped()) {
				LOGGER.debug("Server has shutdown : " + server.getServerId());
				server.shutdown();
				this.teeworldsServerHandler.freeServer(server);
			}
		}
	}
}
