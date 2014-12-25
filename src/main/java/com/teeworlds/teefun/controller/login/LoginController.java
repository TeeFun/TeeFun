/**
 *
 */
package com.teeworlds.teefun.controller.login;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.teeworlds.teefun.controller.AbstractController;

/**
 * Login controller
 *
 * @author Rajh
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends AbstractController {

	/**
	 * Authentication manager.
	 */
	@Resource
	private AuthenticationManager authenticationManager;

	/**
	 * Login homepage.
	 *
	 * @return the view
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("login");
	}

	/**
	 * Authenticate an user.
	 *
	 * @return the view
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView auth() {
		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("admin", "password");
		this.authenticationManager.authenticate(token);
		return null;
	}

	/**
	 * Authenticate an user.
	 *
	 * @return the view
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView logout() {
		return new ModelAndView("redirect:/home.do");
	}

}
