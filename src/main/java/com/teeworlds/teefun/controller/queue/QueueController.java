package com.teeworlds.teefun.controller.queue;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
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
	 * Join a queue.
	 *
	 * @param queue the queue name
	 * @return the view
	 */
	@RequestMapping(value = "/joinQueue", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ModelAndView joinQueue(@RequestParam final String queueName) {
		final Player player = this.userContext.getPlayer();
		final Queue queue = this.matchmaking.getQueueByName(queueName);
		if (queue != null) {
			this.matchmaking.joinQueue(player, queue);
		}
		return new ModelAndView("json/empty.json");
	}

	/**
	 * Quit a queue.
	 *
	 * @param queue the queue name
	 * @return the view
	 */
	@RequestMapping(value = "/quitQueue", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ModelAndView quitQueue(@RequestParam final String queueName) {
		final Player player = this.userContext.getPlayer();
		final Queue queue = this.matchmaking.getQueueByName(queueName);
		if (queue != null) {
			this.matchmaking.quitQueue(player, queue);
		}
		return new ModelAndView("json/empty.json");
	}

	/**
	 * Quit all queues.
	 *
	 * @return the view
	 */
	@RequestMapping(value = "/quitAllQueues", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
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
	@RequestMapping(value = "/createQueue", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ModelAndView createQueue(@RequestParam final String queueName, @RequestParam final Integer maxSize) {
		this.matchmaking.addQueue(new Queue(queueName, maxSize));
		return new ModelAndView("json/empty.json");
	}

	/**
	 * Delete a queue.
	 *
	 * @return the view
	 */
	@RequestMapping(value = "/deleteQueue", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ModelAndView deleteQueue(@RequestParam final String queueName) {
		final Queue queue = this.matchmaking.getQueueByName(queueName);
		if (queue != null) {
			this.matchmaking.removeQueue(queue);
		}
		return new ModelAndView("json/empty.json");
	}

}
