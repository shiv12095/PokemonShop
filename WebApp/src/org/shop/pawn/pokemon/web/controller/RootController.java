package org.shop.pawn.pokemon.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RootController {

	@RequestMapping(method = RequestMethod.GET)
	public String viewHomePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "Home";
	}

	@RequestMapping(path = "AboutUs", method = RequestMethod.GET)
	public String viewAboutUsPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "AboutUs";
	}
	
	@RequestMapping(path = "ContactUs", method = RequestMethod.GET)
	public String viewContactUsPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "ContactUs";
	}
}