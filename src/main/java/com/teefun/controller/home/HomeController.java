package com.teefun.controller.home;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.teefun.bean.Matchmaking;
import com.teefun.bean.UserContext;
import com.teefun.controller.AbstractController;

/**
 * Homepage controller.
 *
 * @author Rajh
 *
 */
@Controller
@RequestMapping("/")
public class HomeController extends AbstractController {

	/**
	 * User context.
	 */
	@Resource
	private UserContext userContext;

	/**
	 * Matchmaking system.
	 */
	@Resource
	private Matchmaking matchmaking;

	/**
	 * Home page.
	 *
	 * @param model the model
	 * @return the view
	 */
	@RequestMapping(value = { "/home", "/" }, method = RequestMethod.GET)
	public ModelAndView home(final Model model) {
		this.userContext.getPlayer().keepAlive();
		model.addAttribute("queues", this.matchmaking.getQueues());
		model.addAttribute("currentPlayer", this.userContext.getPlayer());
		model.addAttribute("isInQueue", this.matchmaking.isInQueue(this.userContext.getPlayer()));
		return new ModelAndView("home");
	}

	/**
	 * Refresh queues.
	 *
	 * @param model the model
	 * @return the view
	 */
	@RequestMapping(value = "/refreshQueues", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView refreshQueues(final Model model) {
		model.addAttribute("queues", this.matchmaking.getQueues());
		model.addAttribute("currentPlayer", this.userContext.getPlayer());
		model.addAttribute("isInQueue", this.matchmaking.isInQueue(this.userContext.getPlayer()));
		return new ModelAndView("/home/queues");
	}

}