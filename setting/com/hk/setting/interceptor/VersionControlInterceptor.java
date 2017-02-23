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

public class VersionControlInterceptor extends BaseInterceptor implements HandlerInterceptor{
	
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
		if((request.getServletPath().equals("/setting/versionControl/version_control_search.html") || 
		  request.getServletPath().equals("/setting/versionControl/get_version_control_jqgrid.html")|| 

		  request.getServletPath().equals("/setting/versionControl/get_version_control_jqgrid_by_parent.html")) && 
		  accessUserService.getAccesModule(InterceptorDirectoryEnum.VERSION_CONTROL.getVal(),getId())){
			return true;
		}else if(request.getServletPath().equals("/setting/versionControl/version_control_add.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.VERSION_CONTROL.getVal(),getId()) && userService.getUserActive().getIsCreate()){
			return true;
		}else if(request.getServletPath().equals("/setting/versionControl/version_control_add_save.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.VERSION_CONTROL.getVal(),getId()) && userService.getUserActive().getIsCreate()){
			return true;
		}else if(request.getServletPath().equals("/setting/versionControl/version_control_detail_edit_save.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.VERSION_CONTROL.getVal(),getId()) && userService.getUserActive().getIsUpdate()){
			return true;
		}else if(request.getServletPath().equals("/setting/versionControl/version_control_edit.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.VERSION_CONTROL.getVal(),getId()) && userService.getUserActive().getIsUpdate()){
			return true;
		}else if(request.getServletPath().equals("/setting/versionControl/version_control_delete.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.VERSION_CONTROL.getVal(),getId()) && userService.getUserActive().getIsDelete()){
			return true;
		}else if(request.getServletPath().equals("/setting/versionControl/version_control_detail_add_save.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.VERSION_CONTROL.getVal(),getId()) && userService.getUserActive().getIsCreate()){
			return true;
		}else if(request.getServletPath().equals("/setting/versionControl/version_control_detail_delete.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.VERSION_CONTROL.getVal(),getId()) && userService.getUserActive().getIsDelete()){
			return true;
		}else if(request.getServletPath().equals("/setting/versionControl/get_version_control_list.html")){
			return true;
		}else{
			response.sendRedirect(mainProjectUrl+"/danied.html");
			return false;
		}
		
	}

}
