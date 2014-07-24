package gov.azdoa.breaz.itesttools.mvc.controller;

/**
 * @author tim
 * June 30, 2013
 */


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	protected String displayLoginForm(HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		return new String("hello");
	}

}
