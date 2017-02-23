package com.hk.setting.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.hk.base.dao.UserDAO;
import com.hk.base.entity.User;
import com.hk.common.service.AbstractService;
import com.hk.common.util.CommonUtil;
import com.hk.common.util.UserUtil;



/**
 * @author Adik Darmadi
 * Email : darmadi.adik@gmail.com
 */

@Component
@Transactional(rollbackFor = Exception.class)
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CekUserAksesService {

	@Autowired
    private UserDAO userDAO;
	
	public Boolean isDelete(){
		return userDAO.findById(UserUtil.getUsername()).getIsDelete();
	}
	
	public Boolean isCreate(){
		return userDAO.findById(UserUtil.getUsername()).getIsCreate();
	}
	
	public Boolean isUpdate(){
		return userDAO.findById(UserUtil.getUsername()).getIsUpdate();
	}
	
	public Boolean isCancel(){
		return userDAO.findById(UserUtil.getUsername()).getIsCancel();
	}
	
	public Boolean isPrint(){
		return userDAO.findById(UserUtil.getUsername()).getIsPrint();
	}
	
	public Boolean isReport(){
		return userDAO.findById(UserUtil.getUsername()).getIsReport();
	}
	
	public Boolean isSupervisor(){
		return userDAO.findById(UserUtil.getUsername()).getIsSupervisor();
	}
	
	public Boolean isSuperuser(){
		return userDAO.findById(UserUtil.getUsername()).getIsSuperuser();
	}
	
	public Boolean isConfirm() {
		return userDAO.findById(UserUtil.getUsername()).getIsConfirm();
	}
	
	public Boolean isUnconfirm() {
		return userDAO.findById(UserUtil.getUsername()).getIsUnconfirm();
	}
	
	public Boolean cekUserAktif(){
		User user=userDAO.findById(UserUtil.getUsername());
		if(CommonUtil.isNotNullOrEmpty(user)){
			return true;
		}else{
			return false;
		}
	}

}
