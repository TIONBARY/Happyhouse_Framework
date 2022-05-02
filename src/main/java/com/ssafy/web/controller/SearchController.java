package com.ssafy.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("search1")
public class SearchController {
	@GetMapping("")
	public ModelAndView search() throws Exception {
		return new ModelAndView("search");
	}
}
