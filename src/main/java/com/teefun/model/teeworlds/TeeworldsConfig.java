/**
 *
 */
package com.teefun.model.teeworlds;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teefun.exception.TeeFunRuntimeException;
import com.teefun.util.TeeworldsConfigUtil;

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
		this.variables.put(name, value);
	}

	/**
	 * Sets a variable to an integer value.
	 */
	public void setVariable(final String name, final int value) {
		this.variables.put(name, String.format("%d", value));
	}

	/**
	 * Gets a variable as a string value.
	 */
	public String getVariableAsString(final String name) {
		return this.variables.get(name);
	}

	/**
	 * Generate a random password.
	 */
	public String generatePassword() {
		final String password = TeeworldsConfigUtil.getRandomString(5);
		LOGGER.trace("Generated password : " + password);
		this.setVariable("password", password);
		return password;
	}

	/**
	 * Generate the config file that this object represents.
	 *
	 * @param filepath the filepath
	 */
	public void generateConfigFile(final Path filepath) {
		try {
			final BufferedWriter writer = Files.newBufferedWriter(filepath, Charset.defaultCharset());
			for (final Entry<String, String> entry : this.variables.entrySet()) {
				writer.append(String.format("%s \"%s\"", entry.getKey(), entry.getValue()));
				writer.newLine();
			}
			writer.flush();
		} catch (final IOException exception) {
			LOGGER.error("Could not write config file.", exception);
			throw new TeeFunRuntimeException("Could not write config file.", exception);
		}

		LOGGER.trace("Generated config file.");
	}
}
