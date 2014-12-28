package com.teefun.controller.queue;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.teefun.bean.matchmaking.Matchmaking;
import com.teefun.bean.usercontext.UserContext;
import com.teefun.controller.AbstractController;
import com.teefun.controller.queue.bean.AskPasswordResponse;
import com.teefun.controller.queue.bean.CreateQueueRequest;
import com.teefun.controller.queue.bean.PlayerReadyRequest;
import com.teefun.exception.JsonErrorException;
import com.teefun.model.Player;
import com.teefun.model.Queue;
import com.teefun.model.QueueState;
import com.teefun.model.teeworlds.TeeworldsConfig;

/**
 * Queue controller.
 *
 * @author Rajh
 *
 */
@Controller
@RequestMapping("/queue")
public class QueueController extends AbstractController {

	/**
	 * Matchmaking system.
	 */
	@Resource
	private Matchmaking matchmaking;

	/**
	 * The user context.
	 */
	@Resource
	private UserContext userContext;

	/**
	 * Queue modification home page.
	 *
	 * @param model the model
	 * @return the view
	 */
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = { "/home", "/" }, method = RequestMethod.GET)
	public ModelAndView home(final Model model) {
		model.addAttribute("queues", this.matchmaking.getQueues());
		return new ModelAndView("queue");
	}

	/**
	 * Join a queue.
	 *
	 * @param queueId the queue id
	 * @param bindingResult the binding result
	 * @return an empty json
	 */
	@RequestMapping(value = "/joinQueue", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String joinQueue(@RequestBody @Valid final Integer queueId, final BindingResult bindingResult) {

		this.userContext.getPlayer().keepAlive();

		if (bindingResult.hasErrors()) {
			throw new JsonErrorException("Request validation failed", bindingResult);
		}

		final Player player = this.userContext.getPlayer();
		final Queue queue = this.matchmaking.getQueueById(queueId);

		if (queue == null) {
			throw new JsonErrorException("Queue does not exist", bindingResult);
		}
		if (queue.isFull()) {
			throw new JsonErrorException("Queue is full", bindingResult);
		}
		if (queue.containsPlayer(player)) {
			throw new JsonErrorException("Player already in queue", bindingResult);
		}
		if (queue.getState() != QueueState.WAITING_PLAYERS) {
			throw new JsonErrorException("Incorrect queue state", bindingResult);
		}

		this.matchmaking.joinQueue(player, queue);

		return EMPTY_JSON;
	}

	/**
	 * Quit a queue.
	 *
	 * @param queueId the queue id
	 * @param bindingResult the binding result
	 * @return an empty json
	 */
	@RequestMapping(value = "/quitQueue", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String quitQueue(@RequestBody @Valid final Integer queueId, final BindingResult bindingResult) {

		this.userContext.getPlayer().keepAlive();

		if (bindingResult.hasErrors()) {
			throw new JsonErrorException("Request validation failed", bindingResult);
		}

		final Player player = this.userContext.getPlayer();
		final Queue queue = this.matchmaking.getQueueById(queueId);

		if (queue == null) {
			throw new JsonErrorException("Queue does not exist", bindingResult);
		}
		if (!queue.containsPlayer(player)) {
			throw new JsonErrorException("Player is not in this queue", bindingResult);
		}
		if (queue.getState() != QueueState.WAITING_PLAYERS) {
			throw new JsonErrorException("Incorrect queue state", bindingResult);
		}

		this.matchmaking.quitQueue(player, queue);

		return EMPTY_JSON;
	}

	/**
	 * Quit all queues.
	 *
	 * @return an empty json
	 */
	@RequestMapping(value = "/quitAllQueues", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String quitAllQueues() {

		this.userContext.getPlayer().keepAlive();

		final Player player = this.userContext.getPlayer();
		this.matchmaking.quitAllQueues(player);

		return EMPTY_JSON;
	}

	/**
	 * Create a queue.
	 *
	 * @param createQueueRequest the request
	 * @param bindingResult the binding result
	 * @return an empty json
	 */
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/createQueue", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String createQueue(@RequestBody @Valid final CreateQueueRequest createQueueRequest, final BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new JsonErrorException("Request validation failed", bindingResult);
		}

		final Queue existQueue = this.matchmaking.getQueueByName(createQueueRequest.getName());
		if (existQueue != null) {
			throw new JsonErrorException("A queue with that name already exist", bindingResult);
		}

		final Queue queue = new Queue(createQueueRequest.getName(), createQueueRequest.getMaxSize(), createQueueRequest.getMap(), createQueueRequest.getGametype(), createQueueRequest.getScoreLimit(),
				createQueueRequest.getTimeLimit(), createQueueRequest.getPermanent());

		this.matchmaking.addQueue(queue);

		return EMPTY_JSON;
	}

	/**
	 * Delete a queue.
	 *
	 * @param queueId the queue id
	 * @param bindingResult the binding result
	 * @return an empty json
	 */
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/deleteQueue", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteQueue(@RequestBody @Valid final Integer queueId, final BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new JsonErrorException("Request validation failed", bindingResult);
		}

		final Queue queue = this.matchmaking.getQueueById(queueId);

		if (queue == null) {
			throw new JsonErrorException("Queue does not exist", bindingResult);
		}

		this.matchmaking.removeQueue(queue);

		return EMPTY_JSON;
	}

	/**
	 * Player is ready/notready.
	 *
	 * @param queueId the queue id
	 * @param bindingResult the binding result
	 * @return the password
	 */
	@RequestMapping(value = "/askPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AskPasswordResponse askPassword(@RequestBody @Valid final Integer queueId, final BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new JsonErrorException("Request validation failed", bindingResult);
		}

		final Queue queue = this.matchmaking.getQueueById(queueId);

		if (queue == null) {
			throw new JsonErrorException("Queue does not exist", bindingResult);
		}
		if (!queue.containsPlayer(this.userContext.getPlayer())) {
			throw new JsonErrorException("Player is not in this queue", bindingResult);
		}
		if (queue.getState() != QueueState.IN_GAME) {
			throw new JsonErrorException("Game not ready", bindingResult);
		}

		final TeeworldsConfig config = queue.getServer().getConfig();
		return new AskPasswordResponse(config.getVariableAsString("sv_name"), config.getVariableAsString("password"));
	}

	/**
	 * Player is ready/notready.
	 *
	 * @param playerReadyRequest the request
	 * @param bindingResult the binding result
	 * @return an empty json
	 */
	@RequestMapping(value = "/playerReady", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String playerReady(@RequestBody @Valid final PlayerReadyRequest playerReadyRequest, final BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new JsonErrorException("Request validation failed", bindingResult);
		}

		final Queue queue = this.matchmaking.getQueueById(playerReadyRequest.getQueueId());

		if (queue == null) {
			throw new JsonErrorException("Queue does not exist", bindingResult);
		}
		if (!queue.containsPlayer(this.userContext.getPlayer())) {
			throw new JsonErrorException("Player is not in this queue", bindingResult);
		}
		if (queue.getState() != QueueState.WAITING_READY) {
			throw new JsonErrorException("Incorrect queue state", bindingResult);
		}

		this.matchmaking.setPlayerReady(this.userContext.getPlayer(), queue, playerReadyRequest.getIsReady());

		return EMPTY_JSON;
	}

}
