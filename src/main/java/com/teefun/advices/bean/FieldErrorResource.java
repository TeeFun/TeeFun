/**
 *
 */
package com.teefun.advices.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Field error resource (error on a field).
 *
 * @author Rajh
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldErrorResource {
	private String resource;
	private String field;
	private String code;
	private String message;

	public String getResource() {
		return this.resource;
	}

	public void setResource(final String resource) {
		this.resource = resource;
	}

	public String getField() {
		return this.field;
	}

	public void setField(final String field) {
		this.field = field;
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
}