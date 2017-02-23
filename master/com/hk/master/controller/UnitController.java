package com.hk.master.controller;


import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.hk.base.entity.Unit;
import com.hk.common.controller.AbstractController;
import com.hk.common.exception.ServiceException;
import com.hk.common.util.UserUtil;
import com.hk.master.mapper.UnitMapper;
import com.hk.master.service.UnitService;



@Controller
@RequestMapping("/master/unit")
@SessionAttributes(types = Unit.class, value = "unit")
public class UnitController extends AbstractController{
	
	@Autowired
    private UnitService unitService;
	
	@RequestMapping("/unit_search")
    public ModelAndView search() throws ServiceException{
		return new ModelAndView("master.unit_search",unitService.search());
    }
	
	@RequestMapping("/unit_add")
	@ModelAttribute("unit")
	public ModelAndView preadd(HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		return new ModelAndView("master.unit_add",unitService.preadd());
	}
	
	@RequestMapping(value="/unit_add_save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean add(HttpServletRequest request) throws ServiceException, IOException {
		Boolean a=unitService.add(UnitMapper.unitMapper(request));
		if(a==true){
			return true;
		}else{
			return false;
		}
		
	}
	
	@RequestMapping(value="/unit_edit_save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean edit(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		 Boolean result=unitService.edit(UnitMapper.unitMapper(request));
		 if(result){
	            return true;
	     }else{
	        	return false;
	     }
		
	}
	
	@RequestMapping(value="/unit_edit_is_active", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean editStatusDelete(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		 Boolean result=unitService.editIsActive(UnitMapper.unitMapper(request));
		 if(result==true){
	            return true;
	     }else{
	        	return false;
	     }
		
	}
	@RequestMapping("/unit_edit")
	public ModelAndView preedit(@RequestParam("id")String id) throws ServiceException{
		return new ModelAndView("master.unit_add", unitService.preedit(id));
	}
	
	
	@RequestMapping(value="/unit_delete", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean remove(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		if(unitService.deleteById(UnitMapper.unitMapper(request))){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	@RequestMapping(value = "/get_unit_list", 
			method = RequestMethod.GET, 
			headers="Accept=*/*")
	public @ResponseBody Map<String, Object> getUnitJsonList(@RequestParam("page") String page,@RequestParam("rows") String rows,
			@RequestParam("sidx") String sidx,@RequestParam("sord") String sord,@RequestParam(required=false,value="searchTerm") String query) {
		
		return unitService.getUnitJsonList(page, rows, sidx, sord, query);
	}
	
	@RequestMapping(value = "/get_unit_jqgrid", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Map<String, Object> getUnitJqgridList(@RequestParam("page") String page,@RequestParam("rows") String rows,
			@RequestParam("sidx") String sidx,@RequestParam("sord") String sord,@RequestParam(required=false,value="id") String id,@RequestParam(required=false,value="nama") String nama,@RequestParam(required=false,value="isActive") String isActive) throws ServiceException {
	
		return unitService.getUnitJqgridList(page, rows, sidx, sord, id,nama,isActive);
	}


}
