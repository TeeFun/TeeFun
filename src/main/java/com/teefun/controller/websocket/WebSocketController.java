/**
 *
 */
package com.teefun.controller.websocket;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.teefun.bean.UserContext;

/**
 * WebSocket controller.
 *
 * @author Rajh
 *
 */
@Controller
public class WebSocketController {

	/**
	 * User context.
	 */
	@Resource
	private UserContext userContext;

	/**
	 * Keep active a player. FIXME not working due to session bean
	 */
	@MessageMapping("/keepAlive")
	public void keepAlive() {
		this.userContext.getPlayer().keepAlive();
	}

}
