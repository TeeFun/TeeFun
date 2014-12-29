package com.teefun.controller.player;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.eventbus.EventBus;
import com.teefun.bean.matchmaking.Matchmaking;
import com.teefun.bean.usercontext.UserContext;
import com.teefun.controller.AbstractController;
import com.teefun.controller.player.bean.ChangeNameRequest;
import com.teefun.events.event.PlayerModifiedEvent;
import com.teefun.exception.JsonErrorException;
import com.teefun.model.Player;
import com.teefun.util.PlayerHolder;

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
	 * Event bus.
	 */
	@Resource
	private EventBus eventBus;

	/**
	 * Application context.
	 */
	@Resource
	private ApplicationContext appContext;

	/**
	 * Change a player's name.
	 *
	 * @param name the new name
	 */
	@RequestMapping(value = "/changeName", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String changeName(@RequestBody @Valid final ChangeNameRequest changeNameRequest, final BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new JsonErrorException("Request validation failed", bindingResult);
		}

		final String name = changeNameRequest.getName();

		if (name == this.userContext.getPlayer().getName()) {
			throw new JsonErrorException("The new name is the same than the old one.", bindingResult);
		}

		for (final Player player : PlayerHolder.PLAYERS) {
			if (player.getName().equals(name)) {
				throw new JsonErrorException("Name already taken.", bindingResult);
			}
		}

		this.userContext.getPlayer().setName(name);
		this.eventBus.post(new PlayerModifiedEvent(this.userContext.getPlayer()));

		return EMPTY_JSON;
	}

	/**
	 * Keep active a player.
	 *
	 * @return the view
	 */
	@RequestMapping(value = "/keepAlive", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String keepAlive() {
		this.userContext.getPlayer().keepAlive();

		return EMPTY_JSON;
	}

}
