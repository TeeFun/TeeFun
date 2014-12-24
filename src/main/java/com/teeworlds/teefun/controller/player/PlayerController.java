package com.teeworlds.teefun.controller.player;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.teeworlds.teefun.bean.Matchmaking;
import com.teeworlds.teefun.bean.UserContext;
import com.teeworlds.teefun.controller.AbstractController;

/**
 * Player controller.
 * 
 * @author Rajh
 * 
 */
@Controller
@RequestMapping("/player")
public class PlayerController extends AbstractController {

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
	 * Make this user active and save its name.
	 * 
	 * @param name the player name
	 * @return the view
	 */
	@RequestMapping(value = "/active", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ModelAndView active(@RequestParam final String name) {
		this.userContext.getPlayer().setName(name);
		this.matchmaking.addPlayer(this.userContext.getPlayer());
		return new ModelAndView("empty.json");
	}

	/**
	 * Make this user inactive.
	 * 
	 * @return the view
	 */
	@RequestMapping(value = "/inactive", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ModelAndView inactive() {
		this.matchmaking.removePlayer(this.userContext.getPlayer());
		return new ModelAndView("json/empty.json");
	}

	/**
	 * Change a player's name.
	 * 
	 * @param name the new name
	 * @return the view
	 */
	@RequestMapping(value = "/changeName", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ModelAndView changeName(@RequestParam final String name) {
		this.userContext.getPlayer().setName(name);
		return new ModelAndView("json/empty.json");
	}

	/**
	 * Keep active a player.
	 * 
	 * @return the view
	 */
	@RequestMapping(value = "/keepAlive", method = RequestMethod.GET)
	public ModelAndView keepAlive() {
		this.userContext.getPlayer().keepAlive();
		return new ModelAndView("json/empty.json");
	}

}
