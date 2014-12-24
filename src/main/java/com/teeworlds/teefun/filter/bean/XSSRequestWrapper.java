package com.teeworlds.teefun.filter.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * XSSRequestWrapper with cleaned parameters.
 *
 * @author Rajh
 *
 */
public final class XSSRequestWrapper extends HttpServletRequestWrapper {

	public XSSRequestWrapper(final HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	@Override
	public String[] getParameterValues(final String parameter) {

		final String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		final int count = values.length;
		final String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = this.cleanXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(final String parameter) {
		final String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return this.cleanXSS(value);
	}

	@Override
	public String getHeader(final String name) {
		final String value = super.getHeader(name);
		if (value == null) {
			return null;
		}
		return this.cleanXSS(value);

	}

	/**
	 * Clean XSS.
	 *
	 * @param value the value to be cleaned
	 * @return the cleaned value
	 */
	private String cleanXSS(String value) {
		// You'll need to remove the spaces from the html entities below
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		return value;
	}
}