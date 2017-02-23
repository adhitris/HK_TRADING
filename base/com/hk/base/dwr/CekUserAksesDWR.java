package com.hk.base.dwr;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;

import com.hk.base.dao.UserDAO;
import com.hk.common.util.UserUtil;


@RemoteProxy(name = "cekUserAksesDWR")
public class CekUserAksesDWR {

	
	@Autowired
    private UserDAO userDAO;
	
	@RemoteMethod
	public synchronized Boolean isDelete(){
		Boolean result = false;
		try{
			if(userDAO.findById(UserUtil.getUsername()).getIsDelete()){
				result  = true;
			}
			
		}catch(Exception e){
		 		result  = false;
		}
		return result;
	}
	

	
	
	
}
