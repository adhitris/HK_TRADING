package com.hk.base.dao.impl;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.hk.base.dao.AccessUserDAO;
import com.hk.base.entity.AccessUser;
import com.hk.base.entity.Module;
import com.hk.common.dao.AbstractDAO;
import com.hk.common.util.CommonUtil;

@Component
public class AccessUserDAOImpl extends AbstractDAO<AccessUser> implements
		AccessUserDAO {
	
	@Override
	protected Class<AccessUser> getDomainClass() {
		// TODO Auto-generated method stub
		return AccessUser.class;
	}

	
	@Override
	public void deleteByIdUser(String id) {
		Session session = getSession();
		try {
			Query query = session.createQuery("DELETE FROM "
					+ getDomainClass().getSimpleName()
					+ " x where x.pk.user.id =:id");
			query.setString("id", id).executeUpdate();
			// //System.out.println(rowCount);
		} catch (HibernateException e) {
			// throw SessionFactoryUtils.convertHibernateAccessException(e);
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteByIdModule(String id) {
		Session session = getSession();
		try {
			Query query = session.createQuery("DELETE FROM "
					+ getDomainClass().getSimpleName()
					+ " x where x.pk.module.id =:id");
			query.setString("id", id).executeUpdate();
			// //System.out.println(rowCount);
		} catch (HibernateException e) {
			// throw SessionFactoryUtils.convertHibernateAccessException(e);
			e.printStackTrace();
		}
	}

	@Override
	public Boolean findByUserModule(String url, String id) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("Select u.id from User u join u.accessUserSet au join au.pk.module m where m.path=:path and u.id=:id and m.isActive=true ");
		
		Query query = getSession().createQuery(buffer.toString());
		query.setString("path", url);
		query.setString("id", id);
	
		if(CommonUtil.isNotNullOrEmpty((String)query.uniqueResult())){
			return true;
		}else{
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> findModuleByUser(String id) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("Select m from User u join u.accessUserSet au join au.pk.module m where u.id=:id and m.isActive=true ");
		
		Query query = getSession().createQuery(buffer.toString());
		query.setString("id", id);
	
		return (List<Module>) query.list();
	}
	
	/*@Override
	public Boolean statusCreateByUserModule(String url, String id) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select am.isCreate from User r ");
		buffer.append("join r.accessUserSet am ");
		buffer.append("join am.pk.module as m ");
		buffer.append("where j.id = :search1 and m.path = :search2 and m.isActive=true ");
		
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search1", id);
		query.setString("search2", url);
		
		return (Boolean)query.uniqueResult();
	}
	
	@Override
	public Boolean statusUpdateByUserModule(String url, String id) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select am.isUpdate from User r ");
		buffer.append("join r.accessModuleSet am ");
		buffer.append("join am.pk.module as m ");
		buffer.append("where j.id = :search1 and m.path = :search2 and m.isActive=true ");
		
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search1", id);
		query.setString("search2", url);
		
		return (Boolean)query.uniqueResult();
	}*/
	
	@Override
	public Boolean findByModuleUser(String moduleId,String userId) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("Select u.id from User u join u.accessUserSet au join au.pk.module m where m.id =:moduleId and u.id =:userId ");
		
		Query query = getSession().createQuery(buffer.toString());
		query.setString("moduleId", moduleId);
		query.setString("userId", userId);
	
		if(CommonUtil.isNotNullOrEmpty((String)query.uniqueResult())){
			return true;
		}else{
			return false;
		}
	}
}
