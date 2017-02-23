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

import com.hk.base.dao.MataUangDAO;
import com.hk.base.entity.MataUang;
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
public class MataUangService extends AbstractService<MataUang>{

	@Autowired
    private MataUangDAO mataUangDAO;
	
	public Map<String, Object> preadd() throws ServiceException{
		Map<String, Object> map = new HashMap<String, Object>();
		MataUang mataUang=new MataUang();
		map.put("mataUang", mataUang);
		map.put("countVersionJs", DateUtil.nowVersion());
		return map;
	}
	
	@Transactional
	public Boolean add(MataUang mataUang) throws ServiceException{
		mataUang.setId(mataUang.getId().toUpperCase());
		mataUang.setCreateBy(UserUtil.getUsername());
		mataUang.setCreateDate(DateUtil.now());
		mataUang.setIsActive(true);
		if(mataUangDAO.insert(mataUang)){
			mataUangDAO.inserAudiLog(getAuditLog(mataUang, ActionEnum.Saved.getVal()));

			return true;
		}else{
			return false;
		}
	}
	
	public Map<String, Object> preedit(String id) throws ServiceException{
		MataUang mataUang = mataUangDAO.findById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mataUang", mataUang);
		map.put("countVersionJs", DateUtil.nowVersion());
		return map;
	}
	
	@Transactional
	public Boolean edit(MataUang mataUang) throws ServiceException{
		MataUang mataUangUpdate = mataUangDAO.findById(mataUang.getId());
		mataUangUpdate.setLastUpdateBy(UserUtil.getUsername());
		mataUangUpdate.setLastUpdateDate(DateUtil.now());
		mataUangUpdate.setVersion(mataUang.getVersion());
		if(mataUangDAO.update(mataUangUpdate)){
			mataUangDAO.inserAudiLog(getAuditLog(mataUangUpdate, ActionEnum.Updated.getVal()));

			return true;
		}else{
			return false;
		}
	}
	
	@Transactional
	public Boolean deleteById(MataUang mataUang) throws CommonException{
		MataUang mataUangUpdate=mataUangDAO.findById(mataUang.getId());
		if(mataUangDAO.deleteById(mataUang.getId())){
			mataUangDAO.inserAudiLog(getAuditLog(mataUangUpdate, ActionEnum.Deleted.getVal()));
			return true;
		}else{
			return false;
		}
	}
	
	public AuditLog getAuditLog(MataUang obj,String action){
		
		AuditLog auditLog=new AuditLog();
		auditLog.setAction(action);
		auditLog.setCreatedDate(DateUtil.now());
		auditLog.setEntityId(obj.getId());
		auditLog.setEntityName(obj.getClass().toString());
		auditLog.setUsername(UserUtil.activeUser().getUsername());
		auditLog.setDetail("Mata Uang ID : "+obj.getId());
		auditLog.setModule(obj.getClass().getSimpleName());
		return auditLog;
	}
	
	@Transactional
	public Map<String, Object> getMataUangJsonList(String page, String rows, String sidx, String sord, String searchTerm){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String keyword = ComboGridUtil.checkKeyword(searchTerm);
		List<MataUang> allMataUang = mataUangDAO.comboGridFind("id",sidx,sord,keyword);
		int totalRow = allMataUang.size();
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
			for(MataUang k : mataUangDAO.comboGridFindLimitOffset("id",rowStart, rowEnd, sidx, sord, keyword)){
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
			for(MataUang u : allMataUang){
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", u.getId());
				map2.put("createBy", u.getCreateBy());
				list.add(map2);
			}
			map.put("rows", list);
		}
		
		return map;
	}

	public Map<String, Object> getMataUangJqgridList(String page,String rows, String sidx, String sord, String id, String isActive) {
		
			Map<String, Object> map = new HashMap<String, Object>();
			if(id == null){id = "";}
			if(isActive == null){isActive = "";}
			
			int totalRow = mataUangDAO.jqgridFindAllSize(sidx,sord,id,isActive);
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
				for(MataUang x : mataUangDAO.jqgridFindLimitOffset(rowStart, rowEnd, sidx, sord, id,isActive)){
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("id", x.getId());
					map2.put("createBy", x.getCreateBy());
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
				for(MataUang x : mataUangDAO.jqgridFindAll(sidx,sord,id,isActive)){
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("id", x.getId());
					map2.put("createBy", x.getCreateBy());
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
	public Boolean editIsActive(MataUang mataUang) throws CommonException {
		MataUang mataUangUpdate = mataUangDAO.findById(mataUang.getId());
		if (mataUangUpdate.getIsActive()) {
			mataUangUpdate.setIsActive(false);
			mataUangUpdate.setVersion(mataUang.getVersion());
			if(mataUangDAO.update(mataUangUpdate)){
				mataUangDAO.inserAudiLog(getAuditLog(mataUangUpdate, ActionEnum.NonActived.getVal()));
				return true;
			}else{
				return false;
			}
		} else {
			mataUangUpdate.setIsActive(true);
			mataUangUpdate.setVersion(mataUang.getVersion());
			if(mataUangDAO.update(mataUangUpdate)){
				mataUangDAO.inserAudiLog(getAuditLog(mataUangUpdate, ActionEnum.Actived.getVal()));
				return true;
			}else{
				return false;
			}
		}
	
		
	}
	
}
