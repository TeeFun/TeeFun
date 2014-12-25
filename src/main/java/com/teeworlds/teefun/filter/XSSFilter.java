/**
 *
 */
package com.teeworlds.teefun.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.teeworlds.teefun.filter.bean.XSSRequestWrapper;

/**
 * XSS Filter.
 *
 * @author Rajh
 *
 */
@WebFilter("/*")
public class XSSFilter implements Filter {

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
	}

}