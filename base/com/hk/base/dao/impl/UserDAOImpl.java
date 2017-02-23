package com.hk.base.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Component;

import com.hk.base.dao.UserDAO;
import com.hk.base.entity.User;
import com.hk.common.dao.AbstractDAO;
import com.hk.common.util.CommonUtil;

@SuppressWarnings("unchecked")
@Component
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

	@Override
	protected Class<User> getDomainClass() {
		// TODO Auto-generated method stub
		return User.class;
	}

	@Override
	public List<User> findByStatusUser(Boolean statusUser) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("select x from User x where x.id IS NOT NULL   and x.isActive =:statusUser ");
		Query query = getSession().createQuery(buffer.toString());
		query.setBoolean("statusUser", statusUser);

		return (List<User>) query.list();
	}
	
	@Override
	public List<User> findByParam(Boolean statusUser, String id, String nama,
			String isCreate, String isDelete, String isUpdate, String isPrint,
			String isCancel, String isReport, String isSupervisor) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("select x from User x where x.id IS NOT NULL ");
		
		if(CommonUtil.isNotNullOrEmpty(statusUser)){
			buffer.append("and x.isActive =:statusUser ");
		}
		if(CommonUtil.isNotNullOrEmpty(id)){
			buffer.append("and lower(x.id) like :id ");
		}
		if(CommonUtil.isNotNullOrEmpty(nama)){
			buffer.append("and lower(x.nama) like :nama ");
		}
		if(CommonUtil.isNotNullOrEmpty(isCreate)){
			buffer.append("and x.isCreate =:isCreate ");
		}
		if(CommonUtil.isNotNullOrEmpty(isDelete)){
			buffer.append("and x.isDelete =:isDelete ");
		}
		if(CommonUtil.isNotNullOrEmpty(isUpdate)){
			buffer.append("and x.isUpdate =:isUpdate ");
		}
		if(CommonUtil.isNotNullOrEmpty(isPrint)){
			buffer.append("and x.isPrint =:isPrint ");
		}
		if(CommonUtil.isNotNullOrEmpty(isCancel)){
			buffer.append("and x.isCancel =:isCancel ");
		}
		if(CommonUtil.isNotNullOrEmpty(isReport)){
			buffer.append("and x.isReport =:isReport ");
		}
		if(CommonUtil.isNotNullOrEmpty(isSupervisor)){
			buffer.append("and x.isSupervisor =:isSupervisor ");
		}
		Query query = getSession().createQuery(buffer.toString());
		if(CommonUtil.isNotNullOrEmpty(statusUser)){
			query.setBoolean("statusUser", statusUser);
		}
		
		if(CommonUtil.isNotNullOrEmpty(id)){
			query.setString("id", "%"+id+"%");
		}
		if(CommonUtil.isNotNullOrEmpty(nama)){
			query.setString("nama", "%"+nama+"%");
		}
		if(CommonUtil.isNotNullOrEmpty(isReport)){
			Boolean reportBolean=null;
			if(Integer.parseInt(isReport)==1){
				reportBolean=true;
			}else{
				reportBolean=false;
			}
			query.setBoolean("isReport", reportBolean);
		}
		if(CommonUtil.isNotNullOrEmpty(isCancel)){
			Boolean cancelBolean=null;
			if(Integer.parseInt(isCancel)==1){
				cancelBolean=true;
			}else{
				cancelBolean=false;
			}
			query.setBoolean("isCancel", cancelBolean);
		}
		if(CommonUtil.isNotNullOrEmpty(isDelete)){
			Boolean deleteBolean=null;
			if(Integer.parseInt(isDelete)==1){
				deleteBolean=true;
			}else{
				deleteBolean=false;
			}
			query.setBoolean("isDelete", deleteBolean);
		}
		if(CommonUtil.isNotNullOrEmpty(isCreate)){
			Boolean createBolean=null;
			if(Integer.parseInt(isCreate)==1){
				createBolean=true;
			}else{
				createBolean=false;
			}
			query.setBoolean("isCreate", createBolean);
		}
		if(CommonUtil.isNotNullOrEmpty(isUpdate)){
			Boolean updateBolean=null;
			if(Integer.parseInt(isUpdate)==1){
				updateBolean=true;
			}else{
				updateBolean=false;
			}
			query.setBoolean("isUpdate", updateBolean);
		}
		if(CommonUtil.isNotNullOrEmpty(isPrint)){
			Boolean isPrintBolean=null;
			if(Integer.parseInt(isPrint)==1){
				isPrintBolean=true;
			}else{
				isPrintBolean=false;
			}
			query.setBoolean("isPrint", isPrintBolean);
		}
		
		if(CommonUtil.isNotNullOrEmpty(isSupervisor)){
			Boolean supervisorBolean=null;
			if(Integer.parseInt(isSupervisor)==1){
				supervisorBolean=true;
			}else{
				supervisorBolean=false;
			}
			query.setBoolean("isSupervisor", supervisorBolean);
		}

		return (List<User>) query.list();
	}



	@Override
	public User findByIdSingle(String id) {
		StringBuffer buffer = new StringBuffer();

		buffer.append("select u from User u where u.id=:id ");
	
		Query query = getSession().createQuery(buffer.toString());
		query.setString("id", id);

		return (User) query.uniqueResult();
	}


	@Override
	public User findById(String id) {
		Session session = getSession();
		Transaction tx = null;
			try {
				tx = session.beginTransaction();
				Query query = session.createQuery("from User domain where domain.id =:id    ");
				query.setString("id", id);
				tx.commit();
				User u=(User) query.uniqueResult();
				Hibernate.initialize(u.getAccessUserSet());
				return u;
				
			} catch (HibernateException e) {
				throw SessionFactoryUtils.convertHibernateAccessException(e);
			}finally {	
				if (session != null) {
					session.close();
				}
			}
		
	}

	@Override
	public List<User> jqgridFindAll(String sidx, String sord, String id,
			String nama, String isCreate, String isDelete,
			String isUpdate, String isPrint, String isCancel, String isReport,
			String isSupervisor, String isActive,  String startDate,
			String endDate, String moduleId, String departmentId, String isSuperuser, String isConfirm, String isUnconfirm, String isReprint) {
		
			StringBuffer buffer = new StringBuffer();
			buffer.append("select x from AccessUser y right join y.pk.user x where x.id IS NOT NULL ");
		
			if (id != "") {
				buffer.append("and concat(lower(x.nama),lower(x.id)) like :id ");
			}
			if (nama != "") {
				buffer.append("and concat(lower(x.nama),lower(x.id))  like :nama ");
			}
			if (CommonUtil.isNotNullOrEmpty(moduleId)) {
				buffer.append("and y.pk.module.id =:moduleId ");
			}
			if (isActive != "") {
				buffer.append("and x.isActive = :isActive ");
			}
			if (isCancel != "") {
				buffer.append("and x.isCancel = :isCancel ");
			}
			if (isCreate != "") {
				buffer.append("and x.isCreate =:isCreate ");
			}
			if (isDelete != "") {
				buffer.append("and x.isDelete = :isDelete ");
			}
			if (isPrint != "") {
				buffer.append("and x.isPrint = :isPrint ");
			}
			if (isReport != "") {
				buffer.append("and x.isReport = :isReport ");
			}
			if (isConfirm != "") {
				buffer.append("and x.isConfirm = :isConfirm ");
			}
			if (isUnconfirm != "") {
				buffer.append("and x.isUnconfirm = :isUnconfirm ");
			}
			if (isSuperuser != "") {
				buffer.append("and x.isSuperuser = :isSuperuser ");
			}
			if (isSupervisor != "") {
				buffer.append("and x.isSupervisor = :isSupervisor ");
			}
			if (isUpdate != "") {
				buffer.append("and x.isUpdate = :isUpdate ");
			}
			if (isReprint != "") {
				buffer.append("and x.isReprint = :isReprint ");
			}
			
			if (departmentId != "") {
				buffer.append("and x.pegawai.departmentId = :departmentId ");
			}
			
			buffer.append("group by x.id ");
			
			if (sidx != "") {
				buffer.append("order by x." + sidx + " " + sord);
			} else {
				buffer.append("order by x.createDate desc ");
			}
	
			Query query = getSession().createQuery(buffer.toString());
			
			if(id != ""){
				query.setString("id", "%"+id+"%");
			}
			if(nama != ""){
				query.setString("nama",  "%"+nama+"%");
			}
			if(CommonUtil.isNotNullOrEmpty(moduleId)){
				query.setString("moduleId", moduleId);
			}
			if(isReport != ""){
				Boolean reportBolean=null;
				if(Integer.parseInt(isReport)==1){
					reportBolean=true;
				}else{
					reportBolean=false;
				}
				query.setBoolean("isReport", reportBolean);
			}
			if(isCancel != ""){
				Boolean cancelBolean=null;
				if(Integer.parseInt(isCancel)==1){
					cancelBolean=true;
				}else{
					cancelBolean=false;
				}
				query.setBoolean("isCancel", cancelBolean);
			}
			if(isDelete != ""){
				Boolean deleteBolean=null;
				if(Integer.parseInt(isDelete)==1){
					deleteBolean=true;
				}else{
					deleteBolean=false;
				}
				query.setBoolean("isDelete", deleteBolean);
			}
			if(isCreate != ""){
				Boolean createBolean=null;
				if(Integer.parseInt(isCreate)==1){
					createBolean=true;
				}else{
					createBolean=false;
				}
				query.setBoolean("isCreate", createBolean);
			}
			if(isUpdate != ""){
				Boolean updateBolean=null;
				if(Integer.parseInt(isUpdate)==1){
					updateBolean=true;
				}else{
					updateBolean=false;
				}
				query.setBoolean("isUpdate", updateBolean);
			}
			if(isPrint != ""){
				Boolean isPrintBolean=null;
				if(Integer.parseInt(isPrint)==1){
					isPrintBolean=true;
				}else{
					isPrintBolean=false;
				}
				query.setBoolean("isPrint", isPrintBolean);
			}
			if(isConfirm != ""){
				Boolean isConfirmBolean=null;
				if(Integer.parseInt(isConfirm)==1){
					isConfirmBolean=true;
				}else{
					isConfirmBolean=false;
				}
				query.setBoolean("isConfirm", isConfirmBolean);
			}
			if(isUnconfirm != ""){
				Boolean isUnconfirmBolean=null;
				if(Integer.parseInt(isUnconfirm)==1){
					isUnconfirmBolean=true;
				}else{
					isUnconfirmBolean=false;
				}
				query.setBoolean("isUnconfirm", isUnconfirmBolean);
			}
			if(isSuperuser != ""){
				Boolean isSuperuserBolean=null;
				if(Integer.parseInt(isSuperuser)==1){
					isSuperuserBolean=true;
				}else{
					isSuperuserBolean=false;
				}
				query.setBoolean("isSuperuser", isSuperuserBolean);
			}
			if(isSupervisor != ""){
				Boolean supervisorBolean=null;
				if(Integer.parseInt(isSupervisor)==1){
					supervisorBolean=true;
				}else{
					supervisorBolean=false;
				}
				query.setBoolean("isSupervisor", supervisorBolean);
			}
			
			if(isActive != ""){
				Boolean activeBoolean=null;
				if(Integer.parseInt(isActive)==1){
					activeBoolean=true;
				}else{
					activeBoolean=false;
				}
				query.setBoolean("isActive", activeBoolean);
			}
			
			if(isReprint != ""){
				Boolean reprintBoolean=null;
				if(Integer.parseInt(isReprint)==1){
					reprintBoolean=true;
				}else{
					reprintBoolean=false;
				}
				query.setBoolean("isReprint", reprintBoolean);
			}
			
			if(CommonUtil.isNotNullOrEmpty(departmentId)){
				query.setString("departmentId", departmentId);
			}
			
			return (List<User>) query.list();
	}

	@Override
	public List<User> jqgridFindLimitOffset(int rowStart, int rowEnd,
			String sidx, String sord, String id, String nama,
			String isCreate, String isDelete, String isUpdate, String isPrint,
			String isCancel, String isReport, String isSupervisor,
			String isActive,  String startDate, String endDate, String moduleId, String departmentId, String isSuperuser, String isConfirm, String isUnconfirm, String isReprint) {
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("select x from AccessUser y right join y.pk.user x where x.id IS NOT NULL ");
		
			if (id != "") {
				buffer.append("and concat(lower(x.nama),lower(x.id)) like :id ");
			}
			if (nama != "") {
				buffer.append("and concat(lower(x.nama),lower(x.id))  like :nama ");
			}
			if (CommonUtil.isNotNullOrEmpty(moduleId)) {
				buffer.append("and y.pk.module.id =:moduleId ");
			}
			if (isActive != "") {
				buffer.append("and x.isActive = :isActive ");
			}
			if (isCancel != "") {
				buffer.append("and x.isCancel = :isCancel ");
			}
			if (isCreate != "") {
				buffer.append("and x.isCreate =:isCreate ");
			}
			if (isDelete != "") {
				buffer.append("and x.isDelete = :isDelete ");
			}
			if (isPrint != "") {
				buffer.append("and x.isPrint = :isPrint ");
			}
			if (isReport != "") {
				buffer.append("and x.isReport = :isReport ");
			}
			if (isConfirm != "") {
				buffer.append("and x.isConfirm = :isConfirm ");
			}
			if (isUnconfirm != "") {
				buffer.append("and x.isUnconfirm = :isUnconfirm ");
			}
			if (isSuperuser != "") {
				buffer.append("and x.isSuperuser = :isSuperuser ");
			}
			if (isSupervisor != "") {
				buffer.append("and x.isSupervisor = :isSupervisor ");
			}
			if (isUpdate != "") {
				buffer.append("and x.isUpdate = :isUpdate ");
			}
			if (isReprint != "") {
				buffer.append("and x.isReprint = :isReprint ");
			}
			
			if (departmentId != "") {
				buffer.append("and x.pegawai.departmentId = :departmentId ");
			}
			
			buffer.append("group by x.id ");
			
			if (sidx != "") {
				buffer.append("order by x." + sidx + " " + sord);
			} else {
				buffer.append("order by x.createDate desc ");
			}
	
			Query query = getSession().createQuery(buffer.toString());
			
			if(id != ""){
				query.setString("id", "%"+id+"%");
			}
			if(nama != ""){
				query.setString("nama",  "%"+nama+"%");
			}
			if(CommonUtil.isNotNullOrEmpty(moduleId)){
				query.setString("moduleId", moduleId);
			}
			if(isReport != ""){
				Boolean reportBolean=null;
				if(Integer.parseInt(isReport)==1){
					reportBolean=true;
				}else{
					reportBolean=false;
				}
				query.setBoolean("isReport", reportBolean);
			}
			if(isCancel != ""){
				Boolean cancelBolean=null;
				if(Integer.parseInt(isCancel)==1){
					cancelBolean=true;
				}else{
					cancelBolean=false;
				}
				query.setBoolean("isCancel", cancelBolean);
			}
			if(isDelete != ""){
				Boolean deleteBolean=null;
				if(Integer.parseInt(isDelete)==1){
					deleteBolean=true;
				}else{
					deleteBolean=false;
				}
				query.setBoolean("isDelete", deleteBolean);
			}
			if(isCreate != ""){
				Boolean createBolean=null;
				if(Integer.parseInt(isCreate)==1){
					createBolean=true;
				}else{
					createBolean=false;
				}
				query.setBoolean("isCreate", createBolean);
			}
			if(isUpdate != ""){
				Boolean updateBolean=null;
				if(Integer.parseInt(isUpdate)==1){
					updateBolean=true;
				}else{
					updateBolean=false;
				}
				query.setBoolean("isUpdate", updateBolean);
			}
			if(isPrint != ""){
				Boolean isPrintBolean=null;
				if(Integer.parseInt(isPrint)==1){
					isPrintBolean=true;
				}else{
					isPrintBolean=false;
				}
				query.setBoolean("isPrint", isPrintBolean);
			}
			if(isConfirm != ""){
				Boolean isConfirmBolean=null;
				if(Integer.parseInt(isConfirm)==1){
					isConfirmBolean=true;
				}else{
					isConfirmBolean=false;
				}
				query.setBoolean("isConfirm", isConfirmBolean);
			}
			if(isUnconfirm != ""){
				Boolean isUnconfirmBolean=null;
				if(Integer.parseInt(isUnconfirm)==1){
					isUnconfirmBolean=true;
				}else{
					isUnconfirmBolean=false;
				}
				query.setBoolean("isUnconfirm", isUnconfirmBolean);
			}
			if(isSuperuser != ""){
				Boolean isSuperuserBolean=null;
				if(Integer.parseInt(isSuperuser)==1){
					isSuperuserBolean=true;
				}else{
					isSuperuserBolean=false;
				}
				query.setBoolean("isSuperuser", isSuperuserBolean);
			}
			if(isSupervisor != ""){
				Boolean supervisorBolean=null;
				if(Integer.parseInt(isSupervisor)==1){
					supervisorBolean=true;
				}else{
					supervisorBolean=false;
				}
				query.setBoolean("isSupervisor", supervisorBolean);
			}
			
			if(isActive != ""){
				Boolean activeBoolean=null;
				if(Integer.parseInt(isActive)==1){
					activeBoolean=true;
				}else{
					activeBoolean=false;
				}
				query.setBoolean("isActive", activeBoolean);
			}
			
			if(isReprint != ""){
				Boolean reprintBoolean=null;
				if(Integer.parseInt(isReprint)==1){
					reprintBoolean=true;
				}else{
					reprintBoolean=false;
				}
				query.setBoolean("isReprint", reprintBoolean);
			}
			
			if(CommonUtil.isNotNullOrEmpty(departmentId)){
				query.setString("departmentId", departmentId);
			}
			
			query.setFirstResult(rowStart);
			query.setMaxResults(rowEnd);
			
			return (List<User>) query.list();
	}
	
	@Override
	public int jqgridFindAllSize(String sidx, String sord, String id,
			String nama, String isCreate, String isDelete,
			String isUpdate, String isPrint, String isCancel, String isReport,
			String isSupervisor, String isActive, String startDate,
			String endDate, String moduleId, String departmentId, String isSuperuser, String isConfirm, String isUnconfirm) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(distinct x.id) from AccessUser y right join y.pk.user x where x.id IS NOT NULL ");
	
		if (id != "") {
			buffer.append("and concat(lower(x.nama),lower(x.id)) like :id ");
		}
		if (nama != "") {
			buffer.append("and concat(lower(x.nama),lower(x.id))  like :nama ");
		}
		if (CommonUtil.isNotNullOrEmpty(moduleId)) {
			buffer.append("and y.pk.module.id =:moduleId ");
		}
		if (isActive != "") {
			buffer.append("and x.isActive = :isActive ");
		}
		if (isCancel != "") {
			buffer.append("and x.isCancel = :isCancel ");
		}
		if (isCreate != "") {
			buffer.append("and x.isCreate =:isCreate ");
		}
		if (isDelete != "") {
			buffer.append("and x.isDelete = :isDelete ");
		}
		if (isPrint != "") {
			buffer.append("and x.isPrint = :isPrint ");
		}
		if (isReport != "") {
			buffer.append("and x.isReport = :isReport ");
		}
		if (isConfirm != "") {
			buffer.append("and x.isConfirm = :isConfirm ");
		}
		if (isUnconfirm != "") {
			buffer.append("and x.isUnconfirm = :isUnconfirm ");
		}
		if (isSuperuser != "") {
			buffer.append("and x.isSuperuser = :isSuperuser ");
		}
		if (isSupervisor != "") {
			buffer.append("and x.isSupervisor = :isSupervisor ");
		}
		if (isUpdate != "") {
			buffer.append("and x.isUpdate = :isUpdate ");
		}
		
		if (departmentId != "") {
			buffer.append("and x.pegawai.departmentId = :departmentId ");
		}
		
		if (sidx != "") {
			buffer.append("order by x." + sidx + " " + sord);
		} else {
			buffer.append("order by x.createDate desc ");
		}

		Query query = getSession().createQuery(buffer.toString());
		
		if(id != ""){
			query.setString("id", "%"+id+"%");
		}
		if(nama != ""){
			query.setString("nama",  "%"+nama+"%");
		}
		if(CommonUtil.isNotNullOrEmpty(moduleId)){
			query.setString("moduleId", moduleId);
		}
		if(isReport != ""){
			Boolean reportBolean=null;
			if(Integer.parseInt(isReport)==1){
				reportBolean=true;
			}else{
				reportBolean=false;
			}
			query.setBoolean("isReport", reportBolean);
		}
		if(isCancel != ""){
			Boolean cancelBolean=null;
			if(Integer.parseInt(isCancel)==1){
				cancelBolean=true;
			}else{
				cancelBolean=false;
			}
			query.setBoolean("isCancel", cancelBolean);
		}
		if(isDelete != ""){
			Boolean deleteBolean=null;
			if(Integer.parseInt(isDelete)==1){
				deleteBolean=true;
			}else{
				deleteBolean=false;
			}
			query.setBoolean("isDelete", deleteBolean);
		}
		if(isCreate != ""){
			Boolean createBolean=null;
			if(Integer.parseInt(isCreate)==1){
				createBolean=true;
			}else{
				createBolean=false;
			}
			query.setBoolean("isCreate", createBolean);
		}
		if(isUpdate != ""){
			Boolean updateBolean=null;
			if(Integer.parseInt(isUpdate)==1){
				updateBolean=true;
			}else{
				updateBolean=false;
			}
			query.setBoolean("isUpdate", updateBolean);
		}
		if(isPrint != ""){
			Boolean isPrintBolean=null;
			if(Integer.parseInt(isPrint)==1){
				isPrintBolean=true;
			}else{
				isPrintBolean=false;
			}
			query.setBoolean("isPrint", isPrintBolean);
		}
		if(isConfirm != ""){
			Boolean isConfirmBolean=null;
			if(Integer.parseInt(isConfirm)==1){
				isConfirmBolean=true;
			}else{
				isConfirmBolean=false;
			}
			query.setBoolean("isConfirm", isConfirmBolean);
		}
		if(isUnconfirm != ""){
			Boolean isUnconfirmBolean=null;
			if(Integer.parseInt(isUnconfirm)==1){
				isUnconfirmBolean=true;
			}else{
				isUnconfirmBolean=false;
			}
			query.setBoolean("isUnconfirm", isUnconfirmBolean);
		}
		if(isSuperuser != ""){
			Boolean isSuperuserBolean=null;
			if(Integer.parseInt(isSuperuser)==1){
				isSuperuserBolean=true;
			}else{
				isSuperuserBolean=false;
			}
			query.setBoolean("isSuperuser", isSuperuserBolean);
		}
		if(isSupervisor != ""){
			Boolean supervisorBolean=null;
			if(Integer.parseInt(isSupervisor)==1){
				supervisorBolean=true;
			}else{
				supervisorBolean=false;
			}
			query.setBoolean("isSupervisor", supervisorBolean);
		}
		
		if(isActive != ""){
			Boolean activeBoolean=null;
			if(Integer.parseInt(isActive)==1){
				activeBoolean=true;
			}else{
				activeBoolean=false;
			}
			query.setBoolean("isActive", activeBoolean);
		}
		
		if(CommonUtil.isNotNullOrEmpty(departmentId)){
			query.setString("departmentId", departmentId);
		}
		
		return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public List<User> comboGridFindFromSales(String criteriaName, String sidx,String sord, String searchTerm) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select x from "+ getDomainClass().getSimpleName()+ " x where x.id IS NOT NULL   and x.isActive=true ");
		buffer.append("and lower(x." + criteriaName + ") like :search and x.id not in ( Select u.userId as id from Sales u )");
		if (sidx != "") {
			buffer.append("order by x." + sidx + " " + sord);
		}
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%" + searchTerm + "%");

		return (List<User>) query.list();
	}

	@Override
	public List<User> comboGridFindFromSalesLimitOffset(String criteriaName,int rowStart, int rowEnd, String sidx, String sord,String searchTerm) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select x from "+ getDomainClass().getSimpleName()+ " x where x.id IS NOT NULL   and x.isActive=true ");
		buffer.append("and lower(x." + criteriaName + ") like :search and x.id not in ( Select u.userId as id from Sales u ) ");
		if (sidx != "") {
			buffer.append("order by x." + sidx + " " + sord);
		}
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%" + searchTerm + "%");

		query.setFirstResult(rowStart);
		query.setMaxResults(rowEnd);
		return (List<User>) query.list();
	}

	@Override
	public int comboGridFindFromSalesSize(String criteriaName, String sidx,String sord, String searchTerm) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(x.id) from "+ getDomainClass().getSimpleName()+ " x where x.id IS NOT NULL   and x.isActive=true ");
		buffer.append("and lower(x." + criteriaName + ") like :search and x.id not in ( Select u.userId as id from Sales u ) ");
		if (sidx != "") {
			buffer.append("order by x." + sidx + " " + sord);
		}
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%" + searchTerm + "%");

		return ((Long) query.uniqueResult()).intValue();
	}
	
	public List<User> comboGridFindFromSalesEdit(String criteriaName, String sidx,String sord, String searchTerm,String salesId) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select x from "+ getDomainClass().getSimpleName()+ " x where x.id IS NOT NULL   and x.isActive=true ");
		buffer.append("and lower(x." + criteriaName + ") like :search and x.id not in ( Select u.userId as id from Sales u where u.id !=:salesId )");
		if (sidx != "") {
			buffer.append("order by x." + sidx + " " + sord);
		}
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%" + searchTerm + "%");
		query.setString("salesId", salesId);

		return (List<User>) query.list();
	}
	
	public List<User> comboGridFindFromSalesLimitOffsetEdit(String criteriaName,int rowStart, int rowEnd, String sidx, String sord,String searchTerm,String salesId) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select x from "+ getDomainClass().getSimpleName()+ " x where x.id IS NOT NULL   and x.isActive=true ");
		buffer.append("and lower(x." + criteriaName + ") like :search and x.id not in ( Select u.userId as id from Sales u where u.id !=:salesId ) ");
		if (sidx != "") {
			buffer.append("order by x." + sidx + " " + sord);
		}
		Query query = getSession().createQuery(buffer.toString());
		query.setString("search", "%" + searchTerm + "%");
		query.setString("salesId", salesId);
		
		query.setFirstResult(rowStart);
		query.setMaxResults(rowEnd);
		return (List<User>) query.list();
	}
}
