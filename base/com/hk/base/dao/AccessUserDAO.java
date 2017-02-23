package com.hk.base.dao;

import java.util.List;

import com.hk.base.entity.AccessUser;
import com.hk.base.entity.Module;
import com.hk.common.dao.DAO;

public interface AccessUserDAO extends DAO<AccessUser>{




/*	Boolean statusCreateByUserModule(String url, String username);

	Boolean statusUpdateByUserModule(String url, String username);
*/
	void deleteByIdUser(String id);

	Boolean findByUserModule(String url, String username);

	List<Module> findModuleByUser(String id);

	void deleteByIdModule(String id);
	
	Boolean findByModuleUser(String moduleId,String userId);

}
