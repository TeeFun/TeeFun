/**
 *
 */
package com.teeworlds.teefun.model.teeworlds;

import java.io.File;

/**
 * A Teeworlds server configuration.
 *
 * @author Rajh
 *
 */
public class TeeworldsConfig {

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
	public void generatePassword() {
		// TODO generate a password.
	}

	public File generateConfigFile() {
		// TODO generate config file
		return null;
	}
}
