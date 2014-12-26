/**
 *
 */
package com.teefun.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handle error page.
 *
 * @author Rajh
 *
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

	/**
	 * Default error page.
	 *
	 * @return the view
	 */
	@RequestMapping("/default")
	public ModelAndView error() {
		return new ModelAndView("error");
	}

}
