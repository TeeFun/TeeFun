/**
 *
 */
package com.teefun.util;

import java.util.ArrayList;
import java.util.List;

import com.teefun.model.Player;

/**
 * @author Rajh
 *
 */
public final class PlayerHolder {

	/**
	 * Private constructor for utility classes.
	 */
	private PlayerHolder() {

	}

	/**
	 * List of all players with active session.
	 */
	public static final List<Player> PLAYERS = new ArrayList<Player>();

}
