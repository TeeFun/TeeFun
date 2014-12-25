/**
 *
 */
package com.teeworlds.teefun.exception;

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
	 * @param string
	 */
	public TeeFunRuntimeException(final String message) {
		super(message);
	}

}
