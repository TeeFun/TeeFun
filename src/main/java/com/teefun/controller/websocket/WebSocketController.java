/**
 *
 */
package com.teefun.controller.websocket;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.common.eventbus.EventBus;
import com.teefun.bean.matchmaking.Matchmaking;
import com.teefun.bean.usercontext.WebsocketUserContext;
import com.teefun.controller.AbstractController;
import com.teefun.controller.websocket.bean.PlayerReadyRequest;
import com.teefun.events.event.PlayerModifiedEvent;
import com.teefun.events.event.PlayerReadyEvent;
import com.teefun.exception.JsonErrorException;
import com.teefun.model.Player;
import com.teefun.model.Queue;
import com.teefun.model.QueueState;

/**
 * WebSocket controller.
 *
 * @author Rajh
 *
 */
@Controller
public class WebSocketController extends AbstractController {

	/**
	 * User context.
	 */
	@Resource
	private WebsocketUserContext wsUserContext;

	/**
	 * Matchmaking system.
	 */
	@Resource
	private Matchmaking matchmaking;

	/**
	 * Event bus.
	 */
	@Resource
	private EventBus eventBus;

	/**
	 * Keep active a player.
	 */
	@MessageMapping("/keepAlive")
	public void keepAlive() {
		this.wsUserContext.getPlayer().keepAlive();
	}

	/**
	 * Change a player's name.
	 *
	 * @param name the new name
	 */
	@MessageMapping(value = "/changeName")
	public void changeName(final String name) {

		this.wsUserContext.getPlayer().setName(name);
		this.eventBus.post(new PlayerModifiedEvent(this.wsUserContext.getPlayer()));
	}

	/**
	 * Join a queue.
	 *
	 * @param queueId the queue id
	 * @param bindingResult the binding result
	 */
	@MessageMapping(value = "/joinQueue")
	public void joinQueue(final Integer queueId) {

		if (queueId == null) {
			throw new JsonErrorException("Queue id is null");
		}

		final Player player = this.wsUserContext.getPlayer();
		final Queue queue = this.matchmaking.getQueueById(queueId);

		if (queue == null) {
			throw new JsonErrorException("Queue does not exist");
		}
		if (queue.isFull()) {
			throw new JsonErrorException("Queue is full");
		}
		if (queue.containsPlayer(player)) {
			throw new JsonErrorException("Player already in queue");
		}
		if (queue.getState() != QueueState.WAITING_PLAYERS) {
			throw new JsonErrorException("Incorrect queue state");
		}

		this.matchmaking.joinQueue(player, queue);
	}

	/**
	 * Quit a queue.
	 *
	 * @param queueId the queue id
	 * @param bindingResult the binding result
	 */
	@MessageMapping(value = "/quitQueue")
	public void quitQueue(final Integer queueId) {

		if (queueId == null) {
			throw new JsonErrorException("Queue id is null");
		}

		final Player player = this.wsUserContext.getPlayer();
		final Queue queue = this.matchmaking.getQueueById(queueId);

		if (queue == null) {
			throw new JsonErrorException("Queue does not exist");
		}
		if (queue.isFull()) {
			throw new JsonErrorException("Queue is full");
		}
		if (!queue.containsPlayer(player)) {
			throw new JsonErrorException("Player is not in this queue");
		}
		if (queue.getState() != QueueState.WAITING_PLAYERS) {
			throw new JsonErrorException("Incorrect queue state");
		}

		this.matchmaking.quitQueue(player, queue);
	}

	/**
	 * Quit all queues.
	 */
	@MessageMapping(value = "/quitAllQueues")
	public void quitAllQueues() {
		final Player player = this.wsUserContext.getPlayer();
		this.matchmaking.quitAllQueues(player);
	}

	/**
	 * Player is ready/notready.
	 *
	 * @param queueId the queue id
	 * @param bindingResult the binding result
	 * @return the password
	 */
	@MessageMapping(value = "/askPassword")
	public String askPassword(final Integer queueId) {

		if (queueId == null) {
			throw new JsonErrorException("Queue id is null");
		}

		final Queue queue = this.matchmaking.getQueueById(queueId);

		if (queue == null) {
			throw new JsonErrorException("Queue does not exist");
		}
		if (!queue.containsPlayer(this.wsUserContext.getPlayer())) {
			throw new JsonErrorException("Player is not in this queue");
		}
		if (queue.getState() != QueueState.IN_GAME) {
			throw new JsonErrorException("Game not ready");
		}

		return queue.getServer().getConfig().getVariableAsString("password");
	}

	/**
	 * Player is ready/notready.
	 *
	 * @param playerReadyRequest the request
	 * @param bindingResult the binding result
	 */
	@MessageMapping(value = "/playerReady")
	public void playerReady(@RequestBody @Valid final PlayerReadyRequest playerReadyRequest, final BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new JsonErrorException("Request validation failed", bindingResult);
		}

		final Queue queue = this.matchmaking.getQueueById(playerReadyRequest.getQueueId());

		if (queue == null) {
			throw new JsonErrorException("Queue does not exist", bindingResult);
		}
		if (!queue.containsPlayer(this.wsUserContext.getPlayer())) {
			throw new JsonErrorException("Player is not in this queue", bindingResult);
		}
		if (queue.getState() != QueueState.WAITING_READY) {
			throw new JsonErrorException("Incorrect queue state", bindingResult);
		}

		queue.setPlayerReady(this.wsUserContext.getPlayer(), playerReadyRequest.getReady());
		this.eventBus.post(new PlayerReadyEvent(this.wsUserContext.getPlayer(), queue));
	}

}
