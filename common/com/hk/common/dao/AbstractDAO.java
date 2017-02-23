package com.hk.common.dao;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.hk.base.entityLog.AuditLog;
import com.hk.common.enumeration.KodeTransaksiEnum;
import com.hk.common.exception.CommonException;
import com.hk.common.util.CommonUtil;
import com.hk.common.util.DateUtil;
import com.hk.common.util.HibernateUtil;
import com.hk.common.util.HibernateUtil2;

public abstract class AbstractDAO<T> implements DAO<T> {

	protected abstract Class<T> getDomainClass();

	public Session getSession() {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();

		return session;
	}
	
	public final static Session getSession2(){
		return SessionFactoryUtils.getSession(HibernateUtil2.getSessionFactory2(), true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		Session session = getSession();
		try {
			session.beginTransaction();
			return (List<T>) session
					.createQuery(
							"from "
									+ getDomainClass().getName()
									+ " x where x.isActive=true order by x.id asc")
					.list();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}finally {	
			if (session != null) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllTransaction() {
		Session session = getSession();
		try {
			session.beginTransaction();
			return (List<T>) session
					.createQuery(
							"from "
									+ getDomainClass().getName()
									+ " x where x.isCancel=false order by x.id asc")
					.list();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}finally {	
			if (session != null) {
				session.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findById(String id) {
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from "
							+ getDomainClass().getSimpleName()
							+ " domain where domain.id =:id    ");
			query.setString("id", id);
			tx.commit();
			return (T) query.uniqueResult();
			
		} catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}finally {	
			if (session != null) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findByIdLog(String id) {
		Session session = getSession2();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from "
							+ getDomainClass().getSimpleName()
							+ " domain where domain.id =:id    ");
			query.setString("id", id);
			tx.commit();
			return (T) query.uniqueResult();
			
		} catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}finally {	
			if (session != null) {
				session.close();
			}
		}
	}
	
	@Override
	public Boolean insert(T obj) {
		Boolean a = false;
		Session session = getSession();
	    Transaction tx = session.beginTransaction();
		try{
			Field field = obj.getClass().getDeclaredField("id");
			field.setAccessible(true);
			Object id = field.get(obj);
			if(CommonUtil.isNotNullOrEmpty(id)){
				session.save(obj);
			    tx.commit();
			    a = true;
			}else{
				a=false;
			}
		    
		}catch (HibernateException e) {
			tx.rollback();
			session.flush();
			a = false;
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}catch (Exception e) {
			throw SessionFactoryUtils.convertHibernateAccessException((HibernateException) e);
		}finally {	
			if (session != null) {
				session.close();
			}
		}
		return a;
		
    }
	
	@Override
	public Boolean insertLog(T obj) {
		Boolean a = false;
		Session session = getSession2();
	    Transaction tx = session.beginTransaction();
		try{
			Field field = obj.getClass().getDeclaredField("id");
			field.setAccessible(true);
			Object id = field.get(obj);
			if(CommonUtil.isNotNullOrEmpty(id)){
				session.save(obj);
			    tx.commit();
			    a = true;
			}else{
				a=false;
			}
		    
		}catch (HibernateException e) {
			tx.rollback();
			session.flush();
			a = false;
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}catch (Exception e) {
			throw SessionFactoryUtils.convertHibernateAccessException((HibernateException) e);
		}finally {	
			if (session != null) {
				session.close();
			}
		}
		return a;
		
    }
	
	@Override
	public Boolean insertAutoIncrement(T obj) {
		Boolean a = false;
		Session session = getSession();
	    Transaction tx = session.beginTransaction();
		try{
				session.save(obj);
			    tx.commit();
			    a = true;
		    
		}catch (HibernateException e) {
			tx.rollback();
			a = false;
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}catch (Exception e) {
			throw SessionFactoryUtils.convertHibernateAccessException((HibernateException) e);
		}finally {	
			if (session != null) {
				session.close();
			}
		}
		return a;
		
    }
	
	@Override
	public Boolean update(T obj) throws CommonException {
		
		Session session = getSession();
		Boolean a = false;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
			a = true;
		} catch (StaleStateException  e) {
			throw new CommonException("<span id='locking' class='locking'> Data telah diupdate atau dihapus oleh transaksi lain,Reresh Kembali Browser Anda Untuk Mendapatkan Update Data Terbaru Dan Kemudian Tekan Tombol Simpan</span>");
		} catch (HibernateException e) {
			try {
				tx.rollback();
				throw SessionFactoryUtils.convertHibernateAccessException((HibernateException) e);
			} catch (RuntimeException rbe) {
				
			}
			
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				throw SessionFactoryUtils.convertHibernateAccessException((HibernateException) e);
			} catch (RuntimeException rbe) {
				
			}
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		/*try{
			tx = session.beginTransaction();
			session.update(obj);
		    tx.commit();
		    a = true;
		}catch (HibernateException e) {
			tx.rollback();
			a = false;
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}finally {	
			if (session != null) {
				session.close();
			}
		}*/
		return a;
    }
	
	@Override
	public Boolean updateLog(T obj) throws CommonException {
		
		Session session = getSession2();
		Boolean a = false;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
			a = true;
		} catch (StaleStateException  e) {
			throw new CommonException("<span id='locking' class='locking'> Data telah diupdate atau dihapus oleh transaksi lain,Reresh Kembali Browser Anda Untuk Mendapatkan Update Data Terbaru Dan Kemudian Tekan Tombol Simpan</span>");
		} catch (HibernateException e) {
			try {
				tx.rollback();
				throw SessionFactoryUtils.convertHibernateAccessException((HibernateException) e);
			} catch (RuntimeException rbe) {
				
			}
			
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				throw SessionFactoryUtils.convertHibernateAccessException((HibernateException) e);
			} catch (RuntimeException rbe) {
				
			}
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return a;
    }
	
	@Override
	public Boolean saveUpdate(T obj) {
		Boolean a=false;
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(obj);
			tx.commit();
			a=true;
		} catch (HibernateException e) {
			a=false;
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
			}
			throw e;
		} catch (RuntimeException e) {
			a=false;
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
			}
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return a;
	}
	
	
	@Override
	public Boolean merge(T obj) {
		Boolean a=false;
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(obj);
			a=true;
			tx.commit();
		} catch (HibernateException e) {
			a=false;
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		} catch (RuntimeException e) {
			a=false;
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
			}
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return a;
	}

	@Override
	public boolean delete(T obj) throws CommonException {
		
		Session sess = getSession();
	    Transaction tx = sess.beginTransaction();
	    Boolean result=false;
		try{
		    sess.delete(obj);
		    tx.commit();
		    result=true;
		}  catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		} finally {	
			if (sess != null) {
				sess.close();
			}
			
		}
		return result;
		
	}

	@Override
	public Boolean deleteById(String id) throws CommonException {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Boolean result=false;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("DELETE FROM "+ getDomainClass().getSimpleName() + " x where x.id =:id");
			query.setString("id", id);
			query.executeUpdate();
			result=true;
			tx.commit();
		} catch (HibernateException e) {
			throw new CommonException("<span id='constraint' class='constraint'> Your data has relation to other information</span>");
		}finally {	
			if (session != null) {
				session.close();
			}
			
		}
		
		return result;
		
	}
	
	@Override
	public Boolean deleteByIdLog(String id) throws CommonException {
		Session session = getSession2();
		Transaction tx = session.beginTransaction();
		Boolean result=false;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("DELETE FROM "+ getDomainClass().getSimpleName() + " x where x.id =:id");
			query.setString("id", id);
			query.executeUpdate();
			result=true;
			tx.commit();
		} catch (HibernateException e) {
			throw new CommonException("<span id='constraint' class='constraint'> Your data has relation to other information</span>");
		}finally {	
			if (session != null) {
				session.close();
			}
			
		}
		
		return result;
		
	}
	
	@Override
	public Object execUnique(String query) {
		Session session = getSession();
		try {
			return session.createQuery(query).uniqueResult();
		} catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}
	}

	@Override
	public Object execUnique(String query, Object[] parameters) {
		Session session = getSession();
		try {
			Query object = session.createQuery(query);
			setParameters(object, parameters);
			return object.uniqueResult();
		} catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}
	}

	@Override
	public Object execList(String query) {
		Session session = getSession();
		try {
			return session.createQuery(query).list();
		} catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}
	}

	@Override
	public Object execList(String query, Object[] parameters) {
		Session session = getSession();
		try {
			Query object = session.createQuery(query);
			setParameters(object, parameters);
			return object.list();
		} catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}
	}

	@Override
	public Object execUnique(Query query, Session session) {
		try {
			return query.uniqueResult();
		} catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}
	}

	@Override
	public Object execList(Query query, Session session) {
		try {
			return query.list();
		} catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}
	}
	
	private void setParameters(Query query, Object parameters[]) {
		if (parameters == null || parameters.length == 0) {
			return;
		}

		for (int i = 0; i < parameters.length; i++) {
			if (parameters[i] == null) {

			}
			query.setParameter(i, parameters[i]);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> comboGridFindLimitOffset(String criteriaName,Integer startRow, Integer endRow, String sidx, String sord,String searchTerm) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select x from "+ getDomainClass().getSimpleName()+ " x where x.id IS NOT NULL   and x.isActive=true ");
		buffer.append("and concat(lower(x." + criteriaName + "),lower(x.id)) like :search ");
		if (sidx != "") {
			buffer.append("order by x." + sidx + " " + sord);
		}
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%" + searchTerm + "%");

		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return (List<T>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> comboGridFind(String criteriaName, String sidx, String sord,String searchTerm) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select x from "+ getDomainClass().getSimpleName()+ " x where x.id IS NOT NULL   and x.isActive=true ");
		buffer.append("and concat(lower(x." + criteriaName + "),lower(x.id)) like :search ");
		if (sidx != "") {
			buffer.append("order by x." + sidx + " " + sord);
		}
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%" + searchTerm + "%");

		return (List<T>) query.list();
	}
	
	@Override
	public int comboGridFindSize(String criteriaName, String sidx, String sord,String searchTerm){
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(x.id) from "+ getDomainClass().getSimpleName()+ " x where x.id IS NOT NULL   and x.isActive=true ");
		buffer.append("and concat(lower(x." + criteriaName + "),lower(x.id)) like :search ");
		if (sidx != "") {
			buffer.append("order by x." + sidx + " " + sord);
		}
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%" + searchTerm + "%");

		return ((Long) query.uniqueResult()).intValue();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Object getLastId(T obj,Date tanggal){
		StringBuffer buffer = new StringBuffer();
		buffer.append("select x.id from "+ getDomainClass().getSimpleName()+ " x where month(x.tanggal)=:monthNow and year(x.tanggal)=:yearNow order by x.id desc");
		Query query = getSession().createQuery(buffer.toString());
		query.setParameter("monthNow", DateUtil.getMonthFromDate(tanggal));
		query.setParameter("yearNow", DateUtil.getYearFromDate(tanggal));
		query.setMaxResults(1);
		return query.uniqueResult();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Object getLastId2(T obj,Date tanggal){
		StringBuffer buffer = new StringBuffer();
		buffer.append("select x.id from "+ getDomainClass().getSimpleName()+ " x where month(x.tanggal)=:monthNow and year(x.tanggal)=:yearNow order by x.id desc");
		Query query = getSession2().createQuery(buffer.toString());
		query.setParameter("monthNow", DateUtil.getMonthFromDate(tanggal));
		query.setParameter("yearNow", DateUtil.getYearFromDate(tanggal));
		query.setMaxResults(1);
		return query.uniqueResult();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Object getLastIdRAM(T obj,Date tanggal){
		StringBuffer buffer = new StringBuffer();
		buffer.append("select x.id from "+ getDomainClass().getSimpleName()+ " x where month(x.createDate)=:monthNow and year(x.createDate)=:yearNow order by x.id desc");
		Query query = getSession2().createQuery(buffer.toString());
		query.setParameter("monthNow", DateUtil.getMonthFromDate(tanggal));
		query.setParameter("yearNow", DateUtil.getYearFromDate(tanggal));
		query.setMaxResults(1);
		return query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByIdList(String id) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select x from " + getDomainClass().getSimpleName()+ " x where x.id=:id ");
		Query query = getSession().createQuery(buffer.toString());
		query.setString("id", id);
		return (List<T>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByIdListLog(String id) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select x from " + getDomainClass().getSimpleName()+ " x where x.id=:id ");
		Query query = getSession2().createQuery(buffer.toString());
		query.setString("id", id);
		return (List<T>) query.list();
	}
	
	public void inserAudiLog(AuditLog auditLog){
		Session session = getSession2();
		Transaction tx = session.beginTransaction();
		try {
			session.save(auditLog);
			tx.commit();
		}catch (Exception e){
			e.printStackTrace();
		    tx.rollback();
		}finally {	
			if (session != null) {
				session.close();
			}
			
		}
	}
	
	/*public Boolean insertSelisihStockLog(SelisihStockOpnameLogHdr selisihStockOpnameLogHdr){
		Session session = getSession2();
		Boolean a = false;
		Transaction tx = session.beginTransaction();
		try {
			session.save(selisihStockOpnameLogHdr);
			tx.commit();
			a = true;
		}catch (Exception e){
			e.printStackTrace();
		    tx.rollback();
		}finally {	
			if (session != null) {
				session.close();
			}
			
		}
		return a;
	}*/

	
	@Override
	public Boolean editIsActive(T obj) throws CommonException {
		Session session = getSession();
		Boolean a = false;
		Transaction tx = null;
		try {
			Field fieldId = obj.getClass().getDeclaredField("id");
			Field fieldIsActive = obj.getClass().getDeclaredField("isActive");
			fieldId.setAccessible(true);
			fieldIsActive.setAccessible(true);
			Object id = fieldId.get(obj);
			Object isActive = fieldIsActive.get(obj);
			if(CommonUtil.isNotNullOrEmpty(id) && CommonUtil.isNotNullOrEmpty(isActive)){
				tx = session.beginTransaction();
				Query query = session.createQuery("UPDATE "+ getDomainClass().getSimpleName() + " set isActive=:isActive where id=:id");
				query.setString("id", id.toString());
				query.setBoolean("isActive", new Boolean(isActive.toString()));
				query.executeUpdate();
				tx.commit();
				a = true;
			}
			
		} catch (StaleStateException  e) {
			throw new CommonException("<span id='locking' class='locking'> Data telah diupdate atau dihapus oleh transaksi lain,Reresh Kembali Browser Anda Untuk Mendapatkan Update Data Terbaru Dan Kemudian Tekan Tombol Simpan</span>");
		} catch (RuntimeException e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				
			}
			
		}catch (Exception e) {
			throw SessionFactoryUtils.convertHibernateAccessException((HibernateException) e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return a;
	}
	
	@Override
	public Boolean editIsCancel(T obj, AuditLog auditLog) throws CommonException {
		Session session = getSession();
		Boolean a = false;
		Transaction tx = null;
		try {
			Field fieldId = obj.getClass().getDeclaredField("id");
			Field fieldIsCancel = obj.getClass().getDeclaredField("isCancel");
			fieldId.setAccessible(true);
			fieldIsCancel.setAccessible(true);
			Object id = fieldId.get(obj);
			Object isCancel = fieldIsCancel.get(obj);
			if(CommonUtil.isNotNullOrEmpty(id) && CommonUtil.isNotNullOrEmpty(isCancel)){
				tx = session.beginTransaction();
				Query query = session.createQuery("UPDATE "+ getDomainClass().getSimpleName() + " set isCancel=:isCancel where id=:id");
				query.setString("id", id.toString());
				query.setBoolean("isCancel", new Boolean(isCancel.toString()));
				query.executeUpdate();
				inserAudiLog(auditLog);
				tx.commit();
				a = true;
			}
			
		} catch (StaleStateException  e) {
			throw new CommonException("<span id='locking' class='locking'> Data telah diupdate atau dihapus oleh transaksi lain,Reresh Kembali Browser Anda Untuk Mendapatkan Update Data Terbaru Dan Kemudian Tekan Tombol Simpan</span>");
		} catch (RuntimeException e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				
			}
			
		}catch (Exception e) {
			throw SessionFactoryUtils.convertHibernateAccessException((HibernateException) e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return a;
	}
	
	
	@Override
	public int checkJenisKainIsTambahan(String jenisKainId) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(x.id) from JenisKainTambahan x where x.jenisKainId =:jenisKainId ");

		Query query = getSession().createQuery(buffer.toString());
		query.setString("jenisKainId", jenisKainId);
		return ((Long) query.uniqueResult()).intValue();
	}
	
	@Override
	public Object getKodeTranIdBbmCelupProses(String kodeTran,String kodeTranId) {

		StringBuffer buffer = new StringBuffer();

		buffer.append("Select ");

		if(kodeTran.equalsIgnoreCase(KodeTransaksiEnum.BbmClp.getVal())){
			buffer.append("bbmClp.id from BbmCelupDtl bbmClpDtl join bbmClpDtl.bbmCelupHdr bbmClp ");
			buffer.append("where bbmClpDtl.id =:kodeTranId ");
		}else if(kodeTran.equalsIgnoreCase(KodeTransaksiEnum.BbmPrg.getVal())){
			buffer.append("bbmPrg.id from BbmProsesGreigeDtl bbmPrgDtl join bbmPrgDtl.bbmProsesGreigeHdr bbmPrg ");
			buffer.append("where bbmPrgDtl.id =:kodeTranId ");
		}else if(kodeTran.equalsIgnoreCase(KodeTransaksiEnum.BbmPrw.getVal())){
			buffer.append("bbmPrw.id from BbmProsesWarnaDtl bbmPrwDtl join bbmPrwDtl.bbmProsesWarnaHdr bbmPrw ");
			buffer.append("where bbmPrwDtl.id =:kodeTranId ");
		}else if(kodeTran.equalsIgnoreCase(KodeTransaksiEnum.BbmPrm.getVal())){
			buffer.append("bbmPrm.id from BbmProsesMotifDtl bbmPrmDtl join bbmPrmDtl.bbmProsesMotifHdr bbmPrm ");
			buffer.append("where bbmPrmDtl.id =:kodeTranId ");
		}
		
	
		Query query = getSession().createQuery(buffer.toString());
		
		if(CommonUtil.isNotNullOrEmpty(kodeTranId)){
			query.setString("kodeTranId", kodeTranId);
		}
		
		return (Object)query.uniqueResult();
	}
	

}
