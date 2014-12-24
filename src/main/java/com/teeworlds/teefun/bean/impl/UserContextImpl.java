/**
 *
 */
package com.teeworlds.teefun.bean.impl;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.teeworlds.teefun.bean.UserContext;
import com.teeworlds.teefun.model.Player;

/**
 * Default impl for {@link UserContext}.
 *
 * @author Rajh
 *
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserContextImpl implements UserContext, Serializable {

	/**
	 * SUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default player name.
	 */
	private static final String DEFAULT_NAME = "nameless tee";

	/**
	 * Player count.
	 */
	private static int playerCount = 0;

	/**
	 * The player.
	 */
	private Player player;

	/**
	 * Init bean.
	 */
	@PostConstruct
	public void initDefaultPlayer() {
		this.player = new Player(getUniqueName());
	}

	/**
	 * Look for a unique name for the player.
	 */
	public static String getUniqueName() {
		return DEFAULT_NAME + " " + String.valueOf(playerCount++);
	}

	/**
	 * Getter for player.
	 */
	@Override
	public Player getPlayer() {
		return this.player;
	}

}
