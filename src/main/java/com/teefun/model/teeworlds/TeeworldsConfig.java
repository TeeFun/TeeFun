/**
 *
 */
package com.teefun.model.teeworlds;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teefun.exception.TeeFunRuntimeException;

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
	 * Pattern for config filename generation.
	 */
	private static final String CONFIG_FILENAME_PATTERN = "/tmp/server-%s.cfg";

	/**
	 * Variables hashmap.
	 */
	private final HashMap<String, String> variables;

	/**
	 * Constructor.
	 */
	public TeeworldsConfig() {
		this.variables = new HashMap<String, String>();
	}

	/**
	 * Sets a variable to a string value.
	 */
	public void setVariable(final String name, final String value) {
		this.variables.put(name, String.format("\"%s\"", value));
	}

	/**
	 * Sets a variable to an integer value.
	 */
	public void setVariable(final String name, final int value) {
		this.variables.put(name, String.format("%d", value));
	}

	/**
	 * Generate a random password.
	 */
	public String generatePassword() {
		final String password = "test";
		LOGGER.trace("Generated password : " + password);
		this.setVariable("password", password);
		return password;
	}

	/**
	 * Generate the config file that this object represents.
	 *
	 * @param uuid the unique id
	 * @return the file path
	 */
	public Path generateConfigFile(final String uuid) {
		final Path path = Paths.get(String.format(CONFIG_FILENAME_PATTERN, uuid));
		try {
			final BufferedWriter writer = Files.newBufferedWriter(path, Charset.defaultCharset());
			for (final Entry<String, String> entry : this.variables.entrySet()) {
				writer.append(String.format("%s %s", entry.getKey(), entry.getValue()));
				writer.newLine();
			}
			writer.flush();
		} catch (final IOException exception) {
			LOGGER.error("Could not write config file.", exception);
			throw new TeeFunRuntimeException("Could not write config file.", exception);
		}

		LOGGER.trace("Generated config file.");
		return path;
	}
}
