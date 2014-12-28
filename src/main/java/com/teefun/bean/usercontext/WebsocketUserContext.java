/**
 *
 */
package com.teefun.bean.usercontext;

import com.teefun.model.Player;

/**
 * User context associated to websockets.<br/>
 * This bean as SCOPE_SESSION. Each session will have its own instance.
 *
 * @author Rajh
 *
 */
public interface WebsocketUserContext {

	/**
	 * @return the player associated to this user
	 */
	Player getPlayer();
}
