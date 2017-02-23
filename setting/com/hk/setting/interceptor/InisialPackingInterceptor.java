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

public class InisialPackingInterceptor extends BaseInterceptor implements HandlerInterceptor{
	
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
		if((request.getServletPath().equals("/setting/inisialPacking/inisial_packing_edit.html") || 
		  request.getServletPath().equals("/setting/inisialPacking/get_mata_uang_list.html")|| 
		  request.getServletPath().equals("/setting/inisialPacking/get_unit_list.html")|| 
		  request.getServletPath().equals("/setting/inisialPacking/get_akun_list.html")|| 
		  request.getServletPath().equals("/setting/inisialPacking/get_supplier_list.html")|| 
		  request.getServletPath().equals("/setting/inisialPacking/get_gudang_grup_list.html")|| 
		  request.getServletPath().equals("/setting/inisialPacking/get_gudang_list.html")|| 

		  request.getServletPath().equals("/setting/inisialPacking/get_kain_grade_list.html")) && 
		  accessUserService.getAccesModule(InterceptorDirectoryEnum.INISIAL_PACKING.getVal(),getId())){
			return true;
		}else if(request.getServletPath().equals("/setting/inisialPacking/inisial_edit_save.html") && accessUserService.getAccesModule(InterceptorDirectoryEnum.INISIAL_PACKING.getVal(),getId()) && userService.getUserActive().getIsUpdate()){
			return true;
		}else{
			response.sendRedirect(mainProjectUrl+"/danied.html");
			return false;
		}
		
	}

}
