package com.hk.master.dwr;

import java.util.List;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import com.hk.base.dao.ModuleDAO;
import com.hk.base.entity.Module;
import com.hk.common.util.CommonUtil;


@RemoteProxy(name = "moduleDWR")
public class ModuleDWR {

	@Autowired
	private ModuleDAO moduleDAO;
	
	@RemoteMethod
	public synchronized Boolean checkIdModule(String id){
		Boolean result = false;
		try{
			List<Module> module=moduleDAO.findByIdList(id);
			if(CommonUtil.isNotNullOrEmpty(module)){
				result=true;
			}
		}catch(Exception e){
		 		result  = false;
		}
	
		return result;
	}
	
}
