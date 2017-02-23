package com.hk.common.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.RedirectView;


import com.hk.base.dao.ExceptionEventDAO;
import com.hk.base.entity.ExceptionEvent;
import com.hk.common.util.UrlUtil;


public class ExceptionResolver extends SimpleMappingExceptionResolver{
	
	@Autowired
	private ExceptionEventDAO exceptionEventDao;
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
		
		ExceptionEvent model = new ExceptionEvent();
		model.setMessage(ex.getMessage());
		model.setExceptionType(ex.getClass().getSimpleName());
		model.setDateTime(new Date());
		model.setCreateBy("System");
		model.setCreateDate(new Date());
		model.setLocation(handler == null ? "" : handler.getClass().getSimpleName());
		
		StringBuffer stackBuffer = new StringBuffer();
		for(StackTraceElement elem : ex.getStackTrace()){
			stackBuffer.append(elem + "\n");
		}
		model.setStackTrace(stackBuffer.toString());		

		exceptionEventDao.insert(model);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("urlback", UrlUtil.generateParamBased(request));
		map.put("exceptionMessage", ex.toString());
		
		return new ModelAndView(new RedirectView("/exception.html", true), map);
		
	}

}
