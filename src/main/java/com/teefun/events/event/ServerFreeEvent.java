/**
 *
 */
package com.teefun.events.event;

import com.teefun.model.teeworlds.TeeworldsServer;
import com.teefun.service.teeworlds.impl.TeeworldsServerHandlerImpl;

/**
 * Server has been free by {@link TeeworldsServerHandlerImpl}.
 *
 * @author Rajh
 *
 */
public class ServerFreeEvent {

	/**
	 * The server free.
	 */
	private final TeeworldsServer server;

	/**
	 * Default constructor.
	 *
	 * @param server the server
	 */
	public ServerFreeEvent(final TeeworldsServer server) {
		this.server = server;
	}

	/**
	 * @return the {@link #server}
	 */
	public TeeworldsServer getServer() {
		return this.server;
	}

}
