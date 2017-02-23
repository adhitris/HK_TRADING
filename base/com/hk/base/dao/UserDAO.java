package com.hk.base.dao;

import java.util.List;

import com.hk.base.entity.User;
import com.hk.common.dao.DAO;

public interface UserDAO extends DAO<User>{

	List<User> findByStatusUser(Boolean statusUser);

	User findByIdSingle(String id);

	List<User> jqgridFindAll(String sidx, String sord, String id,
			String firstName, String isCreate, String isDelete,
			String isUpdate, String isPrint, String isCancel, String isReport,
			String isSupervisor, String isActive,  String startDate,
			String endDate, String moduleId, String departmentId, String isSuperuser, String isConfirm, String isUnconfirm, String isReprint);

	List<User> jqgridFindLimitOffset(int rowStart, int rowEnd, String sidx,
			String sord, String id, String firstName, String isCreate,
			String isDelete, String isUpdate, String isPrint, String isCancel,
			String isReport, String isSupervisor, String isActive, 
			String startDate, String endDate, String moduleId, String departmentId, String isSuperuser, String isConfirm, String isUnconfirm, String isReprint);
	
	int jqgridFindAllSize(String sidx, String sord, String id,
			String firstName, String isCreate, String isDelete,
			String isUpdate, String isPrint, String isCancel, String isReport,
			String isSupervisor, String isActive,  String startDate,
			String endDate, String moduleId, String departmentId, String isSuperuser, String isConfirm, String isUnconfirm);

	List<User> comboGridFindFromSales(String string, String sidx, String sord,String searchTerm);

	List<User>  comboGridFindFromSalesLimitOffset(String string, int rowStart,int rowEnd, String sidx, String sord, String searchTerm);
	
	int comboGridFindFromSalesSize(String string, String sidx, String sord,String searchTerm);

	List<User> findByParam(Boolean statusUser, String id, String nama,
			String isCreate, String isDelete, String isUpdate, String isPrint,
			String isCancel, String isReport, String isSupervisor);

	List<User> comboGridFindFromSalesEdit(String string, String sidx,
			String sord, String searchTerm, String salesId);

	List<User> comboGridFindFromSalesLimitOffsetEdit(String string, int rowStart,
			int rowEnd, String sidx, String sord, String searchTerm,
			String salesId);


}
