package com.hk.common.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hk.base.entityLog.AuditLog;
import com.hk.common.exception.CommonException;

public interface DAO<T> {

	List<T> findAll();

	T findById(String id);
	
	T findByIdLog(String id);
	
	Boolean insert(T obj);
	
	Boolean insertLog(T obj);

	Boolean update(T obj) throws CommonException;

	Boolean updateLog(T obj) throws CommonException;
	
	boolean delete(T obj) throws CommonException;
	
	Object execUnique(String query);

	Object execUnique(String query, Object[] parameters);

	Object execList(String query);

	Object execList(String query, Object[] parameters);

	Object execUnique(Query query, Session session);

	Object execList(Query query, Session session);

	List<T> comboGridFindLimitOffset(String criteriaName, Integer startRow,Integer endRow, String sidx, String sord, String searchTerm);

	List<T> comboGridFind(String criteriaName, String sidx, String sord,String searchTerm);

	
	Boolean deleteById(String id) throws CommonException;
	
	Boolean deleteByIdLog(String id) throws CommonException;

	void inserAudiLog(AuditLog entity);
	
	//Boolean insertSelisihStockLog(SelisihStockOpnameLogHdr selisihStockOpnameLogHdr);
	
	Boolean merge(T obj);

	Boolean saveUpdate(T obj);

	Boolean editIsActive(T obj)throws CommonException;

	List<T> findByIdList(String id);
	
	List<T> findByIdListLog(String id);
	
	Boolean insertAutoIncrement(T obj);

	Boolean editIsCancel(T obj, AuditLog auditLog) throws CommonException;

	Object getLastId(T obj,Date tanggal);
	
	int comboGridFindSize(String criteriaName, String sidx, String sord, String searchTerm);

	int checkJenisKainIsTambahan(String jenisKainId);

	Object getKodeTranIdBbmCelupProses(String kodeTran, String kodeTranId);

	Object getLastId2(T obj, Date tanggal);
	Object getLastIdRAM(T obj, Date tanggal);

	List<T> findAllTransaction();
}
