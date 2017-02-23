package com.hk.base.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.hk.base.dao.MataUangDAO;
import com.hk.base.entity.MataUang;
import com.hk.common.dao.AbstractDAO;


@SuppressWarnings("unchecked")
@Component
public class MataUangDAOImpl extends AbstractDAO<MataUang> implements MataUangDAO{

	@Override
	protected Class<MataUang> getDomainClass() {
		return MataUang.class;
	}


	@Override
	public List<MataUang> jqgridFindAll(String sidx, String sord,String id,String isActive) {

		StringBuffer buffer = new StringBuffer();

		buffer.append("select x from MataUang x where x.id IS NOT NULL  ");
		
		if (id != "") {
			buffer.append("and lower(x.id) like :id ");
		}
		
		if (isActive != "") {
			buffer.append("and x.isActive =:active ");
		}

		if (sidx != "") {
			buffer.append("order by x." + sidx + " " + sord);
		} else {
			buffer.append("order by x.createDate desc ");
		}

		Query query = getSession().createQuery(buffer.toString());
		
		if(id != ""){
			query.setString("id", "%" + id.toLowerCase() + "%");
		}
		
		if(isActive != ""){
			Boolean activeBolean=null;
			if(Integer.parseInt(isActive)==1){
				activeBolean=true;
			}else{
				activeBolean=false;
			}
			query.setBoolean("active", activeBolean);
		}
		
		query.setMaxResults(10000);

		return (List<MataUang>) query.list();
	}

	@Override
	public List<MataUang> jqgridFindLimitOffset(int rowStart, int rowEnd,String sidx, String sord, String id,String isActive) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();

		buffer.append("select x from MataUang x where x.id IS NOT NULL  ");
		
		if (id != "") {
			buffer.append("and lower(x.id) like :id ");
		}
		
		if (isActive != "") {
			buffer.append("and x.isActive =:active ");
		}

		if (sidx != "") {
			buffer.append("order by x." + sidx + " " + sord);
		} else {
			buffer.append("order by x.createDate desc ");
		}

		Query query = getSession().createQuery(buffer.toString());
		
		if(id != ""){
			query.setString("id", "%" + id.toLowerCase() + "%");
		}
		
		if(isActive != ""){
			Boolean activeBolean=null;
			if(Integer.parseInt(isActive)==1){
				activeBolean=true;
			}else{
				activeBolean=false;
			}
			query.setBoolean("active", activeBolean);
		}

		query.setFirstResult(rowStart);
		query.setMaxResults(rowEnd);

		return (List<MataUang>) query.list();
	
	}


	@Override
	public int jqgridFindAllSize(String sidx, String sord, String id,String isActive) {
		StringBuffer buffer = new StringBuffer();

		buffer.append("select count(x.id) from MataUang x where x.id IS NOT NULL  ");
		
		if (id != "") {
			buffer.append("and lower(x.id) like :id ");
		}
		
		if (isActive != "") {
			buffer.append("and x.isActive =:active ");
		}

		if (sidx != "") {
			buffer.append("order by x." + sidx + " " + sord);
		} else {
			buffer.append("order by x.createDate desc ");
		}

		Query query = getSession().createQuery(buffer.toString());
		
		if(id != ""){
			query.setString("id", "%" + id.toLowerCase() + "%");
		}
		
		if(isActive != ""){
			Boolean activeBolean=null;
			if(Integer.parseInt(isActive)==1){
				activeBolean=true;
			}else{
				activeBolean=false;
			}
			query.setBoolean("active", activeBolean);
		}

		return ((Long) query.uniqueResult()).intValue();
	}

	
}
