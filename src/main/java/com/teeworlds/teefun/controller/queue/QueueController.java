package com.teeworlds.teefun.controller.queue;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.teeworlds.teefun.bean.Matchmaking;
import com.teeworlds.teefun.bean.UserContext;
import com.teeworlds.teefun.controller.AbstractController;
import com.teeworlds.teefun.model.Player;
import com.teeworlds.teefun.model.Queue;

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
	@RequestMapping(value = "/home", method = RequestMethod.GET)
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
	@RequestMapping(value = "/joinQueue", method = RequestMethod.POST)
	public ModelAndView joinQueue(@RequestParam final String queueName) {
		final Player player = this.userContext.getPlayer();
		final Queue queue = this.matchmaking.getQueueByName(queueName);
		if (queue == null) {
			// TODO queue doesnt exist error
		}
		if (queue.isFull()) {
			// TODO "Queue is full"
		}
		if (queue.containsPlayer(player)) {
			// TODO "Queue already contains this player"
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
	@RequestMapping(value = "/quitQueue", method = RequestMethod.POST)
	public ModelAndView quitQueue(@RequestParam final String queueName) {
		final Player player = this.userContext.getPlayer();
		final Queue queue = this.matchmaking.getQueueByName(queueName);

		if (queue == null) {
			// TODO queue doesnt exist error
		}
		if (!queue.containsPlayer(player)) {
			// TODO "Player not in queue"
		}

		this.matchmaking.quitQueue(player, queue);
		return new ModelAndView("json/empty.json");
	}

	/**
	 * Quit all queues.
	 *
	 * @return the view
	 */
	@RequestMapping(value = "/quitAllQueues", method = RequestMethod.POST)
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
	@RequestMapping(value = "/createQueue", method = RequestMethod.POST)
	public ModelAndView createQueue(@RequestParam final String queueName, @RequestParam final Integer maxSize) {
		final Queue queue = new Queue(queueName, maxSize);

		if (this.matchmaking.getQueues().contains(queue)) {
			// TODO "Queue already exist";
		}

		this.matchmaking.addQueue(queue);
		return new ModelAndView("json/empty.json");
	}

	/**
	 * Delete a queue.
	 *
	 * @return the view
	 */
	@RequestMapping(value = "/deleteQueue", method = RequestMethod.POST)
	public ModelAndView deleteQueue(@RequestParam final String queueName) {
		final Queue queue = this.matchmaking.getQueueByName(queueName);

		if (queue == null) {
			// TODO "queue doesnt exist"
		}

		this.matchmaking.removeQueue(queue);
		return new ModelAndView("json/empty.json");
	}

}
