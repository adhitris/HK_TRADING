package com.hk.master.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.hk.common.service.AbstractService;
import com.hk.common.util.ComboGridUtil;
import com.hk.common.util.CommonUtil;
import com.hk.common.util.DateUtil;
import com.hk.common.util.UserUtil;
import com.hk.master.dto.LevelFourMenuDTO;
import com.hk.master.dto.LevelOneMenuDTO;
import com.hk.master.dto.LevelThreeMenuDTO;
import com.hk.master.dto.LevelTwoMenuDTO;


@Component
@Transactional(rollbackFor = Exception.class)
public class AccessUserService extends AbstractService<AccessUser> {

	@Autowired
	private AccessUserDAO accessUserDAO;
	
	@Autowired
	private ModuleDAO moduleDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	
	@SuppressWarnings("unused")
	public Map<String, Object> preAddAccessUser(String idUser){
		User user = userDAO.findById(idUser);
		Map<String, Object> map = new HashMap<String, Object>();
		List<LevelOneMenuDTO> levelOneMenuList = new ArrayList<LevelOneMenuDTO>();
		String menuSelected = "";
		String menuUnselected = "";
		
		for(Module m1 : moduleDAO.ParentNode()){
			String idChild1=""; String idChild2="";
			int loop=0;
			LevelOneMenuDTO levelOneMenuDTO = new LevelOneMenuDTO();
			boolean selected1 = false;
			if(CommonUtil.isNotNullOrEmpty(user.getAccessUserSet())){
				for(AccessUser am : user.getAccessUserSet()){
					if(am.getModule().getId().equalsIgnoreCase(m1.getId())){
						selected1 = true;
						menuSelected+="LevelOne"+m1.getId()+";";
						loop++;
						break;
					}
				}
			}
			
			if(loop == 0)menuUnselected+="LevelOne"+m1.getId()+";";
			levelOneMenuDTO.setId("LevelOne"+m1.getId());
			levelOneMenuDTO.setModule(m1);
			levelOneMenuDTO.setSelected(selected1);
			
			// LEVEL 2
			List<LevelTwoMenuDTO> levelTwoMenuDTOList = new ArrayList<LevelTwoMenuDTO>();
			List<Module> listModuleLevel2 =moduleDAO.childNode(m1.getId());
			if(!CommonUtil.isNullOrEmpty(listModuleLevel2)){
			for(Module m2 : listModuleLevel2){
				int loop2=0;
				AccessUser am2 = null;
				LevelTwoMenuDTO levelTwoMenuDTO = new LevelTwoMenuDTO();
				boolean selected2 = false;
				for(AccessUser am : user.getAccessUserSet()){
					if(am.getModule().getId().equalsIgnoreCase(m2.getId())){
						am2 = am;
						selected2 = true;
						break;
					}
				}
				
				String haveChild2 = "no";
				
				// LEVEL 3
				List<LevelThreeMenuDTO> LevelThreeMenuDTOList = new ArrayList<LevelThreeMenuDTO>();
				if(!CommonUtil.isNullOrEmpty(m2.getModule())){
					haveChild2 = "yes";
					for(Module m3 : moduleDAO.childNode(m2.getId())){
						int loop3=0;
						LevelThreeMenuDTO levelThreeMenuDTO = new LevelThreeMenuDTO();
						boolean selected3 = false;
						for(AccessUser am : user.getAccessUserSet()){
							if(am.getModule().getId().equalsIgnoreCase( m3.getId())){
								selected3 = true;
								menuSelected+="LevelTwo"+m2.getId()+";";
								loop2++;
								break;
							}
						}
						
						// LEVEL 4
						String haveChild3 = "no";
						List<LevelFourMenuDTO> LevelFourMenuDTOList = new ArrayList<LevelFourMenuDTO>();
						if(!CommonUtil.isNullOrEmpty(m3.getModule())){
							for(Module m4 : moduleDAO.childNode(m3.getId())){
								LevelFourMenuDTO levelFourMenuDTO = new LevelFourMenuDTO();
								boolean selected4 = false;
								for(AccessUser am : user.getAccessUserSet()){
									if(am.getModule().getId().equalsIgnoreCase( m4.getId())){
										selected4 = true;
										menuSelected+="LevelThree"+m3.getId()+";";
										loop3++;
										break;
									}
								}
								idChild1="";
								levelFourMenuDTO.setId("LevelFour"+m4.getId());
								levelFourMenuDTO.setIdParent("LevelOne"+m1.getId()+";"+"LevelTwo"+m2.getId()+";"+"LevelThree"+m3.getId());
								idChild1 += "LevelTwo"+m2.getId()+";"+"LevelThree"+m3.getId();
								levelFourMenuDTO.setModule(m4);
								levelFourMenuDTO.setSelected(selected4);
								//levelFourMenuDTO.setIdParent("LevelThree"+m3.getId());
								LevelFourMenuDTOList.add(levelFourMenuDTO);
								
							}
						}//END OF LEVEL 4
						
						idChild2="";
						levelThreeMenuDTO.setId("LevelThree"+m3.getId());
						levelThreeMenuDTO.setIdParent("LevelOne"+m1.getId()+";"+"LevelTwo"+m2.getId()+";");
						idChild2 += "LevelTwo"+m2.getId()+"LevelThree"+m3.getId();
						levelThreeMenuDTO.setModule(m3);
						levelThreeMenuDTO.setSelected(selected3);
						//levelThreeMenuDTO.setIdParent("LevelTwo"+m2.getId());
						levelThreeMenuDTO.setLevelFourMenuDTO(LevelFourMenuDTOList);
						LevelThreeMenuDTOList.add(levelThreeMenuDTO);
					}
				}//END OF LEVEL 3
				
				if(CommonUtil.isNotNullOrEmpty(am2)){
				/*	levelTwoMenuDTO.setDoCreate(am2.getDoCreate());
					levelTwoMenuDTO.setDoUpdate(am2.getDoUpdate());*/
				}else{
				/*	levelTwoMenuDTO.setDoCreate(false);
					levelTwoMenuDTO.setDoUpdate(false);*/
				}
				levelTwoMenuDTO.setIdChild(idChild2);
				levelTwoMenuDTO.setId("LevelTwo"+m2.getId());
				levelTwoMenuDTO.setIdParent("LevelOne"+m1.getId()+";");
				levelTwoMenuDTO.setModule(m2);
				levelTwoMenuDTO.setSelected(selected2);
				//levelTwoMenuDTO.setIdParent("LevelOne"+m1.getId());
				levelTwoMenuDTO.setHaveChild(haveChild2);
				levelTwoMenuDTO.setLevelThreeMenuDTO(LevelThreeMenuDTOList);
				levelTwoMenuDTOList.add(levelTwoMenuDTO);
				
			}
			}//END OF LEVEL 2
			
			levelOneMenuDTO.setIdChild(idChild1);
			levelOneMenuDTO.setLevelTwoMenuDTO(levelTwoMenuDTOList);
			levelOneMenuList.add(levelOneMenuDTO);
			
		}
		
		map.put("user", user);
		map.put("accessModuleList", levelOneMenuList);
		map.put("menuSelected", menuSelected);
		map.put("menuUnselected", menuUnselected);
		
		/*Map<Module, Boolean> mapAccessModule = new HashMap<Module, Boolean>();
		for(Module m : moduleDAO.findAll()){
			m.setModule(null);
			boolean selected = false;
			for(AccessModule am : role.getAccessModuleSet()){
				if(am.getModule().getId() == m.getId()){
					selected = true;
					break;
				}
			}
			
			mapAccessModule.put(m, selected);
		}
		
		map.put("role", role);
		map.put("accessModuleList", mapAccessModule);*/
		map.put("countVersionJs", DateUtil.nowVersion());
		return map;
	}
	
	@Transactional
	public void saveAccessModule(String userId, String[] module){
		Set<AccessUser> ams = new HashSet<AccessUser>();
		int countModule = 0;
		if(CommonUtil.isNotNullOrEmpty(module)){
			countModule = module.length;
		}
		User userById=userDAO.findById(userId);
		accessUserDAO.deleteByIdUser(userId);
		if(null != module && CommonUtil.isNotNullOrEmpty(userId)){
			for(int a=0; a<countModule; a++){
				Module m = moduleDAO.findById(module[a]);
				AccessUser am = new AccessUser();
				am.setUser(userById);
				am.setModule(m);
				ams.add(am);
			}
		}
		userById.getAccessUserSet().clear();
		userById.getAccessUserSet().addAll(ams);
		if(userDAO.merge(userById)){
			userDAO.inserAudiLog(getAuditLog(userById, ActionEnum.Updated.getVal()));
		}
		
		
	}
	
	@Transactional
	public boolean copyAccessModule(String userId, String userIdCopy){
		Set<AccessUser> ams = new HashSet<AccessUser>();
		List<Module> listModule = accessUserDAO.findModuleByUser(userIdCopy);
		User userById=userDAO.findById(userId);
		accessUserDAO.deleteByIdUser(userId);
		if(CommonUtil.isNotNullOrEmpty(listModule) && CommonUtil.isNotNullOrEmpty(userId)){
			for(Module m : listModule){
				AccessUser am = new AccessUser();
				am.setUser(userById);
				am.setModule(m);
				ams.add(am);
			}
		}
		userById.getAccessUserSet().clear();
		userById.getAccessUserSet().addAll(ams);
		if(userDAO.merge(userById)){
			userDAO.inserAudiLog(getAuditLog(userById, ActionEnum.Updated.getVal()));
			return true;
		}else{
			return false;
		}
		
		
	}
	
	@Transactional
	public boolean getAccesModule(String url,String username){
		if(accessUserDAO.findByUserModule(url,username)){
			return true;	
		}else{
			return false;
		}
		
	}
	
/*	@Transactional
	public boolean getAccesUserCreate(String url,String username){
		Boolean am=accessUserDAO.statusCreateByUserModule(url,username);
		if(am){
			return true;	
		}else{
			return false;
		}
		
	}
	
	@Transactional
	public boolean getAccesModuleUpdate(String url,String username){
		Boolean am=accessUserDAO.statusUpdateByUserModule(url,username);
		if(am){
			return true;	
		}else{
			return false;
		}
		
	}
*/	
	
	
	@Transactional
	public Map<String, Object> getUserList(String page, String rows, String sidx, String sord, String searchTerm, String idTarget){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<User> allRole = userDAO.comboGridFind("nama",sidx,sord,searchTerm);
		int totalRow = allRole.size();
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
			for(User r : userDAO.comboGridFindLimitOffset("nama",rowStart, rowEnd, sidx, sord, searchTerm)){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", r.getId());
				map2.put("name", r.getNama());
				if(!r.getId().equalsIgnoreCase(idTarget)){
					list.add(map2);
				}
			}
			map.put("rows", list);
			
		}else {
			map.put("page", page);
			map.put("total", totalPages);
			map.put("records", totalRow);
			for(User r : allRole){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", r.getId());
				map2.put("nama", r.getNama());
				if(!r.getId().equalsIgnoreCase(idTarget)){
					list.add(map2);
				}
			}
			map.put("rows", list);
		}
		
		return map;
	}

	public Map<String, Object> getUserJqgridList(String page, String rows,
			String sidx, String sord, String id, String nama,
			String isCreate, String isDelete, String isUpdate, String isPrint,
			String isCancel, String isReport, String isSupervisor,
			String isActive, String startDate, String endDate, String moduleId, String departmentId, String isConfirm, String isUnconfirm, String isSuperuser, String isReprint) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
	
		if(id == null){id = "";}
		if(nama == null){nama = "";}
		if(isCreate == null){isCreate = "";}
		if(isDelete == null){isDelete = "";}
		if(isUpdate == null){isUpdate = "";}
		if(isPrint == null){isPrint = "";}
		if(isCancel == null){isCancel = "";}
		if(isReport == null){isReport = "";}
		if(isSupervisor == null){isSupervisor = "";}
		if(isActive == null){isActive = "";}
		if(startDate == null){startDate = "";}
		if(endDate == null){endDate = "";}
		if(moduleId == null){moduleId = "";}
		if(departmentId == null){departmentId = "";}
		if(isSuperuser == null){isSuperuser = "";}
		if(isConfirm == null){isConfirm = "";}
		if(isUnconfirm == null){isUnconfirm = "";}
		if(isReprint == null){isReprint = "";}
		
		List<User> all = userDAO.jqgridFindAll(sidx,sord,id,nama,isCreate,isDelete,isUpdate,isPrint,isCancel,isReport,isSupervisor,isActive,startDate, endDate,moduleId,departmentId,isSuperuser,isConfirm,isUnconfirm,isReprint);
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
			for(User x : userDAO.jqgridFindLimitOffset(rowStart, rowEnd, sidx, sord,id,nama,isCreate,isDelete,isUpdate,isPrint,isCancel,isReport,isSupervisor,isActive,startDate, endDate,moduleId,departmentId,isSuperuser,isConfirm,isUnconfirm,isReprint)){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", x.getId());
				map2.put("username", x.getUsername());
				map2.put("nama", x.getNama());
				map2.put("createBy", x.getCreateBy());
				map2.put("version", x.getVersion());
				if(CommonUtil.isNotNullOrEmpty(x.getIsActive())){
					if(x.getIsActive()){
						map2.put("isActive", "Y");
					}else{
						map2.put("isActive", "N");
					}
				}else{
					map2.put("isActive", "-");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getIsCancel())){
					if(x.getIsCancel()){
						map2.put("isCancel", "Y");
					}else{
						map2.put("isCancel", "N");
					}
				}else{
					map2.put("isCancel", "-");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getIsCreate())){
					if(x.getIsCreate()){
						map2.put("isCreate", "Y");
					}else{
						map2.put("isCreate", "N");
					}
				}else{
					map2.put("isCreate", "-");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getIsDelete())){
					if(x.getIsDelete()){
						map2.put("isDelete", "Y");
					}else{
						map2.put("isDelete", "N");
					}
				}else{
					map2.put("isDelete", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getIsPrint())){
					if(x.getIsPrint()){
						map2.put("isPrint", "Y");
					}else{
						map2.put("isPrint", "N");
					}
				}else{
					map2.put("isPrint", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getIsReport())){
					if(x.getIsReport()){
						map2.put("isReport", "Y");
					}else{
						map2.put("isReport", "N");
					}
				}else{
					map2.put("isReport", "-");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getIsSupervisor())){
					if(x.getIsSupervisor()){
						map2.put("isSupervisor", "Y");
					}else{
						map2.put("isSupervisor", "N");
					}
				}else{
					map2.put("isSupervisor", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getIsSuperuser())){
					if(x.getIsSuperuser()){
						map2.put("isSuperuser", "Y");
					}else{
						map2.put("isSuperuser", "N");
					}
				}else{
					map2.put("isSuperuser", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getIsConfirm())){
					if(x.getIsConfirm()){
						map2.put("isConfirm", "Y");
					}else{
						map2.put("isConfirm", "N");
					}
				}else{
					map2.put("isConfirm", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getIsUnconfirm())){
					if(x.getIsUnconfirm()){
						map2.put("isUnconfirm", "Y");
					}else{
						map2.put("isUnconfirm", "N");
					}
				}else{
					map2.put("isUnconfirm", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getIsUpdate())){
					if(x.getIsUpdate()){
						map2.put("isUpdate", "Y");
					}else{
						map2.put("isUpdate", "N");
					}
				}else{
					map2.put("isUpdate", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getIsReprint())){
					if(x.getIsReprint()){
						map2.put("isReprint", "Y");
					}else{
						map2.put("isReprint", "N");
					}
				}else{
					map2.put("isReprint", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getCreateDate())){
					map2.put("createDate", DateUtil.defaultFormatDate(x.getCreateDate()));
				}else{
					map2.put("createDate", "unknown");
				}
				list.add(map2);
			}
			map.put("rows", list);
			
		}else {
			map.put("page", page);
			map.put("total", totalPages);
			map.put("records", totalRow);
			for(User x : all){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", x.getId());
				map2.put("username", x.getUsername());
				map2.put("nama", x.getNama());
				map2.put("createBy", x.getCreateBy());
				map2.put("version", x.getVersion());
				if(CommonUtil.isNotNullOrEmpty(x.getIsActive())){
					if(x.getIsActive()){
						map2.put("isActive", "Y");
					}else{
						map2.put("isActive", "N");
					}
				}else{
					map2.put("isActive", "-");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getIsCancel())){
					if(x.getIsCancel()){
						map2.put("isCancel", "Y");
					}else{
						map2.put("isCancel", "N");
					}
				}else{
					map2.put("isCancel", "-");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getIsCreate())){
					if(x.getIsCreate()){
						map2.put("isCreate", "Y");
					}else{
						map2.put("isCreate", "N");
					}
				}else{
					map2.put("isCreate", "-");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getIsDelete())){
					if(x.getIsDelete()){
						map2.put("isDelete", "Y");
					}else{
						map2.put("isDelete", "N");
					}
				}else{
					map2.put("isDelete", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getIsPrint())){
					if(x.getIsPrint()){
						map2.put("isPrint", "Y");
					}else{
						map2.put("isPrint", "N");
					}
				}else{
					map2.put("isPrint", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getIsReport())){
					if(x.getIsReport()){
						map2.put("isReport", "Y");
					}else{
						map2.put("isReport", "N");
					}
				}else{
					map2.put("isReport", "-");
				}
				if(CommonUtil.isNotNullOrEmpty(x.getIsSupervisor())){
					if(x.getIsSupervisor()){
						map2.put("isSupervisor", "Y");
					}else{
						map2.put("isSupervisor", "N");
					}
				}else{
					map2.put("isSupervisor", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getIsSuperuser())){
					if(x.getIsSuperuser()){
						map2.put("isSuperuser", "Y");
					}else{
						map2.put("isSuperuser", "N");
					}
				}else{
					map2.put("isSuperuser", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getIsConfirm())){
					if(x.getIsConfirm()){
						map2.put("isConfirm", "Y");
					}else{
						map2.put("isConfirm", "N");
					}
				}else{
					map2.put("isConfirm", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getIsUnconfirm())){
					if(x.getIsUnconfirm()){
						map2.put("isUnconfirm", "Y");
					}else{
						map2.put("isUnconfirm", "N");
					}
				}else{
					map2.put("isUnconfirm", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getIsUpdate())){
					if(x.getIsUpdate()){
						map2.put("isUpdate", "Y");
					}else{
						map2.put("isUpdate", "N");
					}
				}else{
					map2.put("isUpdate", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getIsReprint())){
					if(x.getIsReprint()){
						map2.put("isReprint", "Y");
					}else{
						map2.put("isReprint", "N");
					}
				}else{
					map2.put("isReprint", "-");
				}
				
				if(CommonUtil.isNotNullOrEmpty(x.getCreateDate())){
					map2.put("createDate", DateUtil.defaultFormatDate(x.getCreateDate()));
				}else{
					map2.put("createDate", "unknown");
				}
				list.add(map2);
			}
			map.put("rows", list);
		}
		
		return map;
}
	
	public AuditLog getAuditLog(User obj, String action) {
		AuditLog auditLog = new AuditLog();
		auditLog.setAction(action);
		auditLog.setCreatedDate(DateUtil.now());
		auditLog.setEntityId(obj.getId());
		auditLog.setEntityName(obj.getClass().toString());
		auditLog.setUsername(UserUtil.activeUser().getUsername());
		auditLog.setDetail("UBAH HAK AKSES USER_ID : "+obj.getId());
		auditLog.setModule(obj.getClass().getSimpleName());
		return auditLog;
	}

	public Map<String, Object> getModuleList(String page, String rows,
			String sidx, String sord, String searchTerm) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String keyword = ComboGridUtil.checkKeyword(searchTerm);
		int totalRow = 0;
		totalRow=moduleDAO.comboGridFindSize("nama",sidx,sord,keyword);
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
			for(Module k : moduleDAO.comboGridFindLimitOffset("nama",rowStart, rowEnd, sidx, sord, keyword)){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", k.getId());
				map2.put("nama", k.getNama());
				list.add(map2);
			}
			map.put("rows", list);
			
		}else {
			map.put("page", page);
			map.put("total", totalPages);
			map.put("records", totalRow);
			for(Module u : moduleDAO.comboGridFind("nama",sidx,sord,keyword)){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", u.getId());
				map2.put("nama", u.getNama());
				list.add(map2);
			}
			map.put("rows", list);
		}
		
		return map;
	}

	
	
}
