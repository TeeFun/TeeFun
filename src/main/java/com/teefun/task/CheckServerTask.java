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
		final List<TeeworldsServer> runningServers = this.teeworldsServerHandler.getRunningServers();
		for (final TeeworldsServer server : runningServers) {
			if (!server.isActive()) {
				LOGGER.debug("Server shutdown : " + server.getServerId());
				server.shutdown();
				// Safe due to CopyOnWriteArrayList
				runningServers.remove(server);
			}
		}
	}
}
