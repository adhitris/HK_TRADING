package com.hk.master.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hk.base.dao.AccessUserDAO;
import com.hk.base.dao.ModuleDAO;
import com.hk.base.dao.UserDAO;
import com.hk.base.entity.AccessUser;
import com.hk.base.entity.Module;
import com.hk.base.entity.User;
import com.hk.base.entityLog.AuditLog;
import com.hk.common.enumeration.ActionEnum;
import com.hk.common.exception.CommonException;
import com.hk.common.exception.ServiceException;
import com.hk.common.service.AbstractService;
import com.hk.common.util.ComboGridUtil;
import com.hk.common.util.CommonUtil;
import com.hk.common.util.DateUtil;
import com.hk.common.util.UserUtil;


@Component
@Transactional(rollbackFor = Exception.class)
public class ModuleService extends AbstractService<Module> {
	
	@Autowired
	private ModuleDAO moduleDAO;
	
	@Autowired
	private AccessUserDAO accessUserDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	public Map<String, Object> getList(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listModule", moduleDAO.findAll());
		
		return map;
	}
	
	public Map<String, Object> preadd(){
		Map<String, Object> map = new HashMap<String, Object>();
		Module module = new Module();
		module.setIsActive(true);
		map.put("module",module);
		map.put("countVersionJs", DateUtil.nowVersion());
		return map;
	}
	
	@Transactional
	public Boolean add(Module module){
		module.setId(module.getId().toUpperCase());
		if(CommonUtil.isNotNullOrEmpty(module.getModuleId())){
			module.setModule(moduleDAO.findById(module.getModuleId()));
		}
		module.setCreateBy(UserUtil.getUsername());
		module.setCreateDate(DateUtil.now());
		module.setIsActive(true);
		if(moduleDAO.insert(module)){
			moduleDAO.inserAudiLog(getAuditLog(module, ActionEnum.Saved.getVal()));
			return true;
		}else{
			return false;
		}
	}
	
	public Map<String, Object> preedit(String id){
		Module module = moduleDAO.findById(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("module", module);
		map.put("countVersionJs", DateUtil.nowVersion());
		return map;
	}
	
	@Transactional
	public Boolean edit(Module module) throws CommonException{
		Module moduleUpdate=moduleDAO.findById(module.getId());
		moduleUpdate.setLastUpdateBy(UserUtil.getUsername());
		moduleUpdate.setLastUpdateDate(DateUtil.now());
		if(CommonUtil.isNotNullOrEmpty(module.getModuleId())){
			moduleUpdate.setModule(moduleDAO.findById(module.getModuleId()));
		}else{
			moduleUpdate.setModule(null);
		}
		moduleUpdate.setNama(module.getNama());
		moduleUpdate.setPath(module.getPath());
		moduleUpdate.setVersion(module.getVersion());
		moduleUpdate.setUrl(module.getUrl());
		moduleUpdate.setStatus(module.getStatus());
		moduleUpdate.setUrutan(module.getUrutan());
		if(moduleDAO.update(moduleUpdate)){
			moduleDAO.inserAudiLog(getAuditLog(module, ActionEnum.Updated.getVal()));
			return true;
		}else{
			return false;
		}
	}
	
	@Transactional
	public Map<String, Object> getModuleJsonList(String page, String rows, String sidx, String sord, String searchTerm){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Module> allModule = moduleDAO.comboGridFind("nama",sidx,sord,searchTerm);
		int totalRow = allModule.size();
		int totalPages = 0;
		int limit = Integer.parseInt(rows);
		int pageRequested = Integer.parseInt(page);
		
		if( totalRow > 0 ) {
			totalPages = (int) Math.ceil((double)totalRow/(double)limit);
		} else {
			totalPages = 0;
		}
		
		if (pageRequested > totalPages) pageRequested=totalPages;
		int rowStart = pageRequested*limit-limit;
		int rowEnd = limit;
		
		List<Object> list = new ArrayList<Object>();
		if(totalPages!=0){
			map.put("page", page);
			map.put("total", totalPages);
			map.put("records", totalRow);
			for(Module r : moduleDAO.comboGridFindLimitOffset("nama",rowStart, rowEnd, sidx, sord, searchTerm)){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", r.getId());
				map2.put("nama", r.getNama());
				
				list.add(map2);
			}
			map.put("rows", list);
			
		}else {
			map.put("page", page);
			map.put("total", totalPages);
			map.put("records", totalRow);
			for(Module r : allModule){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", r.getId());
				map2.put("nama", r.getNama());
				
				list.add(map2);
			}
			map.put("rows", list);
		}
		
		return map;
	}
	
	// Parent Module SEARCH CRITERIA
	@Transactional
	public Map<String, Object> getParentModuleJson(String page, String rows, String sidx, String sord, String searchTerm)throws ServiceException{
		Map<String, Object> map = new HashMap<String, Object>();
		
		String keyword = ComboGridUtil.checkKeyword(searchTerm);
		//add optional search criteria
		
		//must edit
		List<Module> allRow = moduleDAO.comboGridFindParent("nama",sidx,sord,keyword);
		
		Map<String, Integer> dataMapJSON = ComboGridUtil.dataForJSON(allRow.size(), page, rows);
		
		List<Object> list = new ArrayList<Object>();
		//must edit
		List<Module> rowContain = new ArrayList<Module>();
		
		if( dataMapJSON.get("total") != 0 ){
			//must edit
			rowContain = moduleDAO.comboGridFindParentLimitOffset("nama" ,dataMapJSON.get("rowStart"), dataMapJSON.get("rowEnd"), sidx, sord, keyword);
		}else {
			rowContain = allRow;
		}
		
		map.put("page", page);
		map.put("total", dataMapJSON.get("total"));
		map.put("records", dataMapJSON.get("records"));
		//must edit
		for(Module x : rowContain){
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("id", x.getId());
			map2.put("nama", x.getNama());
			if(CommonUtil.isNotNullOrEmpty(x.getModule())){
				map2.put("module.nama", x.getModule().getNama());
			}else{
				map2.put("module.nama", "-");
			}
			
			list.add(map2);
		}
		map.put("rows", list);
		
		return map;
	}
	
	@Transactional
	public Map<String, Object> getModuleJqgridList(String page, String rows, String sidx, String sord, String searchTerm1, String searchTerm2, String searchTerm3){
		Map<String, Object> map = new HashMap<String, Object>();
		if(searchTerm1 == null){
			searchTerm1 = "";
		}
		
		if(searchTerm2 == null){
			searchTerm2 = "";
		}
		
		Date searchTermCreateDate = null;
		if(searchTerm3 != null){
			searchTermCreateDate = DateUtil.toDate(searchTerm3);
		}
		
		List<Module> all = moduleDAO.jqgridFindAll(sidx,sord,searchTerm1,searchTerm2,searchTermCreateDate);
		int totalRow = all.size();
		int totalPages = 0;
		int limit = Integer.parseInt(rows);
		int pageRequested = Integer.parseInt(page);
		
		if( totalRow > 0 ) {
			totalPages = (int) Math.ceil((double)totalRow/(double)limit);
		} else {
			totalPages = 0;
		}
		
		if (pageRequested > totalPages) pageRequested=totalPages;
		int rowStart = pageRequested*limit-limit;
		int rowEnd = limit;
		
		List<Object> list = new ArrayList<Object>();
		if(totalPages!=0){
			map.put("page", page);
			map.put("total", totalPages);
			map.put("records", totalRow);
			for(Module x : moduleDAO.jqgridFindLimitOffset(rowStart, rowEnd, sidx, sord, searchTerm1, searchTerm2, searchTermCreateDate)){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", x.getId());
				map2.put("nama", x.getNama());
				if(x.getStatus().equalsIgnoreCase("1"))
				{
					map2.put("status", "child");
				}else{
					map2.put("status", "parent");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getModuleId())){
					map2.put("parent", x.getModule().getNama());
					map2.put("parentId", x.getModule().getId());
				}else{
					map2.put("parent", "");
					map2.put("parentId", "");
				}
				map2.put("path", x.getPath());
				map2.put("url", x.getUrl());
				map2.put("createBy", x.getCreateBy());
				map2.put("version", x.getVersion());
				if(CommonUtil.isNotNullOrEmpty(x.getUrutan())){
					map2.put("urutan", x.getUrutan());
				}else{
					map2.put("urutan", "");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getCreateDate())){
					map2.put("createDate", DateUtil.formatDateTime(x.getCreateDate()));
				}else{
					map2.put("createDate", "unknown");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getModuleId())){
					map2.put("module.nama", x.getModule().getNama());
					if(CommonUtil.isNotNullOrEmpty(x.getModule().getModuleId())){
						map2.put("module.module.nama", x.getModule().getModule().getNama());
					}else{
						map2.put("module.module.nama", "");
					}
				}else{
					map2.put("module.nama", "");
					map2.put("module.module.nama", "");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getIsActive())){
					if(x.getIsActive()){
						map2.put("isActive", "Y");
					}else{
						map2.put("isActive", "N");
					}
				}
				list.add(map2);
			}
			map.put("rows", list);
			
		}else {
			map.put("page", page);
			map.put("total", totalPages);
			map.put("records", totalRow);
			for(Module x : all){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", x.getId());
				map2.put("nama", x.getNama());
				map2.put("path", x.getPath());
				map2.put("url", x.getUrl());
				map2.put("createBy", x.getCreateBy());
				map2.put("version", x.getVersion());
				if(x.getStatus().equalsIgnoreCase("1"))
				{
					map2.put("status", "child");
				}else{
					map2.put("status", "parent");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getModuleId())){
					map2.put("parent", x.getModule().getNama());
					map2.put("parentId", x.getModule().getId());
				}else{
					map2.put("parent", "");
					map2.put("parentId", "");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getUrutan())){
					map2.put("urutan", x.getUrutan());
				}else{
					map2.put("urutan", "");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getCreateDate())){
					map2.put("createDate", DateUtil.formatDateTime(x.getCreateDate()));
				}else{
					map2.put("createDate", "unknown");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getModuleId())){
					map2.put("module.nama", x.getModule().getNama());
					if(CommonUtil.isNotNullOrEmpty(x.getModule().getModuleId())){
						map2.put("module.module.nama", x.getModule().getModule().getNama());
					}else{
						map2.put("module.module.nama", "");
					}
				}else{
					map2.put("module.nama", "");
					map2.put("module.module.nama", "");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getIsActive())){
					if(x.getIsActive()){
						map2.put("isActive", "Y");
					}else{
						map2.put("isActive", "N");
					}
				}
				list.add(map2);
			}
			map.put("rows", list);
		}
		
		return map;
	}
	
	public Boolean editIsActive(Module module) throws CommonException {
		Module moduleUpdate = moduleDAO.findById(module.getId());
		if (moduleUpdate.getIsActive()) {
			moduleUpdate.setIsActive(false);
			moduleUpdate.setVersion(module.getVersion());
			if(moduleDAO.update(moduleUpdate)){
				moduleDAO.inserAudiLog(getAuditLog(moduleUpdate, ActionEnum.NonActived.getVal()));
				return true;
			}else{
				return false;
			}
		} else {
			moduleUpdate.setIsActive(true);
			moduleUpdate.setVersion(module.getVersion());
			if(moduleDAO.update(moduleUpdate)){
				moduleDAO.inserAudiLog(getAuditLog(moduleUpdate, ActionEnum.Actived.getVal()));
				return true;
			}else{
				return false;
			}
		}
	
	}
	
	public AuditLog getAuditLog(Module obj,String action){
		
		AuditLog auditLog=new AuditLog();
		auditLog.setAction(action);
		auditLog.setCreatedDate(DateUtil.now());
		auditLog.setEntityId(obj.getId());
		auditLog.setEntityName(obj.getClass().toString());
		auditLog.setUsername(UserUtil.activeUser().getUsername());
		auditLog.setDetail("MODULE_ID : "+obj.getId());
		auditLog.setModule(obj.getClass().getSimpleName());
		return auditLog;
	}
	
	@Transactional
	public Boolean deleteById(Module module) throws CommonException{
		Module moduleUpdate=moduleDAO.findById(module.getId());
		if(moduleDAO.deleteById(module.getId())){
			moduleDAO.inserAudiLog(getAuditLog(moduleUpdate, ActionEnum.Deleted.getVal()));
			return true;
		}else{
			return false;
		}
		
	}

	@Transactional
	public Boolean forceDeleteById(Module module) throws CommonException {
		Module moduleUpdate=moduleDAO.findById(module.getId());
		accessUserDAO.deleteByIdModule(module.getId());
		if(moduleDAO.deleteById(module.getId())){
			moduleDAO.inserAudiLog(getAuditLog(moduleUpdate, ActionEnum.Deleted.getVal()));
			return true;
		}else{
			return false;
		}
	}

	public String getUserAccessUserByModuleList(String moduleId) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		List<Object> list = new ArrayList<Object>();
		
		for (User user : userDAO.findAll()) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("userId", user.getId());
			map2.put("check", accessUserDAO.findByModuleUser(moduleId, user.getId()));
			list.add(map2);
		}
		
		try {
			json = mapper.writeValueAsString(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return json;
	}

	public Boolean accessUserEditSave(String moduleId, String[] user) {
		Set<AccessUser> ams = new HashSet<AccessUser>();
		int countUser = 0;
		if(CommonUtil.isNotNullOrEmpty(user)){
			countUser = user.length;
		}
		Module moduleById = moduleDAO.findById(moduleId);
		accessUserDAO.deleteByIdModule(moduleId);
		if(null != user && CommonUtil.isNotNullOrEmpty(moduleId)){
			for(int a=0; a<countUser; a++){
				User u = userDAO.findById(user[a]);
				AccessUser am = new AccessUser();
				am.setModule(moduleById);
				am.setUser(u);
				ams.add(am);
			}
		}
		moduleById.getAccessUserSet().clear();
		moduleById.getAccessUserSet().addAll(ams);
		if(moduleDAO.merge(moduleById)){
			moduleDAO.inserAudiLog(getAuditLog(moduleById, ActionEnum.Updated.getVal()));
			return true;
		}else{
			return false;
		}
		
		
	}

}
