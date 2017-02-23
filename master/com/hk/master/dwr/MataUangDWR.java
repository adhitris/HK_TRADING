package com.hk.master.dwr;

import java.util.List;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import com.hk.base.dao.MataUangDAO;
import com.hk.base.entity.MataUang;
import com.hk.common.util.CommonUtil;


@RemoteProxy(name = "mataUangDWR")
public class MataUangDWR {

	@Autowired
	private MataUangDAO mataUangDAO;
	
	@RemoteMethod
	public synchronized Boolean checkIdMataUang(String id){
		Boolean result = false;
		try{
			List<MataUang> mataUang=mataUangDAO.findByIdList(id);
			if(CommonUtil.isNotNullOrEmpty(mataUang)){
				result=true;
			}
		}catch(Exception e){
		 		result  = false;
		}
	
		return result;
	}
	
}
