/**
 *
 */
package com.teefun.controller.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketController.class);

	@MessageMapping("/teefun")
	public void teefun() throws Exception {
		LOGGER.debug("/teefun");
	}

	@MessageMapping("/websocket/teefun")
	public void wsteefun() throws Exception {
		LOGGER.debug("/websocket/teefun");
	}

	@MessageMapping("/test")
	public void test(final String string) throws InterruptedException {
		Thread.sleep(5000);
		this.sendTest(string);
		this.topicSendTest(string);
		Thread.sleep(5000);
		this.sendTest2(string);
		this.topicSendTest2(string);
		LOGGER.debug("/test");
	}

	@SendTo("/topic/sendTest2")
	private String topicSendTest2(final String string) {
		LOGGER.debug("/topic/sendTest2");
		return "Message2 " + string;
	}

	@SendTo("/topic/sendTest1")
	private String topicSendTest(final String string) {
		LOGGER.debug("/topic/sendTest1");
		return "Message1 " + string;
	}

	@SendTo("/sendTest2")
	private String sendTest2(final String string) {
		LOGGER.debug("/sendTest2");
		return "Message2 " + string;
	}

	@SendTo("/sendTest1")
	private String sendTest(final String string) {
		LOGGER.debug("/sendTest1");
		return "Message1 " + string;
	}

}
