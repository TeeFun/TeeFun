/**
 *
 */
package com.teeworlds.teefun.controller.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * WebSocket controller.
 *
 * @author Rajh
 *
 */
@Controller
public class WebSocketController {

	@MessageMapping("/test")
	public void test(final String string) throws InterruptedException {
		Thread.sleep(5000);
		this.sendTest();
		Thread.sleep(5000);
		this.sendTest2();
	}

	@SendTo("/sendTest2")
	private String sendTest2() {
		return "Message2";
	}

	@SendTo("/sendTest2")
	private String sendTest() {
		return "Message1";
	}

}
