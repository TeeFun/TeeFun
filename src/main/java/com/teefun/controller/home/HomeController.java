package com.teefun.controller.home;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.teefun.bean.matchmaking.Matchmaking;
import com.teefun.bean.usercontext.UserContext;
import com.teefun.controller.AbstractController;
import com.teefun.model.json.AppData;

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
	 * Test page.
	 *
	 * @param model the model
	 * @return the view
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test(final Model model) {
		model.addAttribute("queues", this.matchmaking.getQueues());
		return new ModelAndView("homeOld");
	}

	/**
	 * Test json.
	 */
	@RequestMapping(value = "/appData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AppData appData() {
		return new AppData(this.matchmaking.getQueues(), this.userContext.getPlayer());
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