package com.hk.base.dao;

import java.util.Date;
import java.util.List;

import com.hk.base.entity.Module;
import com.hk.common.dao.DAO;

public interface ModuleDAO extends DAO<Module> {

	List<Module> comboGridFindParentLimitOffset(String criteriaName,Integer startRow, Integer endRow, String sidx, String sord,String searchTerm);

	List<Module> comboGridFindParent(String criteriaName, String sidx,String sord, String searchTerm);
	
	int comboGridFindParentSize(String criteriaName, String sidx,String sord, String searchTerm);

	List<Module> ParentNode();

	List<Module> childNode(String parentId);

	List<Module> findModuleById(String id);

	List<Module> findModuleChildByIdParent(String id, String idParent);

	List<Module> jqgridFindAll(String sidx, String sord, String searchTerm1,String searchTerm2, Date searchTerm3);

	List<Module> jqgridFindLimitOffset(Integer startRow, Integer endRow,String sidx, String sord, String searchTerm1, String searchTerm2,Date searchTerm3);

	int jqgridFindAllSize(String sidx, String sord, String searchTerm1,String searchTerm2, Date searchTerm3);

	Module findModuleByUrl(String url);
	
}
