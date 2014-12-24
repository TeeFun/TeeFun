package com.teeworlds.teefun.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.teeworlds.teefun.model.Player;

@Controller
@RequestMapping("/")
public class HomeController {

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	private final List<Player> players = new ArrayList<Player>();

	@Resource
	private Player currentPlayer;

	@RequestMapping("/home")
	public ModelAndView home(final Model model) {
		model.addAttribute("players", this.players);
		model.addAttribute("currentPlayer", this.currentPlayer);
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@RequestParam final String name, final HttpSession session) {
		final String escapedName = StringEscapeUtils.escapeHtml4(name);

		this.currentPlayer.setName(escapedName);
		if (!this.players.contains(this.currentPlayer)) {
			this.players.add(this.currentPlayer);
		}
		LOGGER.debug("Register " + escapedName);

		return new ModelAndView("redirect:/home.do");
	}

	@RequestMapping(value = "/joinQueue", method = RequestMethod.POST)
	public ModelAndView joinQueue(@RequestParam final String queue, final HttpSession session) {
		final String escapedQueue = StringEscapeUtils.escapeHtml4(queue);
		this.currentPlayer.addToQueue(escapedQueue);
		LOGGER.debug("Join queue " + queue);
		return new ModelAndView("redirect:/home.do");
	}

	@RequestMapping(value = "/removeQueue", method = RequestMethod.POST)
	public ModelAndView removeQueue(@RequestParam final String queue, final HttpSession session) {
		final String escapedQueue = StringEscapeUtils.escapeHtml4(queue);
		this.currentPlayer.removeFromQueue(escapedQueue);
		LOGGER.debug("Remove queue " + queue);
		return new ModelAndView("redirect:/home.do");
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ModelAndView remove(final HttpSession session) {
		if (!this.players.contains(this.currentPlayer)) {
			this.currentPlayer.reset();
			this.players.remove(this.currentPlayer);
		}
		LOGGER.debug("Removed " + this.currentPlayer.getName());
		return new ModelAndView("redirect:/home.do");
	}

	@RequestMapping(value = "/changeName", method = RequestMethod.POST)
	public ModelAndView changeName(@RequestParam final String name, final HttpSession session) {
		final String escapedName = StringEscapeUtils.escapeHtml4(name);
		this.currentPlayer.setName(escapedName);
		return new ModelAndView("redirect:/home.do");
	}

	@RequestMapping(value = "/keepAlive", method = RequestMethod.POST)
	@ResponseBody
	public String keepAlive(final HttpSession session) {
		this.currentPlayer.keepAlive();
		LOGGER.debug("KeepAlive " + this.currentPlayer.getName());
		return "";
	}

	@Scheduled(fixedRate = 15000)
	public void removeLeavers() {
		final List<Player> removeList = new ArrayList<Player>();
		for (final Player player : this.players) {
			if (System.currentTimeMillis() - player.getLastKeepAlive() > 15000) {
				removeList.add(player);
			}
		}
		final StringBuilder removes = new StringBuilder();
		for (final Player remove : removeList) {
			this.players.remove(remove);
			removes.append(remove.getName() + " | ");
		}
		LOGGER.debug("Cleaned " + removes);

	}
}