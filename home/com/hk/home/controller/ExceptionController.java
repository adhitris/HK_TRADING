package com.hk.home.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hk.common.controller.AbstractController;

@Controller
public class ExceptionController extends AbstractController {

	@RequestMapping("/exception")
	public ModelAndView index(HttpServletRequest request) throws Throwable {
		return new ModelAndView("common.error");
	}
	
}
