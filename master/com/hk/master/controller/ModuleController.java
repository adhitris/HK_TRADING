package com.hk.master.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import com.hk.base.entity.Module;
import com.hk.common.controller.AbstractController;
import com.hk.common.exception.ServiceException;
import com.hk.common.util.CommonUtil;
import com.hk.common.util.UserUtil;
import com.hk.master.mapper.ModuleMapper;
import com.hk.master.service.ModuleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("/master/module")
@SessionAttributes(types = Module.class, value = "module")
public class ModuleController extends AbstractController{
	
	
	@Autowired
    private ModuleService moduleService;
	
	@RequestMapping("/module_search")
    public ModelAndView search() throws ServiceException{
		return new ModelAndView("master.module_search",moduleService.search());
    }
	
	@RequestMapping("/module_add")
	@ModelAttribute("module")
	public ModelAndView preadd(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("master.module_add",moduleService.preadd());
	}
	
	
	@RequestMapping("/module_edit")
	public ModelAndView preedit(@RequestParam("id")String id){
		return new ModelAndView("master.module_add", moduleService.preedit(id));
	}
	
	
	@RequestMapping(value = "/get_module_list", 
			method = RequestMethod.GET, 
			headers="Accept=*/*")
	public @ResponseBody Map<String, Object> getCountryList(@RequestParam("page") String page,@RequestParam("rows") String rows,
			@RequestParam("sidx") String sidx,@RequestParam("sord") String sord,@RequestParam(required=false,value="searchTerm") String query) {
		return moduleService.getModuleJsonList(page, rows, sidx, sord, query);
	}

	@RequestMapping(value="/module_add_save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean add(HttpServletRequest request) throws ServiceException, IOException {
		Boolean a=moduleService.add(ModuleMapper.moduleMapper(request));
		if(a==true){
			return true;
		}else{
			return false;
		}
		
	}
	
	@RequestMapping(value="/module_edit_save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean edit(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		 Boolean result=moduleService.edit(ModuleMapper.moduleMapper(request));
		 if(result){
	            return true;
	     }else{
	        	return false;
	     }
		
	}
	
	@RequestMapping(value="/module_edit_is_active", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean editStatusDelete(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		 Boolean result=moduleService.editIsActive(ModuleMapper.moduleMapper(request));
		 if(result==true){
	            return true;
	     }else{
	        	return false;
	     }
		
	}
	
	@RequestMapping(value="/module_delete", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean remove(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		if(moduleService.deleteById(ModuleMapper.moduleMapper(request))){
			return true;
		}else{
			return false;
		}
	}
	
	@RequestMapping(value="/module_force_delete", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean forceRemove(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		if(moduleService.forceDeleteById(ModuleMapper.moduleMapper(request))){
			return true;
		}else{
			return false;
		}
	}
	
	@RequestMapping(value = "/get_parentModule_list", 
			method = RequestMethod.GET, 
			headers="Accept=*/*")
	public @ResponseBody Map<String, Object> getParentModuleJson(@RequestParam("page") String page,@RequestParam("rows") String rows,
			@RequestParam("sidx") String sidx,@RequestParam("sord") String sord,@RequestParam(required = false, value="searchTerm") String searchTerm) throws ServiceException {
	
		return moduleService.getParentModuleJson(page, rows, sidx, sord, searchTerm);
	}
	
	@RequestMapping(value = "/get_module_jqgrid", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Map<String, Object> getModuleJqgridList(@RequestParam("page") String page,@RequestParam("rows") String rows,
			@RequestParam("sidx") String sidx,@RequestParam("sord") String sord,@RequestParam(required=false,value="searchTerm1") String query1,
			@RequestParam(required=false,value="searchTerm2") String query2,@RequestParam(required=false,value="searchTerm3") String query3) throws ServiceException {
	
		return moduleService.getModuleJqgridList(page, rows, sidx, sord, query1, query2, query3);
	}
	
	@RequestMapping(value = "/get_user_accessuser_by_module", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody String getUserAccessUserByModuleList(
			@RequestParam(required=false,value="moduleId") String moduleId) throws ServiceException {
	
		return moduleService.getUserAccessUserByModuleList(moduleId);
	}
	
	@RequestMapping(value="/access_user_edit_save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean accessUserEditSave(HttpServletRequest request) throws ServiceException, IOException {
		
		String jsonBody = IOUtils.toString(request.getInputStream());
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonBody);
		
		String moduleId = "";
		if(CommonUtil.isNotNullOrEmpty((String) jsonObject.get("moduleId"))){
			moduleId = (String) jsonObject.get("moduleId");
		}
		
		JSONArray userArray = (JSONArray) jsonObject.get("userArray");
		String[] user = Arrays.copyOf(userArray.toArray(), userArray.toArray().length, String[].class);
		
		Boolean a=moduleService.accessUserEditSave(moduleId,user);
		if(a==true){
			return true;
		}else{
			return false;
		}
	}
}
