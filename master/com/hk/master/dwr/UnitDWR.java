package com.hk.master.dwr;

import java.util.List;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import com.hk.base.dao.UnitDAO;
import com.hk.base.entity.Unit;
import com.hk.common.util.CommonUtil;


@RemoteProxy(name = "unitDWR")
public class UnitDWR {

	@Autowired
	private UnitDAO unitDAO;
	
	@RemoteMethod
	public synchronized Boolean checkIdUnit(String id){
		Boolean result = false;
		try{
			List<Unit> unit=unitDAO.findByIdList(id);
			if(CommonUtil.isNotNullOrEmpty(unit)){
				result=true;
			}
		}catch(Exception e){
		 		result  = false;
		}
	
		return result;
	}
	
}
