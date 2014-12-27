/**
 *
 */
package com.teefun.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Freemarker exception handler.
 *
 * @author Rajh
 *
 */
@WebFilter(value = "/*", asyncSupported = true)
public class FreemarkerErrorHandlerFilter implements Filter {

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FreemarkerErrorHandlerFilter.class);

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
		try {
			filterChain.doFilter(request, response);
		} catch (final Exception ex) {
			LOGGER.error("Error in freemarker.", ex);
			throw ex;
		}

	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
