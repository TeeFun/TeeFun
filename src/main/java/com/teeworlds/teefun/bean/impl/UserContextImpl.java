/**
 *
 */
package com.teeworlds.teefun.bean.impl;

import java.io.Serializable;

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
	 * The player.
	 */
	private final Player player = new Player();

	@Override
	public Player getPlayer() {
		return this.player;
	}

}
