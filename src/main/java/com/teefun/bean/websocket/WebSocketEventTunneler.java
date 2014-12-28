/**
 *
 */
package com.teefun.bean.websocket;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.teefun.events.event.GameAbortedEvent;
import com.teefun.events.event.GameReadyEvent;
import com.teefun.events.event.GameStartedEvent;
import com.teefun.events.event.QueueCreatedEvent;
import com.teefun.events.event.QueueDeletedEvent;
import com.teefun.events.event.QueueModifiedEvent;
import com.teefun.service.websocket.WebSocketHandler;

/**
 * Listen to eventbus for event to be sent at websockets.
 *
 * @author Rajh
 *
 */
@Component
public class WebSocketEventTunneler {

	/**
	 * Websocket handler.
	 */
	@Resource
	private WebSocketHandler websocketHandler;

	/**
	 * Listen to {@link QueueModifiedEvent} and sent it through websocket.
	 *
	 * @param queueModifiedEvent the event
	 */
	@Subscribe
	public void onQueueModified(final QueueModifiedEvent queueModifiedEvent) {
		this.websocketHandler.queueUpdated(queueModifiedEvent.getQueue());
	}

	/**
	 * Listen to {@link QueueCreatedEvent} and sent it through websocket.
	 *
	 * @param queueCreatedEvent the event
	 */
	@Subscribe
	public void onQueueCreated(final QueueCreatedEvent queueCreatedEvent) {
		this.websocketHandler.queueCreated(queueCreatedEvent.getQueue());
	}

	/**
	 * Listen to {@link QueueDeletedEvent} and sent it through websocket.
	 *
	 * @param queueDeletedEvent the event
	 */
	@Subscribe
	public void onQueueDeleted(final QueueDeletedEvent queueDeletedEvent) {
		this.websocketHandler.queueDeleted(queueDeletedEvent.getQueue());
	}

	/**
	 * Listen to {@link GameReadyEvent} and sent it through websocket.
	 *
	 * @param gameReadyEvent the event
	 */
	@Subscribe
	public void onGameReady(final GameReadyEvent gameReadyEvent) {
		this.websocketHandler.gameReady(gameReadyEvent.getQueue());
	}

	/**
	 * Listen to {@link gameStartedEvent} and sent it through websocket.
	 *
	 * @param gameStartedEvent the event
	 */
	@Subscribe
	public void onGameStarted(final GameStartedEvent gameStartedEvent) {
		this.websocketHandler.gameStarted(gameStartedEvent.getQueue());
	}

	/**
	 * Listen to {@link GameAbortedEvent} and sent it through websocket.
	 *
	 * @param gameAbortedEvent the event
	 */
	@Subscribe
	public void onGameAborted(final GameAbortedEvent gameAbortedEvent) {
		this.websocketHandler.gameAborted(gameAbortedEvent.getQueue());
	}

}
