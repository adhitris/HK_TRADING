package com.hk.master.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hk.base.entity.User;
import com.hk.common.controller.AbstractController;
import com.hk.common.exception.CommonException;
import com.hk.common.exception.ServiceException;
import com.hk.common.util.UserUtil;
import com.hk.master.mapper.UserMapper;
import com.hk.master.service.UserService;

@Controller
@RequestMapping("/master/user")
@SessionAttributes(types = User.class, value = "user")
public class UserController extends AbstractController{
	
	@Autowired
    private UserService service;
	
	@RequestMapping("/user_search")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws CommonException{
		return new ModelAndView("master.list_user",service.getList());
    }
	
	@RequestMapping("/user_add")
	@ModelAttribute("user")
	public ModelAndView preadd(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("master.user_add",service.preadd());
	}
	
	@RequestMapping(value="/user_add_save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean add(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		Boolean result=service.add(UserMapper.userMapper(request));
		if(result){
            return true;
        }else{
        	return false;
        }
	}
	
	@RequestMapping("/user_edit")	
	public ModelAndView preedit(@RequestParam("id")String id){
		return new ModelAndView("master.user_add", service.preedit(id));
	}
	
	@RequestMapping(value="/user_edit_save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean edit(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		 Boolean result=service.edit(UserMapper.userMapper(request));
		 if(result==true){
	            return true;
	     }else{
	        	return false;
	     }
		
	}
	
	@RequestMapping("/user_pass_otorisasi_search")
    public ModelAndView passOtorisasiSearch(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("master.user_pass_otorisasi_search",service.getList());
    }
	
	@RequestMapping("/user_pass_otorisasi_edit")	
	public ModelAndView preEditPassOtorisasi(@RequestParam("id")String id){
		return new ModelAndView("master.user_pass_otorisasi_edit", service.preedit(id));
	}
	
	@RequestMapping(value="/user_pass_otorisasi_edit_save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean editPassOtorisasi(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		 Boolean result=service.editPasswordOtorisasi(UserMapper.userMapperUbahPassOtorisasi(request));
		 if(result==true){
	            return true;
	     }else{
	        	return false;
	     }
		
	}
	
	@RequestMapping("/user_pass_login_edit")	
	public ModelAndView preEditPassLogin(@RequestParam("id")String id){
		return new ModelAndView("master.user_pass_login_edit", service.preedit(id));
	}
	
	@RequestMapping(value="/user_pass_login_edit_save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean editPassLoginByAdmin(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		 Boolean result=service.editPasswordLoginByAdmin(UserMapper.userMapperUbahPassLogin(request));
		 if(result==true){
	            return true;
	     }else{
	        	return false;
	     }
		
	}
	
	@RequestMapping(value="/user_edit_is_active", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean editStatusDelete(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		 Boolean result=service.editIsActive(UserMapper.userMapper(request));
		 if(result==true){
	            return true;
	     }else{
	        	return false;
	     }
		
	}
	
	@RequestMapping(value="/user_delete", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean remove(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		String id=UserMapper.userMapperGetId(request);
		Boolean userBooleanBoolean=service.deleteById(id);
		if(userBooleanBoolean==true){
			return true;
		}else{
			return false;
		}
	}
	
	@RequestMapping("/change_password")
	public ModelAndView preChangePassword(){
		return new ModelAndView("master.changePassword",service.preChangePassword());
	}
	
	@RequestMapping(value="/change_password_save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean editPassLoginByUser(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		 Boolean result=service.editPasswordLoginByUser(UserMapper.userMapperUbahPassLogin(request));
		 if(result==true){
	            return true;
	     }else{
	        	return false;
	     }
		
	}

	
	@RequestMapping(value = "/get_user_list", 
			method = RequestMethod.GET, 
			headers="Accept=*/*")
	public @ResponseBody Map<String, Object> getCountryList(@RequestParam("page") String page,@RequestParam("rows") String rows,
			@RequestParam("sidx") String sidx,@RequestParam("sord") String sord,@RequestParam(required=false,value="searchTerm") String query) {

		
		
		return service.getJsonList(page, rows, sidx, sord, query);
	}
	
	
	@RequestMapping(value="/generate_print_report", method=RequestMethod.GET)
	@ResponseBody
	public Boolean report(HttpServletRequest request){
		Boolean result=service.generetaPrintReport();
		if(result){
			return result;
		}else{
			return result;
		}
	}
	
	@RequestMapping(value = "/get_user_jqgrid", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Map<String, Object> getUserJqgridList(@RequestParam("page") String page,@RequestParam("rows") String rows,
			@RequestParam("sidx") String sidx,@RequestParam("sord") String sord,
			@RequestParam(required=false,value="id") String id,
			@RequestParam(required=false,value="nama") String nama,
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
	
		return service.getUserJqgridList(page, rows, sidx, sord, id, nama, isCreate,isDelete,isUpdate,isPrint,isCancel,isReport,isSupervisor,isActive,startDate,endDate,departmentId,isSuperuser,isConfirm,isUnconfirm,isReprint);
	}
	
	@RequestMapping(value = "/check_old_password", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Boolean checkOldPassword(
			@RequestParam("password") String password,
			@RequestParam("userId") String userId) throws ServiceException {
	
		return service.checkOldPassword(password,userId);
	}
	
	
}
