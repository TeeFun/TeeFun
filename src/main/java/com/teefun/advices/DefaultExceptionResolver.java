/**
 *
 */
package com.teefun.advices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Default controller advice.
 *
 * @author Rajh
 *
 */
@Component
public class DefaultExceptionResolver implements HandlerExceptionResolver {

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionResolver.class);

	@Override
	public ModelAndView resolveException(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) {
		LOGGER.error("Error in app : ", ex);
		return null;
	}

}
