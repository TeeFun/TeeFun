/**
 *
 */
package com.teefun.model.teeworlds;

import java.util.HashMap;

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
	 * Variables hashmap.
	 */
	private HashMap<String, String> variables;

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
		this.password = "test";
		LOGGER.trace("Generated password : " + this.password);
		return this.password;
	}

	/**
	 * Generate the config file that this object represents.
	 */
	public Path generateConfigFile() {
		Path path = Paths.get("/tmp/server.cfg");
		try(BufferedWriter writer = Files.newBufferedWriter(path, Charset.defaultCharset())) {
			for(Entry<String, String> entry : map.entrySet()) {
				writer.append(String.format("%s %s", entry.getKey(), entry.getValue()));
				writer.newLine();
			}
			writer.flush();
		} catch(IOException exception) {
			System.out.println("Error writing to file");
		}

		LOGGER.trace("Generated config file.");
		return path;
	}
}
