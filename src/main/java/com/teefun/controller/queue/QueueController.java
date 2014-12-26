package com.teefun.controller.queue;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.teefun.bean.Matchmaking;
import com.teefun.bean.UserContext;
import com.teefun.controller.AbstractController;
import com.teefun.model.Player;
import com.teefun.model.Queue;

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
	 * @param queue the queue name
	 * @return the view
	 */
	@RequestMapping(value = "/joinQueue", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView joinQueue(@RequestParam final String queueName) {
		final Player player = this.userContext.getPlayer();
		final Queue queue = this.matchmaking.getQueueByName(queueName);
		if (queue == null) {
			// TODO queue doesnt exist error
			return new ModelAndView("json/empty.json");
		}
		if (queue.isFull()) {
			// TODO "Queue is full"
			return new ModelAndView("json/empty.json");
		}
		if (queue.containsPlayer(player)) {
			// TODO "Queue already contains this player"
			return new ModelAndView("json/empty.json");
		}

		this.matchmaking.joinQueue(player, queue);

		return new ModelAndView("json/empty.json");
	}

	/**
	 * Quit a queue.
	 *
	 * @param queue the queue name
	 * @return the view
	 */
	@RequestMapping(value = "/quitQueue", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView quitQueue(@RequestParam final String queueName) {
		final Player player = this.userContext.getPlayer();
		final Queue queue = this.matchmaking.getQueueByName(queueName);

		if (queue == null) {
			// TODO queue doesnt exist error
			return new ModelAndView("json/empty.json");
		}
		if (!queue.containsPlayer(player)) {
			// TODO "Player not in queue"
			return new ModelAndView("json/empty.json");
		}

		this.matchmaking.quitQueue(player, queue);
		return new ModelAndView("json/empty.json");
	}

	/**
	 * Quit all queues.
	 *
	 * @return the view
	 */
	@RequestMapping(value = "/quitAllQueues", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView quitAllQueues() {
		final Player player = this.userContext.getPlayer();
		this.matchmaking.quitAllQueues(player);
		return new ModelAndView("json/empty.json");
	}

	/**
	 * Create a queue.
	 *
	 * @return the view
	 */
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/createQueue", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView createQueue(@RequestParam final String name, @RequestParam final Integer maxSize, @RequestParam final String map, @RequestParam final String gametype,
			@RequestParam final Integer scoreLimit, @RequestParam final Integer timeLimit) {
		final Queue queue = new Queue(name, maxSize, map, gametype, scoreLimit, timeLimit);

		if (this.matchmaking.getQueues().contains(queue)) {
			// TODO "Queue already exist";
			return new ModelAndView("json/empty.json");
		}

		this.matchmaking.addQueue(queue);
		return new ModelAndView("json/empty.json");
	}

	/**
	 * Delete a queue.
	 *
	 * @return the view
	 */
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/deleteQueue", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView deleteQueue(@RequestParam final String queueName) {
		final Queue queue = this.matchmaking.getQueueByName(queueName);

		if (queue == null) {
			// TODO "queue doesnt exist"
			return new ModelAndView("json/empty.json");
		}

		this.matchmaking.removeQueue(queue);
		return new ModelAndView("json/empty.json");
	}

}
