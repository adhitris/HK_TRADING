package com.hk.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.hk.common.factory.FilterCriteriaFactory;


public abstract class AbstractController {
	
	@Autowired
	public FilterCriteriaFactory filterCriteriaFactory;

	private String defaultUrlExtension = ".html";
	
	public final ModelAndView redirectTo(String url){
		return new ModelAndView(new RedirectView(url + defaultUrlExtension, true));
	}
}
