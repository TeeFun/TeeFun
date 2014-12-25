package com.teeworlds.teefun.controller.player;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
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
	 * Change a player's name.
	 *
	 * @param name the new name
	 * @return the view
	 */
	@RequestMapping(value = "/changeName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView changeName(@RequestParam final String name) {
		this.userContext.getPlayer().setName(name);
		return new ModelAndView("json/empty.json");
	}

	/**
	 * Keep active a player.
	 *
	 * @return the view
	 */
	@RequestMapping(value = "/keepAlive", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView keepAlive() {
		this.userContext.getPlayer().keepAlive();
		return new ModelAndView(EMPTY_JSON_URL);
	}

}
