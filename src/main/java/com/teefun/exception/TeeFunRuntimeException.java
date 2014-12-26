/**
 *
 */
package com.teefun.exception;


/**
 * Default exception used in teefun objects.
 *
 * @author Rajh
 *
 */
public class TeeFunRuntimeException extends RuntimeException {

	/**
	 * SUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 *
	 * @param message the message
	 */
	public TeeFunRuntimeException(final String message) {
		super(message);
	}

	/**
	 * Default constructor with exception.
	 *
	 * @param message the message
	 * @param exception the exception
	 */
	public TeeFunRuntimeException(final String message, final Exception exception) {
		super(message, exception);
	}

}
