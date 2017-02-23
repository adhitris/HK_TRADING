package com.hk.base.dao;

/**
 * @author Adik Darmadi
 * Email : darmadi.adik@gmail.com
 */

import java.util.List;

import com.hk.base.entity.Unit;
import com.hk.common.dao.DAO;


public interface UnitDAO extends DAO<Unit>{

	List<Unit> jqgridFindLimitOffset(int rowStart, int rowEnd, String sidx,String sord, String id,String nama,String isActive);

	List<Unit> jqgridFindAll(String sidx, String sord, String id,String nama, String isActive);
	
	int jqgridFindAllSize(String sidx, String sord, String id,String nama, String isActive);

	List<Unit> comboGridFindNotInInisial(String string, String sidx,
			String sord, String keyword);

	List<Unit> comboGridFindNotInInisialLimitOffset(String string,
			int rowStart, int rowEnd, String sidx, String sord, String keyword);

	int comboGridFindNotInInisialSize(String string, String sidx,
			String sord, String keyword);

	int comboGridFindNotInInisialBySOSize(String string, String sidx, String sord, String keyword,
			String orderJualKainHdrId);

	List<Unit> comboGridFindNotInInisialBySOLimitOffset(String string, int rowStart, int rowEnd, String sidx, String sord,
			String keyword, String orderJualKainHdrId);

	List<Unit> comboGridFindNotInInisialBySO(String string, String sidx, String sord, String keyword,
			String orderJualKainHdrId);

}
