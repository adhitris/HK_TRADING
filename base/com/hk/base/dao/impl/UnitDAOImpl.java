package com.hk.base.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.hk.base.dao.UnitDAO;
import com.hk.base.entity.Unit;
import com.hk.common.dao.AbstractDAO;
import com.hk.common.util.CommonUtil;


@SuppressWarnings("unchecked")
@Component
public class UnitDAOImpl extends AbstractDAO<Unit> implements UnitDAO{

	@Override
	protected Class<Unit> getDomainClass() {
		return Unit.class;
	}


	@Override
	public List<Unit> jqgridFindAll(String sidx, String sord,String id,String nama,String isActive) {

		StringBuffer buffer = new StringBuffer();

		buffer.append("select x from Unit x where x.id IS NOT NULL  ");
		
		if (id != "") {
			buffer.append("and lower(x.id) like :id ");
		}
		
		if (nama != "") {
			buffer.append("and lower(x.nama) like :nama ");
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
		
		if(nama != ""){
			query.setString("nama", "%" + nama.toLowerCase() + "%");
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
		
		return (List<Unit>) query.list();
	}

	@Override
	public List<Unit> jqgridFindLimitOffset(int rowStart, int rowEnd,String sidx, String sord, String id,String nama,String isActive) {


		StringBuffer buffer = new StringBuffer();

		buffer.append("select x from Unit x where x.id IS NOT NULL  ");
		
		if (id != "") {
			buffer.append("and lower(x.id) like :id ");
		}
		
		if (nama != "") {
			buffer.append("and lower(x.nama) like :nama ");
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
		
		if(nama != ""){
			query.setString("nama", "%" + nama.toLowerCase() + "%");
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

		return (List<Unit>) query.list();
	
	}


	@Override
	public int jqgridFindAllSize(String sidx, String sord, String id,String nama, String isActive) {
		
		StringBuffer buffer = new StringBuffer();

		buffer.append("select count(x.id) from Unit x where x.id IS NOT NULL  ");
		
		if (id != "") {
			buffer.append("and lower(x.id) like :id ");
		}
		
		if (nama != "") {
			buffer.append("and lower(x.nama) like :nama ");
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
		
		if(nama != ""){
			query.setString("nama", "%" + nama.toLowerCase() + "%");
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

	public List<Unit> comboGridFindNotInInisial(String criteriaName, String sidx,String sord, String searchTerm) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		buffer.append("select unit from Unit unit where unit.id NOT IN (select init.unitId from Inisial init) ");
		buffer.append(" and lower(unit.nama) like :search ");
		

		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%" + searchTerm + "%");
		

		return (List<Unit>) query.list();
	}

	public List<Unit> comboGridFindNotInInisialLimitOffset(String criteriaName,int rowStart, int rowEnd, String sidx, String sord,String searchTerm) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		buffer.append("select unit from Unit unit where unit.id NOT IN (select init.unitId from Inisial init) ");
		buffer.append(" and lower(unit.nama) like :search ");
		

		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%" + searchTerm + "%");
		
		
		query.setFirstResult(rowStart);
		query.setMaxResults(rowEnd);

		return (List<Unit>) query.list();
	}

	public int comboGridFindNotInInisialSize(String criteriaName, String sidx,String sord, String searchTerm) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(unit.id) from Unit unit where unit.id NOT IN (select init.unitId from Inisial init) ");
		buffer.append(" and lower(unit.nama) like :search ");
		

		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%" + searchTerm + "%");
		

		return ((Long) query.uniqueResult()).intValue();
	}
	
	public int comboGridFindNotInInisialBySOSize(String string, String sidx, String sord, String searchTerm,
			String orderJualKainHdrId) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(distinct dtl.unitId) from OrderJualKainDtl dtl join dtl.orderJualKainHdr hdr where dtl.unitId NOT IN (select init.unitId from Inisial init) and dtl.isCancel =false ");
		buffer.append(" and lower(dtl.unit.nama) like :search ");
		
		if(CommonUtil.isNotNullOrEmpty(orderJualKainHdrId)){
			buffer.append(" and hdr.id =:orderJualKainHdrId ");
		}

		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%" + searchTerm + "%");
		
		if(CommonUtil.isNotNullOrEmpty(orderJualKainHdrId)){
			query.setString("orderJualKainHdrId", orderJualKainHdrId);
		}

		return ((Long) query.uniqueResult()).intValue();
	}
	
	public List<Unit> comboGridFindNotInInisialBySOLimitOffset(String string, int rowStart, int rowEnd, String sidx, String sord,
			String searchTerm, String orderJualKainHdrId) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		buffer.append("select dtl.unit from OrderJualKainDtl dtl join dtl.orderJualKainHdr hdr where dtl.unitId NOT IN (select init.unitId from Inisial init) and dtl.isCancel =false ");
		buffer.append(" and lower(dtl.unit.nama) like :search ");
		
		if(CommonUtil.isNotNullOrEmpty(orderJualKainHdrId)){
			buffer.append(" and hdr.id =:orderJualKainHdrId ");
		}

		buffer.append(" group by dtl.unitId ");
		
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%" + searchTerm + "%");
		
		if(CommonUtil.isNotNullOrEmpty(orderJualKainHdrId)){
			query.setString("orderJualKainHdrId", orderJualKainHdrId);
		}
		
		
		query.setFirstResult(rowStart);
		query.setMaxResults(rowEnd);

		return (List<Unit>) query.list();
	}
	
	public List<Unit> comboGridFindNotInInisialBySO(String string, String sidx, String sord, String searchTerm,
			String orderJualKainHdrId) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		buffer.append("select dtl.unit from OrderJualKainDtl dtl join dtl.orderJualKainHdr hdr where dtl.unitId NOT IN (select init.unitId from Inisial init) and dtl.isCancel =false ");
		buffer.append(" and lower(dtl.unit.nama) like :search ");
		
		if(CommonUtil.isNotNullOrEmpty(orderJualKainHdrId)){
			buffer.append(" and hdr.id =:orderJualKainHdrId ");
		}

		buffer.append(" group by dtl.unitId ");
		
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%" + searchTerm + "%");
		
		if(CommonUtil.isNotNullOrEmpty(orderJualKainHdrId)){
			query.setString("orderJualKainHdrId", orderJualKainHdrId);
		}
		
		return (List<Unit>) query.list();
	}
}
