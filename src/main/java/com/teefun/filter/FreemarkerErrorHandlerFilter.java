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

import freemarker.template.TemplateException;

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
			if (ex instanceof TemplateException) {
				LOGGER.error("Error in freemarker.", ex);
			} else {
				LOGGER.error("Error while processing request.", ex);
			}
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
