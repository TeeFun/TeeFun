/**
 *
 */
package com.teefun.advices.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Global error resource.
 *
 * @author Rajh
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResource {
	private String code;
	private String message;
	private List<FieldErrorResource> fieldErrors;

	public ErrorResource() {
	}

	public ErrorResource(final String code, final String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public List<FieldErrorResource> getFieldErrors() {
		return this.fieldErrors;
	}

	public void setFieldErrors(final List<FieldErrorResource> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}