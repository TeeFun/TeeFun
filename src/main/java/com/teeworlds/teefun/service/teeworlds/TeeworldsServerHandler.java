/**
 *
 */
package com.teeworlds.teefun.service.teeworlds;

import com.teeworlds.teefun.model.teeworlds.TeeworldsConfig;
import com.teeworlds.teefun.model.teeworlds.TeeworldsServer;

/**
 * Teeworlds server service.
 *
 * @author Rajh
 *
 */
public interface TeeworldsServerHandler {

	/**
	 * Create and start a teeworlds server.
	 *
	 * @param configuration the server configuration
	 * @return the server created
	 */
	public TeeworldsServer createServer(TeeworldsConfig configuration);

}
