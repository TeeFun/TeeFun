package com.teefun.controller.player;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teefun.bean.Matchmaking;
import com.teefun.bean.UserContext;
import com.teefun.controller.AbstractController;
import com.teefun.exception.JsonErrorException;
import com.teefun.model.Queue;

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
	 */
	@RequestMapping(value = "/changeName", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void changeName(@RequestBody @Valid final String name, final BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new JsonErrorException("Invalid name", bindingResult);
		}

		this.userContext.getPlayer().setName(name);
		for (final Queue queue : this.matchmaking.getQueues(this.userContext.getPlayer())) {
			this.matchmaking.checkQueue(queue);
		}
	}

	/**
	 * Keep active a player.
	 *
	 * @return the view
	 */
	@RequestMapping(value = "/keepAlive", method = RequestMethod.GET)
	public void keepAlive() {
		this.userContext.getPlayer().keepAlive();
	}

}
