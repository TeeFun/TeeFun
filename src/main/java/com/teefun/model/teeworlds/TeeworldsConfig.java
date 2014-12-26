/**
 *
 */
package com.teefun.model.teeworlds;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Teeworlds server configuration.
 *
 * @author Rajh
 *
 */
public class TeeworldsConfig {

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TeeworldsConfig.class);

	/**
	 * Server name.
	 */
	private String name;

	/**
	 * Server password.
	 */
	private String password;

	/**
	 * Server's ip (if null, will be auto handled).
	 */
	private String bindAddr;

	/**
	 * Server port (if null, will be auto handled).
	 */
	private String port;

	/**
	 * Max clients.
	 */
	private Integer maxClients;

	/**
	 * Map.
	 */
	private String map;

	/* Game settings */

	/**
	 * Warmup time.
	 */
	private Integer warmup;

	/**
	 * Score limit.
	 */
	private Integer scoreLimit;

	/**
	 * Time limit.
	 */
	private Integer timeLimit;

	/**
	 * Game type.
	 */
	private String gameType;

	/**
	 * Server's motd.
	 */
	private String motd;

	/**
	 * Generate a random password.
	 */
	public String generatePassword() {
		this.password = "test";
		LOGGER.trace("Generated password : " + this.password);
		return this.password;
	}

	public Path generateConfigFile() {
		LOGGER.trace("Generated config file.");
		// TODO generate config file
		return Paths.get("/tmp/server.cfg");
	}
}
