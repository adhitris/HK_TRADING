package com.hk.master.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hk.base.dao.UnitDAO;
import com.hk.base.entity.Unit;
import com.hk.base.entityLog.AuditLog;
import com.hk.common.enumeration.ActionEnum;
import com.hk.common.exception.CommonException;
import com.hk.common.exception.ServiceException;
import com.hk.common.service.AbstractService;
import com.hk.common.util.ComboGridUtil;
import com.hk.common.util.CommonUtil;
import com.hk.common.util.DateUtil;
import com.hk.common.util.UserUtil;



/**
 * @author Adik Darmadi
 * Email : darmadi.adik@gmail.com
 */

@Component
@Transactional(rollbackFor = Exception.class)
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UnitService extends AbstractService<Unit>{

	@Autowired
    private UnitDAO unitDAO;
	
	public Map<String, Object> preadd() throws ServiceException{
		Map<String, Object> map = new HashMap<String, Object>();
		Unit unit=new Unit();
		map.put("unit", unit);
		map.put("countVersionJs", DateUtil.nowVersion());
		return map;
	}
	
	@Transactional
	public Boolean add(Unit unit) throws ServiceException{
		unit.setId(unit.getId().toUpperCase());
		unit.setCreateBy(UserUtil.getUsername());
		unit.setCreateDate(DateUtil.now());
		unit.setIsActive(true);
		if(unitDAO.insert(unit)){
			unitDAO.inserAudiLog(getAuditLog(unit, ActionEnum.Saved.getVal()));
			return true;
		}else{
			return false;
		}
	}
	
	public Map<String, Object> preedit(String id) throws ServiceException{
		Unit unit = unitDAO.findById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("unit", unit);
		map.put("countVersionJs", DateUtil.nowVersion());
		return map;
	}
	
	@Transactional
	public Boolean edit(Unit unit) throws ServiceException{
		Unit unitUpdate = unitDAO.findById(unit.getId());
		unitUpdate.setNama(unit.getNama());
		unitUpdate.setLastUpdateBy(UserUtil.getUsername());
		unitUpdate.setLastUpdateDate(DateUtil.now());
		unitUpdate.setVersion(unit.getVersion());
		if(unitDAO.update(unitUpdate)){
			unitDAO.inserAudiLog(getAuditLog(unitUpdate, ActionEnum.Updated.getVal()));
			return true;
		}else{
			return false;
		}
	}
	
	@Transactional
	public Boolean deleteById(Unit unit) throws CommonException{
		Unit unitUpdate=unitDAO.findById(unit.getId());
		if(unitDAO.deleteById(unit.getId())){
			unitDAO.inserAudiLog(getAuditLog(unitUpdate, ActionEnum.Deleted.getVal()));
			return true;
		}else{
			return false;
		}
		
	}
	
	public AuditLog getAuditLog(Unit obj,String action){
		
		AuditLog auditLog=new AuditLog();
		auditLog.setAction(action);
		auditLog.setCreatedDate(DateUtil.now());
		auditLog.setEntityId(obj.getId());
		auditLog.setEntityName(obj.getClass().toString());
		auditLog.setUsername(UserUtil.activeUser().getUsername());
		auditLog.setDetail("Unit ID : "+obj.getId());
		auditLog.setModule(obj.getClass().getSimpleName());
		return auditLog;
	}
	
	@Transactional
	public Map<String, Object> getUnitJsonList(String page, String rows, String sidx, String sord, String searchTerm){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String keyword = ComboGridUtil.checkKeyword(searchTerm);
		List<Unit> allUnit = unitDAO.comboGridFind("id",sidx,sord,keyword);
		int totalRow = allUnit.size();
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
			for(Unit k : unitDAO.comboGridFindLimitOffset("id",rowStart, rowEnd, sidx, sord, keyword)){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", k.getId());
				map2.put("createBy", k.getCreateBy());
				list.add(map2);
			}
			map.put("rows", list);
			
		}else {
			map.put("page", page);
			map.put("total", totalPages);
			map.put("records", totalRow);
			for(Unit u : allUnit){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", u.getId());
				map2.put("createBy", u.getCreateBy());
				list.add(map2);
			}
			map.put("rows", list);
		}
		
		return map;
	}

	public Map<String, Object> getUnitJqgridList(String page,String rows, String sidx, String sord, String id,String nama, String isActive) {
		
			Map<String, Object> map = new HashMap<String, Object>();
			if(id == null){id = "";}
			if(nama == null){nama = "";}
			if(isActive == null){isActive = "";}
			
			List<Unit> all = unitDAO.jqgridFindAll(sidx,sord,id,nama,isActive);
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
				for(Unit x : unitDAO.jqgridFindLimitOffset(rowStart, rowEnd, sidx, sord, id,nama,isActive)){
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("id", x.getId());
					map2.put("createBy", x.getCreateBy());
					map2.put("nama", x.getNama());
					map2.put("version", x.getVersion());
					if(CommonUtil.isNotNullOrEmpty(x.getIsActive())){
						if(x.getIsActive()){
							map2.put("isActive", "Y");
						}else{
							map2.put("isActive", "N");
						}
					}
					if(CommonUtil.isNotNullOrEmpty(x.getCreateDate())){
						map2.put("createDate", DateUtil.formatDateTime(x.getCreateDate()));
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
				for(Unit x : all){
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("id", x.getId());
					map2.put("createBy", x.getCreateBy());
					map2.put("nama", x.getNama());
					map2.put("version", x.getVersion());
					if(CommonUtil.isNotNullOrEmpty(x.getIsActive())){
						if(x.getIsActive()){
							map2.put("isActive", "Y");
						}else{
							map2.put("isActive", "N");
						}
					}
					if(CommonUtil.isNotNullOrEmpty(x.getCreateDate())){
						map2.put("createDate", DateUtil.formatDateTime(x.getCreateDate()));
					}else{
						map2.put("createDate", "unknown");
					}
					list.add(map2);
				}
				map.put("rows", list);
			}
			
			return map;
	}
	public Boolean editIsActive(Unit unit) throws CommonException {
		Unit unitUpdate = unitDAO.findById(unit.getId());
		if (unitUpdate.getIsActive()) {
			unitUpdate.setIsActive(false);
			unitUpdate.setVersion(unit.getVersion());
			if(unitDAO.update(unitUpdate)){
				unitDAO.inserAudiLog(getAuditLog(unitUpdate, ActionEnum.NonActived.getVal()));
				return true;
			}else{
				return false;
			}
		} else {
			unitUpdate.setIsActive(true);
			unitUpdate.setVersion(unit.getVersion());
			if(unitDAO.update(unitUpdate)){
				unitDAO.inserAudiLog(getAuditLog(unitUpdate, ActionEnum.Actived.getVal()));
				return true;
			}else{
				return false;
			}
		}
		
		
	}
	
}
