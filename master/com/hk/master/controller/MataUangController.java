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
import com.hk.base.entity.MataUang;
import com.hk.common.controller.AbstractController;
import com.hk.common.exception.ServiceException;
import com.hk.common.util.UserUtil;
import com.hk.master.mapper.MataUangMapper;
import com.hk.master.service.MataUangService;



@Controller
@RequestMapping("/master/mataUang")
@SessionAttributes(types = MataUang.class, value = "mataUang")
public class MataUangController extends AbstractController{
	
	@Autowired
    private MataUangService mataUangService;
	
	@RequestMapping("/mata_uang_search")
    public ModelAndView search() throws ServiceException{
		return new ModelAndView("master.mata_uang_search",mataUangService.search());
    }
	
	@RequestMapping("/mata_uang_add")
	@ModelAttribute("mataUang")
	public ModelAndView preadd(HttpServletRequest request, HttpServletResponse response) throws ServiceException{
		return new ModelAndView("master.mata_uang_add",mataUangService.preadd());
	}
	
	@RequestMapping(value="/mata_uang_add_save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean add(HttpServletRequest request) throws ServiceException, IOException {
		Boolean a=mataUangService.add(MataUangMapper.mataUangMapper(request));
		if(a==true){
			return true;
		}else{
			return false;
		}
		
	}
	
	@RequestMapping(value="/mata_uang_edit_save", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean edit(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		 Boolean result=mataUangService.edit(MataUangMapper.mataUangMapper(request));
		 if(result){
	            return true;
	     }else{
	        	return false;
	     }
		
	}
	
	@RequestMapping(value="/mata_uang_edit_is_active", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean editStatusDelete(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		 Boolean result=mataUangService.editIsActive(MataUangMapper.mataUangMapper(request));
		 if(result==true){
	            return true;
	     }else{
	        	return false;
	     }
		
	}
	@RequestMapping("/mata_uang_edit")
	public ModelAndView preedit(@RequestParam("id")String id) throws ServiceException{
		return new ModelAndView("master.mata_uang_add", mataUangService.preedit(id));
	}
	
	
	@RequestMapping(value="/mata_uang_delete", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean remove(HttpServletRequest request) throws ServiceException , JsonParseException , JsonMappingException, IOException{
		if(mataUangService.deleteById(MataUangMapper.mataUangMapper(request))){
			return true;
		}else{
			return false;
		}
	}
	
	@RequestMapping(value = "/get_mata_uang_list", 
			method = RequestMethod.GET, 
			headers="Accept=*/*")
	public @ResponseBody Map<String, Object> getMataUangJsonList(@RequestParam("page") String page,@RequestParam("rows") String rows,
			@RequestParam("sidx") String sidx,@RequestParam("sord") String sord,@RequestParam(required=false,value="searchTerm") String query) {

		
		
		return mataUangService.getMataUangJsonList(page, rows, sidx, sord, query);
	}
	
	@RequestMapping(value = "/get_mata_uang_jqgrid", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody Map<String, Object> getMataUangJqgridList(@RequestParam("page") String page,@RequestParam("rows") String rows,
			@RequestParam("sidx") String sidx,@RequestParam("sord") String sord,@RequestParam(required=false,value="id") String id,@RequestParam(required=false,value="isActive") String isActive) throws ServiceException {
	
		return mataUangService.getMataUangJqgridList(page, rows, sidx, sord, id,isActive);
	}


}
