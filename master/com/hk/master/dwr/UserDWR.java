package com.hk.master.dwr;

import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;

import com.hk.base.dao.UserDAO;
import com.hk.base.entity.User;
import com.hk.common.util.CommonUtil;


@RemoteProxy(name = "userDWR")
public class UserDWR {

	@Autowired
	private UserDAO userDAO;
	
	@RemoteMethod
	public synchronized Boolean checkId(String id){
		Boolean result = false;
		try{
			List<User> user=userDAO.findByIdList(id);
			if(CommonUtil.isNotNullOrEmpty(user)){
				result=true;
			}
		}catch(Exception e){
		 		result  = false;
		}
	
		return result;
	}
	
}
