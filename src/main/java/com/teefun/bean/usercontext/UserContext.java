/**
 *
 */
package com.teefun.bean.usercontext;

import com.teefun.model.Player;

/**
 * User context stored into session.<br/>
 * This bean as SCOPE_SESSION. Each session will have its own instance.
 *
 * @author Rajh
 *
 */
public interface UserContext {

	/**
	 * @return the player associated to this user
	 */
	Player getPlayer();

}
