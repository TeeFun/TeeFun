/**
 *
 */
package com.teefun.advices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.teefun.advices.bean.ErrorResource;
import com.teefun.advices.bean.FieldErrorResource;
import com.teefun.exception.JsonErrorException;

/**
 * Handle {@link JsonErrorException} and convert them to json.
 *
 * @author Rajh
 *
 */
@ControllerAdvice
public class JsonExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle json exceptions.
	 *
	 * @param exception the exception
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler({ JsonErrorException.class })
	protected ResponseEntity<Object> handleInvalidRequest(final RuntimeException exception, final WebRequest request) {
		final JsonErrorException ire = (JsonErrorException) exception;
		final List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

		final List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
		for (final FieldError fieldError : fieldErrors) {
			final FieldErrorResource fieldErrorResource = new FieldErrorResource();
			fieldErrorResource.setResource(fieldError.getObjectName());
			fieldErrorResource.setField(fieldError.getField());
			fieldErrorResource.setCode(fieldError.getCode());
			fieldErrorResource.setMessage(fieldError.getDefaultMessage());
			fieldErrorResources.add(fieldErrorResource);
		}

		final ErrorResource error = new ErrorResource("InvalidRequest", ire.getMessage());
		error.setFieldErrors(fieldErrorResources);

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return this.handleExceptionInternal(exception, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
	}
}