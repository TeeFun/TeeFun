/**
 *
 */
package com.teefun.events.event;

import com.teefun.model.Player;

/**
 * A player has been modified event.
 *
 * @author Rajh
 *
 */
public class PlayerModifiedEvent {

	/**
	 * The player modified.
	 */
	private final Player player;

	/**
	 * Default constructor.
	 *
	 * @param player the player
	 */
	public PlayerModifiedEvent(final Player player) {
		this.player = player;
	}

	/**
	 * @return the {@link #player}
	 */
	public Player getPlayer() {
		return this.player;
	}

}
