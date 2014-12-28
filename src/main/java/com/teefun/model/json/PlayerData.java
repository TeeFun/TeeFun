/**
 *
 */
package com.teefun.model.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.teefun.model.Player;

/**
 * Player data in json format
 *
 * @author Rajh
 *
 */
@JsonRootName("player")
public class PlayerData implements Serializable {

	/**
	 * SUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id.
	 */
	@JsonProperty
	private final Integer id;

	/**
	 * Name.
	 */
	@JsonProperty
	private final String name;

	/**
	 * Default constructor.
	 *
	 * @param player a player
	 */
	public PlayerData(final Player player) {
		this.id = player.getId();
		this.name = player.getName();
	}

}
