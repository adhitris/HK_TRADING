package com.hk.home.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.hk.base.entity.Module;
import com.hk.common.controller.AbstractController;
import com.hk.common.exception.ServiceException;
import com.hk.common.util.CommonUtil;
import com.hk.common.util.UserUtil;
import com.hk.home.service.IndexService;



@Controller
@SessionAttributes(types = Module.class, value = "menus")
public class IndexController extends AbstractController {
	
	@Autowired
    private IndexService indexService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage() throws ServiceException {
		if(CommonUtil.isNotNullOrEmpty(UserUtil.getUserActive())){
			return new ModelAndView(new RedirectView("index.html", true));
		}else{
			return new ModelAndView("home.login");
		}
		
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView getIndexPage() throws ServiceException {
		return new ModelAndView("home.index", indexService.search());
	}
	@RequestMapping(value = "/danied", method = RequestMethod.GET)
	public ModelAndView getDanied() {
		return new ModelAndView("home.danied");
	}
	
	
}
