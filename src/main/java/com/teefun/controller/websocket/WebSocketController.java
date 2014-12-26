/**
 *
 */
package com.teefun.controller.websocket;

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
		this.sendTest(string);
		Thread.sleep(5000);
		this.sendTest2(string);
	}

	@SendTo("/sendTest2")
	private String sendTest2(final String string) {
		return "Message2 " + string;
	}

	@SendTo("/sendTest1")
	private String sendTest(final String string) {
		return "Message1 " + string;
	}

}
