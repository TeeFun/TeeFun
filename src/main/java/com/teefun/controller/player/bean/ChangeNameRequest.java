/**
 *
 */
package com.teefun.controller.player.bean;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Change name request in json.
 *
 * @author Rajh
 *
 */
@JsonRootName("ChangeNameRequest")
public class ChangeNameRequest implements Serializable {

	/**
	 * SUID.
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty
	@NotBlank
	private String name;

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the {@link #name} to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

}
