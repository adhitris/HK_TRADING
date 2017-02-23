package com.hk.base.dao;

/**
 * @author Adik Darmadi
 * Email : darmadi.adik@gmail.com
 */

import java.util.List;

import com.hk.base.entity.MataUang;
import com.hk.common.dao.DAO;


public interface MataUangDAO extends DAO<MataUang>{

	List<MataUang> jqgridFindLimitOffset(int rowStart, int rowEnd, String sidx,String sord, String kode,String isActive);

	List<MataUang> jqgridFindAll(String sidx, String sord, String kode, String isActive);
	
	int jqgridFindAllSize(String sidx, String sord, String id, String isActive);


}
