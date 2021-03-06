/**
 *
 */
package com.teefun.bean.usercontext.impl;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.teefun.bean.usercontext.UserContext;
import com.teefun.bean.usercontext.WebsocketUserContext;
import com.teefun.model.Player;

/**
 * Default impl for {@link UserContext}.
 *
 * @author Rajh
 *
 */
@Component
@Scope(value = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WebsocketUserContextImpl implements WebsocketUserContext, Serializable {

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
	private static final AtomicInteger PLAYER_COUNTER = new AtomicInteger();

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
		return DEFAULT_NAME + " " + String.valueOf(PLAYER_COUNTER.getAndAdd(1));
	}

	/**
	 * Getter for player.
	 */
	@Override
	public Player getPlayer() {
		return this.player;
	}

}
