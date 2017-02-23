package com.hk.master.interceptor;

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

public class UnitInterceptor extends BaseInterceptor implements HandlerInterceptor{
	
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
		if((request.getServletPath().equals("/master/unit/unit_search.html") || 
		  request.getServletPath().equals("/master/unit/get_unit_list.html")|| 
		  request.getServletPath().equals("/master/unit/get_unit_jqgrid.html")) && 
		  accessUserService.getAccesModule(InterceptorDirectoryEnum.MASTER_UNIT.getVal(),getId())){
			return true;
		}else if(request.getServletPath().equals("/master/unit/unit_add.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.MASTER_UNIT.getVal(),getId()) && userService.getUserActive().getIsCreate()){
			return true;
		}else if(request.getServletPath().equals("/master/unit/unit_edit.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.MASTER_UNIT.getVal(),getId())  && userService.getUserActive().getIsUpdate()){
			return true;
		}else if(request.getServletPath().equals("/master/unit/unit_add_save.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.MASTER_UNIT.getVal(),getId()) && userService.getUserActive().getIsCreate()){
			return true;
		}else if(request.getServletPath().equals("/master/unit/unit_edit_save.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.MASTER_UNIT.getVal(),getId())  && userService.getUserActive().getIsUpdate()){
			return true;
		}else if(request.getServletPath().equals("/master/unit/unit_edit_is_active.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.MASTER_UNIT.getVal(),getId())  && userService.getUserActive().getIsSupervisor()){
			return true;
		}else if(request.getServletPath().equals("/master/unit/unit_delete.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.MASTER_UNIT.getVal(),getId())  && userService.getUserActive().getIsDelete()){
			return true;
		}else{
			response.sendRedirect(mainProjectUrl+"/danied.html");
			return false;
		}
		
	}

}
