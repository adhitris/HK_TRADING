package com.hk.home.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hk.base.dao.UserDAO;
import com.hk.base.entity.User;
import com.hk.common.exception.ServiceException;
import com.hk.common.service.AbstractService;
import com.hk.common.util.DateUtil;
import com.hk.common.util.UserUtil;


@Component
@Transactional(rollbackFor = Exception.class)
public class IndexService extends AbstractService {
	
	@Autowired
    private UserDAO userDAO;
	
	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;
	
	@Override
	public Map<String, Object> search() throws ServiceException{
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("isCreate", UserUtil.getUserActive().getIsCreate());
		map.put("isUpdate", UserUtil.getUserActive().getIsUpdate());
		map.put("isDelete", UserUtil.getUserActive().getIsDelete());
		map.put("isCancel", UserUtil.getUserActive().getIsCancel());
		map.put("isPrint", UserUtil.getUserActive().getIsPrint());
		map.put("isSupervisor", UserUtil.getUserActive().getIsSupervisor());
		map.put("isReport", UserUtil.getUserActive().getIsReport());
		map.put("dateStart", DateUtil.getYesterdayDate());
		map.put("dateFinish", DateUtil.getYesterdayDate());
		
		map.put("countVersionJs", DateUtil.nowVersion());
		
		List<Object> principals = sessionRegistry.getAllPrincipals();

		List<String> usersNamesList = new ArrayList<String>();

		for (Object principal: principals) {
		    if (principal instanceof User) {
		    	if(!usersNamesList.contains(((User) principal).getUsername())){
		    		usersNamesList.add(((User) principal).getUsername());
		    	}
		    }
		}
		map.put("usersNamesList", usersNamesList);
		
		
		return map;
	}
	
	


}
