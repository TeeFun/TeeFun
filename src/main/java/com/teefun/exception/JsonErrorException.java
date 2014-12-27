/**
 *
 */
package com.teefun.exception;

import org.springframework.validation.Errors;

/**
 * JSon error exception.
 *
 * @author Rajh
 *
 */
public class JsonErrorException extends RuntimeException {

	/**
	 * SUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The errors.
	 */
	private final Errors errors;

	/**
	 * Default constructor.
	 *
	 * @param message the global message
	 * @param errors the errors
	 */
	public JsonErrorException(final String message, final Errors errors) {
		super(message);
		this.errors = errors;
	}

	/**
	 * @return the errors
	 */
	public Errors getErrors() {
		return this.errors;
	}
}