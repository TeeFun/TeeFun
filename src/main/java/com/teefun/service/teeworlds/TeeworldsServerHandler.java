/**
 *
 */
package com.teefun.service.teeworlds;

import java.util.List;

import com.teefun.model.teeworlds.TeeworldsConfig;
import com.teefun.model.teeworlds.TeeworldsServer;

/**
 * Teeworlds server service.
 *
 * @author Rajh
 *
 */
public interface TeeworldsServerHandler {

	/**
	 * Create a teeworlds server.
	 *
	 * @param configuration the server configuration
	 * @return the server created
	 */
	public TeeworldsServer createAndBorrowServer(TeeworldsConfig configuration);

	/**
	 * Free a borrowed server.
	 *
	 * @param server the server
	 */
	public void freeServer(TeeworldsServer server);

	/**
	 * Start a teeworlds server.
	 *
	 * @param server the server
	 */
	public void startServer(TeeworldsServer server);

	/**
	 * Get the list of borrowed servers.
	 *
	 * @return the list of servers
	 */
	public List<TeeworldsServer> getBorrowedServers();

	/**
	 * Check if any server are available.
	 *
	 * @return true if at least one server is free
	 */
	public boolean hasServerAvailable();

	/**
	 * Get numbers of free servers.
	 *
	 * @return the nb of servers available
	 */
	public Integer getNbFreeServers();

}
