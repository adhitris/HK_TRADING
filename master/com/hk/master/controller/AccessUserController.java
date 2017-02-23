package com.hk.master.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hk.base.entity.User;
import com.hk.common.controller.AbstractController;
import com.hk.common.exception.ServiceException;
import com.hk.common.util.UserUtil;
import com.hk.master.service.AccessUserService;

@Controller
@RequestMapping("/master/accessuser")
@SessionAttributes(value = "user", types = User.class)
public class AccessUserController extends AbstractController{
	
	@Autowired
	private AccessUserService service;
	
	@RequestMapping("/accessuser_search")
    public ModelAndView search() throws ServiceException{
		return new ModelAndView("master.accessuser_search",service.search());
    }
	
	@RequestMapping("/add_access_user")
	public ModelAndView preAddRoleAccessModule(@RequestParam("idUser") String idUser) {
		
		return new ModelAndView("master.add_access_user", service.preAddAccessUser(idUser));
		
	}
	
	@RequestMapping("/add_access_user_save")
	public ModelAndView add(@RequestParam(required = false, value ="userId") String userId, @RequestParam(required = false, value ="moduleList") String[] module){
		
		service.saveAccessModule(userId, module);
		return redirectTo("/master/accessuser/accessuser_search");
		
	}

	@RequestMapping(value = "/get_user_list", 
			method = RequestMethod.GET, 
			headers="Accept=*/*")
	public @ResponseBody Map<String, Object> getUserList(@RequestParam("page") String page,@RequestParam("rows") String rows,
			@RequestParam("sidx") String sidx,@RequestParam("sord") String sord,@RequestParam(required=false,value="searchTerm") String query,
			@RequestParam(required=false,value="idTarget") String idTarget) {

		
		
		return service.getUserList(page, rows, sidx, sord, query, idTarget);
	}
	
	@RequestMapping(value = "/get_user_jqgrid", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Map<String, Object> getUserJqgrid(@RequestParam("page") String page,@RequestParam("rows") String rows,
			@RequestParam("sidx") String sidx,@RequestParam("sord") String sord,
			@RequestParam(required=false,value="id") String id,
			@RequestParam(required=false,value="firstName") String nama,
			@RequestParam(required=false,value="module") String moduleId,
			@RequestParam(required=false,value="isCreate") String isCreate,
			@RequestParam(required=false,value="isDelete") String isDelete,
			@RequestParam(required=false,value="isUpdate") String isUpdate,
			@RequestParam(required=false,value="isPrint") String isPrint,
			@RequestParam(required=false,value="isCancel") String isCancel,
			@RequestParam(required=false,value="isReport") String isReport,
			@RequestParam(required=false,value="isConfirm") String isConfirm,
			@RequestParam(required=false,value="isUnconfirm") String isUnconfirm,
			@RequestParam(required=false,value="isSupervisor") String isSupervisor,
			@RequestParam(required=false,value="isSuperuser") String isSuperuser,
			@RequestParam(required=false,value="isReprint") String isReprint,
			@RequestParam(required=false,value="isActive") String isActive,
			@RequestParam(required=false,value="startDate") String startDate,
			@RequestParam(required=false,value="endDate") String endDate,
			@RequestParam(required=false,value="departmentId") String departmentId) throws ServiceException {
	
		return service.getUserJqgridList(page, rows, sidx, sord, id, nama, isCreate,isDelete,isUpdate,isPrint,isCancel,isReport,isSupervisor,isActive,startDate,endDate,moduleId,departmentId,isSuperuser,isConfirm,isUnconfirm,isReprint);
	}

	@RequestMapping(value="/copy", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean copy(HttpServletRequest request,@RequestParam(required=false,value="id") String id,
			@RequestParam(required=false,value="idCopy") String idCopy) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		 Boolean result=service.copyAccessModule(id,idCopy);
		 if(result==true){
	            return true;
	     }else{
	        	return false;
	     }
		
	}
	

	@RequestMapping(value = "/get_module_list",method = RequestMethod.GET,headers="Accept=*/*")
	public @ResponseBody Map<String, Object> getModuleList(@RequestParam("page") String page,@RequestParam("rows") String rows,
			@RequestParam("sidx") String sidx,@RequestParam("sord") String sord,@RequestParam(required=false,value="searchTerm") String query) {

		
		
		return service.getModuleList(page, rows, sidx, sord, query);
	}
	
}
