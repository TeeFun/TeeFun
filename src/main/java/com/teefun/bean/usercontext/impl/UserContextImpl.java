/**
 *
 */
package com.teefun.bean.usercontext.impl;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.teefun.bean.usercontext.UserContext;
import com.teefun.model.Player;
import com.teefun.util.PlayerHolder;

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
		PlayerHolder.PLAYERS.add(this.player);
	}

	/**
	 * Remove player from active list.
	 */
	@PreDestroy
	public void destroyPlayer() {
		PlayerHolder.PLAYERS.remove(this.player);
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
