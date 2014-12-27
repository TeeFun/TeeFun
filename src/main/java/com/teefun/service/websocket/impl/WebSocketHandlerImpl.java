/**
 *
 */
package com.teefun.service.websocket.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.teefun.model.Queue;
import com.teefun.model.json.QueueData;
import com.teefun.service.websocket.WebSocketHandler;

/**
 * Default impl for {@link WebSocketHandler}.
 *
 * @author Rajh
 *
 */
@Service
public class WebSocketHandlerImpl implements WebSocketHandler {

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketHandlerImpl.class);

	/**
	 * Message template.
	 */
	@Resource
	private SimpMessagingTemplate template;

	@Override
	public void queueUpdated(final Queue queue) {
		LOGGER.debug("Queue updated event for : " + queue.getName());
		this.template.convertAndSend("/topic/queueUpdated", new QueueData());
	}

	@Override
	public void gameReady(final Queue queue) {
		LOGGER.debug("Game ready event for : " + queue.getName());
		this.template.convertAndSend("/topic/gameReady", new QueueData());
	}

	@Override
	public void gameStarted(final Queue queue) {
		LOGGER.debug("Game started event for : " + queue.getName());
		this.template.convertAndSend("/topic/gameStarted", new QueueData());
	}

	@Override
	public void gameAborted(final Queue queue) {
		LOGGER.debug("Game aborted event for : " + queue.getName());
		this.template.convertAndSend("/topic/gameAborted", new QueueData());
	}

}
