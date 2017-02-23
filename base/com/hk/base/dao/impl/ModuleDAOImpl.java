package com.hk.base.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Component;

import com.hk.base.dao.ModuleDAO;
import com.hk.base.entity.Module;
import com.hk.base.entity.User;
import com.hk.common.dao.AbstractDAO;
import com.hk.common.util.CommonUtil;

@Component
public class ModuleDAOImpl extends AbstractDAO<Module> implements ModuleDAO{

	@Override
	protected Class<Module> getDomainClass() {
		// TODO Auto-generated method stub
		return Module.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Module> comboGridFindParentLimitOffset(String criteriaName, Integer startRow, Integer endRow, String sidx, String sord, String searchTerm) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select x from Module x where x.id IS NOT NULL   ");
		buffer.append("and lower(x."+criteriaName+") like :search ");
		buffer.append("and x.status = '0' and ((x.module is not null) or (x.module is null)) ");
		if(sidx != ""){
			buffer.append("order by x."+sidx+" "+sord);
		}
		
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%"+searchTerm+"%");
		
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		
		return (List<Module>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> comboGridFindParent(String criteriaName, String sidx, String sord, String searchTerm) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select x from Module x where x.id IS NOT NULL   ");
		buffer.append("and lower(x."+criteriaName+") like :search ");
		buffer.append("and x.status = '0' and ((x.module is not null) or (x.module is null))");
		if(sidx != ""){
			buffer.append("order by x."+sidx+" "+sord);
		}
		
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%"+searchTerm+"%");
		
		return (List<Module>)query.list();
	}
	
	@Override
	public int comboGridFindParentSize(String criteriaName, String sidx,String sord, String searchTerm) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(x.id) from Module x where x.id IS NOT NULL   ");
		buffer.append("and lower(x."+criteriaName+") like :search ");
		buffer.append("and x.status = '0' and ((x.module is not null) or (x.module is null))");
		if(sidx != ""){
			buffer.append("order by x."+sidx+" "+sord);
		}
		
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%"+searchTerm+"%");
		
		return ((Long) query.uniqueResult()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> ParentNode() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select x from Module x where x.status = '0' and x.module is null and x.isActive =true order by x.urutan asc");
		
		Query query = getSession().createQuery(buffer.toString());
		
		return (List<Module>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> childNode(String parentId) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select x from Module x where x.module.id = :search1 and x.isActive =true order by x.urutan asc ");
		
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search1", parentId);
		
		return (List<Module>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> findModuleById(String id) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select m from User j ");
		buffer.append("join j.accessUserSet am ");
		buffer.append("join am.pk.module as m ");
		buffer.append("where j.id = :search1 and m.status='0' and m.module.id is null and m.isActive=true order by m.urutan asc");
		
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search1", id);
		
		return (List<Module>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> findModuleChildByIdParent(String id, String idParent) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select m from User j ");
		buffer.append("join j.accessUserSet am ");
		buffer.append("join am.pk.module as m ");
		buffer.append("where j.id = :search1 and m.module.id = :search2 and m.isActive=true  order by m.urutan asc");
		
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search1", id);
		query.setString("search2", idParent);
		
		return (List<Module>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> jqgridFindAll(String sidx, String sord, String searchTerm1, String searchTerm2, Date searchTerm3) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select x from Module x left join x.module m where x.id IS NOT NULL  ");
		if(searchTerm1 != ""){
			buffer.append("and lower(x.nama) like :search1 ");
		}
		if(searchTerm2 != ""){
			buffer.append("and lower(m.nama) like :search2 ");
		}
		if(searchTerm3 != null){
			buffer.append("and x.createDate = :search3 ");
		}
		
		if(sidx != ""){
			buffer.append("order by x."+sidx+" "+sord);
		}else{
			buffer.append("order by m.urutan, x.urutan asc ");
		}
		
		Query query = getSession().createQuery(buffer.toString());
		if(searchTerm1 != ""){
			query.setString("search1", "%"+searchTerm1+"%");
		}
		if(searchTerm2 != ""){
			query.setString("search2", "%"+searchTerm2+"%");
		}
		if(searchTerm3 != null){
			query.setDate("search3", searchTerm3);
		}
		
		return (List<Module>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> jqgridFindLimitOffset(Integer startRow, Integer endRow, String sidx, String sord, String searchTerm1, String searchTerm2, Date searchTerm3) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select x from Module x left join x.module m where x.id IS NOT NULL  ");
		if(searchTerm1 != ""){
			buffer.append("and lower(x.nama) like :search1 ");
		}
		if(searchTerm2 != ""){
			buffer.append("and lower(m.nama) like :search2 ");
		}
		if(searchTerm3 != null){
			buffer.append("and x.createDate = :search3 ");
		}
		
		if(sidx != ""){
			buffer.append("order by x."+sidx+" "+sord);
		}else{
			buffer.append("order by m.urutan, x.urutan asc ");
		}
		
		Query query = getSession().createQuery(buffer.toString());
		if(searchTerm1 != ""){
			query.setString("search1", "%"+searchTerm1+"%");
		}
		if(searchTerm2 != ""){
			query.setString("search2", "%"+searchTerm2+"%");
		}
		if(searchTerm3 != null){
			query.setDate("search3", searchTerm3);
		}
		
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		
		return (List<Module>)query.list();
	}

	@Override
	public int jqgridFindAllSize(String sidx, String sord, String searchTerm1,String searchTerm2, Date searchTerm3) {
StringBuffer buffer = new StringBuffer();
		
		buffer.append("select count(x.id) from Module x left join x.module m where x.id IS NOT NULL  ");
		if(searchTerm1 != ""){
			buffer.append("and lower(x.nama) like :search1 ");
		}
		if(searchTerm2 != ""){
			buffer.append("and lower(m.nama) like :search2 ");
		}
		if(searchTerm3 != null){
			buffer.append("and x.createDate = :search3 ");
		}
		
		if(sidx != ""){
			buffer.append("order by x."+sidx+" "+sord);
		}else{
			buffer.append("order by m.urutan, x.urutan asc ");
		}
		
		Query query = getSession().createQuery(buffer.toString());
		if(searchTerm1 != ""){
			query.setString("search1", "%"+searchTerm1+"%");
		}
		if(searchTerm2 != ""){
			query.setString("search2", "%"+searchTerm2+"%");
		}
		if(searchTerm3 != null){
			query.setDate("search3", searchTerm3);
		}
		
		return ((Long) query.uniqueResult()).intValue();
	}

	public Module findModuleByUrl(String url) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select m from Module m ");
		buffer.append("where m.url =:url ");
		
		Query query = getSession().createQuery(buffer.toString());
		query.setString("url", url+".html");
		query.setMaxResults(1);
		return (Module) query.uniqueResult();
	}
	
	@Override
	public Module findById(String id) {
		Session session = getSession();
		Transaction tx = null;
			try {
				tx = session.beginTransaction();
				Query query = session.createQuery("from Module domain where domain.id =:id    ");
				query.setString("id", id);
				tx.commit();
				Module m=(Module) query.uniqueResult();
				Hibernate.initialize(m.getAccessUserSet());
				return m;
				
			} catch (HibernateException e) {
				throw SessionFactoryUtils.convertHibernateAccessException(e);
			}finally {	
				if (session != null) {
					session.close();
				}
			}
		
	}
}
