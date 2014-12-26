/**
 *
 */
package com.teefun.task;

import java.util.Collections;
import java.util.Iterator;
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
	@Scheduled(fixedRate = 5 * 60 * 1000)
	public void freeServers() {
		LOGGER.trace("Removing inactive players ...");
		final List<TeeworldsServer> runningServers = Collections.synchronizedList(this.teeworldsServerHandler.getRunningServers());
		synchronized (runningServers) {
			final Iterator<TeeworldsServer> iter = runningServers.iterator();
			while (iter.hasNext()) {
				final TeeworldsServer server = iter.next();
				if (!server.isActive()) {
					LOGGER.debug("Server has timed out : " + server.getServerId());
					server.shutdown();
					iter.remove();
				}
			}
		}
	}
}
