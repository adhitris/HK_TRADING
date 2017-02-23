package com.hk.setting.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hk.common.interceptor.BaseInterceptor;
import com.hk.common.interceptor.InterceptorDirectoryEnum;
import com.hk.master.service.AccessUserService;
import com.hk.master.service.UserService;

public class RequestAccessMenuInterceptor extends BaseInterceptor implements HandlerInterceptor{
	
	@Autowired
    private AccessUserService accessUserService;
	
	@Autowired
    private UserService userService;

	@Value("${mainProjectUrl}")
	String mainProjectUrl;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {		
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object arg2) throws Exception {
		if((request.getServletPath().equals("/setting/requestAccessMenu/request_access_menu_search.html") || 

		  request.getServletPath().equals("/setting/requestAccessMenu/get_request_access_menu_jqgrid.html")) && 
		  accessUserService.getAccesModule(InterceptorDirectoryEnum.REQUEST_ACCESS_MENU.getVal(),getId())){
			return true;
		}else if(request.getServletPath().equals("/setting/requestAccessMenu/request_access_menu_add_save.html")){
			return true;
		}else if(request.getServletPath().equals("/setting/requestAccessMenu/request_access_menu_delete.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.REQUEST_ACCESS_MENU.getVal(),getId()) && userService.getUserActive().getIsDelete()){
			return true;
		}else if(request.getServletPath().equals("/setting/requestAccessMenu/request_access_menu_is_approve.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.REQUEST_ACCESS_MENU.getVal(),getId()) && userService.getUserActive().getIsSuperuser()){
			return true;
		}else if(request.getServletPath().equals("/setting/requestAccessMenu/request_access_menu_is_ignore.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.REQUEST_ACCESS_MENU.getVal(),getId()) && userService.getUserActive().getIsSuperuser()){
			return true;
		}else if(request.getServletPath().equals("/setting/requestAccessMenu/get_request_access_menu_pending_list.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.REQUEST_ACCESS_MENU.getVal(),getId()) && userService.getUserActive().getIsSuperuser()){
			return true;
		}else{
			response.sendRedirect(mainProjectUrl+"/index.html");
			return false;
		}
		
	}

}
