package com.hk.master.service;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hk.base.dao.UserDAO;
import com.hk.base.entity.MataUang;
import com.hk.base.entity.User;
import com.hk.base.entityLog.AuditLog;
import com.hk.common.enumeration.ActionEnum;
import com.hk.common.exception.CommonException;
import com.hk.common.exception.ServiceException;
import com.hk.common.service.AbstractService;
import com.hk.common.util.ComboGridUtil;
import com.hk.common.util.CommonUtil;
import com.hk.common.util.DateUtil;
import com.hk.common.util.Md5Util;
import com.hk.common.util.UserUtil;
import com.hk.master.dto.UserGudangDTO;
import com.hk.master.dto.UserKasBankDTO;
import com.hk.master.dto.UserRoleDTO;
import com.hk.master.report.UserReport;

@Component
@Transactional(rollbackFor = Exception.class)
public class UserService extends AbstractService<User>{

	@Autowired
	private UserDAO userDAO;
	
	@Value("${reportDirectory}")
	String reportDirectory;

	
	
	public Map<String, Object> getList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listUser", userDAO.findAll());
		
		map.put("isSupervisor", UserUtil.getUserActive().getIsSupervisor());
		map.put("isReport", UserUtil.getUserActive().getIsReport());
		return map;
	}

	public Map<String, Object> preadd() {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		
		map.put("user", user);
		map.put("countVersionJs", DateUtil.nowVersion());

		return map;
	}

	@Transactional
	public Boolean add(User user) {
		String plainPassword = user.getId();
		String hasdedPassword = Md5Util.generateMd5(plainPassword);

		user.setPassword(hasdedPassword);
		user.setPasswordOtorisasi(null);
		user.setIsActive(true);
		
		if (!user.getIsActive()) {
			user.setDateNonActive(DateUtil.now());
		}
		user.setCreateBy(UserUtil.getUsername());
		user.setCreateDate(DateUtil.now());
		
		if(userDAO.insert(user)){
			userDAO.inserAudiLog(getAuditLog(user, ActionEnum.Saved.getVal()));
			return true;
		}else{
			return false;
		}
	}

	public Map<String, Object> preedit(String id) {
		User user = userDAO.findById(id);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		
		map.put("hiddenStatus", "update");
		map.put("countVersionJs", DateUtil.nowVersion());
		
		if(CommonUtil.isNotNullOrEmpty(user.getMruLimit())){
			map.put("mruLimit", user.getMruLimit());
		}
		
		return map;
	}


	@Transactional
	public void changePassword(String newPass) {
		try {
			User user1 = UserUtil.activeUser();
			String plain = newPass;
			String hasded = Md5Util.generateMd5(plain);

			user1.setPassword(hasded);
			userDAO.update(user1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public Map<String, Object> getJsonList(String page, String rows,
		String sidx, String sord, String searchTerm) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<User> allRole = userDAO.comboGridFind("firstName", sidx, sord,
				searchTerm);
		int totalRow = allRole.size();
		int totalPages = 0;
		int limit = Integer.parseInt(rows);
		int pageRequested = Integer.parseInt(page);

		if (totalRow > 0) {
			totalPages = (int) Math.ceil((double) totalRow / (double) limit);
		} else {
			totalPages = 0;
		}

		if (pageRequested > totalPages)
			pageRequested = totalPages;
		int rowStart = pageRequested * limit - limit;
		int rowEnd = limit;

		List<Object> list = new ArrayList<Object>();
		if (totalPages != 0) {
			map.put("page", page);
			map.put("total", totalPages);
			map.put("records", totalRow);
			for (User r : userDAO.comboGridFindLimitOffset("firstName",
					rowStart, rowEnd, sidx, sord, searchTerm)) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", r.getId());
				map2.put("nama", r.getNama());
				list.add(map2);
			}
			map.put("rows", list);

		} else {
			map.put("page", page);
			map.put("total", totalPages);
			map.put("records", totalRow);
			for (User r : allRole) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("id", r.getId());
				map2.put("nama", r.getNama());
				list.add(map2);
			}
			map.put("rows", list);
		}

		return map;
	}

	@Transactional
	public User getUserActive() {
		User user = userDAO.findById(UserUtil.getUsername());
		return user;
	}

	public JRDataSource getDataSource(Boolean statusUser, String id, String nama, String isCreate, String isDelete, String isUpdate, String isPrint, String isCancel, String isReport, String isSupervisor) {

		List<UserReport> listUserActive = new ArrayList<UserReport>();
		for (User user : userDAO.findByParam(statusUser,id, nama, isCreate,isDelete,isUpdate,isPrint,isCancel,isReport,isSupervisor)) {
			String hakAkses="";
			if(user.getIsCancel()){
				hakAkses+="C-";
			}if(user.getIsCreate()){
				hakAkses+="C-";
			}if(user.getIsDelete()){
				hakAkses+="D-";
			}if(user.getIsPrint()){
				hakAkses+=	"P-";
			}if(user.getIsReport()){
				hakAkses+="R-";
			}if(user.getIsSupervisor()){
				hakAkses+="S-";
			}if(user.getIsUpdate()){
				hakAkses+="U-";
			}
			
			UserReport userReport = new UserReport();
			userReport.setNama(user.getNama());
			userReport.setHakAkses(hakAkses);
			userReport.setUsername(user.getUsername());
			if(CommonUtil.isNotNullOrEmpty(user.getDateNonActive())){
				userReport.setKeterangan("Non Aktif Sejak : "+DateUtil.defaultFormatDate(user.getDateNonActive()));
			}else{
				userReport.setKeterangan("-");
			}
			userReport.setTanggalInput(DateUtil.defaultFormatDate(user.getCreateDate()));
			listUserActive.add(userReport);
			
		}
		JRDataSource ds = new JRBeanCollectionDataSource(listUserActive);
		return ds;
	}

	public Map<String, Object> getUserJqgridList(String page, String rows,
			String sidx, String sord, String id, String nama,
			String isCreate, String isDelete, String isUpdate, String isPrint,
			String isCancel, String isReport, String isSupervisor,
			String isActive, String startDate, String endDate, String departmentId, String isSuperuser, String isConfirm, String isUnconfirm, String isReprint) {
		// TODO Auto-generated method stub
			Map<String, Object> map = new HashMap<String, Object>();
		
			if(id == null){id = "";}
			if(nama == null){nama = "";}
			if(isCreate == null){isCreate = "";}
			if(isDelete == null){isDelete = "";}
			if(isUpdate == null){isUpdate = "";}
			if(isPrint == null){isPrint = "";}
			if(isCancel == null){isCancel = "";}
			if(isReport == null){isReport = "";}
			if(isSupervisor == null){isSupervisor = "";}
			if(isActive == null){isActive = "";}
			if(startDate == null){startDate = "";}
			if(endDate == null){endDate = "";}
			if(departmentId == null){departmentId = "";}
			if(isSuperuser == null){isSuperuser = "";}
			if(isConfirm == null){isConfirm = "";}
			if(isUnconfirm == null){isUnconfirm = "";}
			if(isReprint == null){isReprint = "";}
			
			List<User> all = userDAO.jqgridFindAll(sidx,sord,id,nama,isCreate,isDelete,isUpdate,isPrint,isCancel,isReport,isSupervisor,isActive,startDate, endDate,"",departmentId,isSuperuser,isConfirm,isUnconfirm,isReprint);
			int totalRow = all.size();
			int totalPages = 0;
			int limit = Integer.parseInt(rows);
			int pageRequested = Integer.parseInt(page);
			
			if( totalRow > 0 ) {
				totalPages = (int) Math.ceil((double)totalRow/(double)limit);
			} else {
				totalPages = 0;
			}
			
			if (pageRequested > totalPages) pageRequested=totalPages;
			int rowStart = pageRequested*limit-limit;
			int rowEnd = limit;
			
			List<Object> list = new ArrayList<Object>();
			if(totalPages!=0){
				map.put("page", page);
				map.put("total", totalPages);
				map.put("records", totalRow);
				for(User x : userDAO.jqgridFindLimitOffset(rowStart, rowEnd, sidx, sord,id,nama,isCreate,isDelete,isUpdate,isPrint,isCancel,isReport,isSupervisor,isActive,startDate, endDate,"",departmentId,isSuperuser,isConfirm,isUnconfirm,isReprint)){
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("id", x.getId());
					map2.put("username", x.getUsername());
					map2.put("nama", x.getNama());
					map2.put("createBy", x.getCreateBy());
					map2.put("version", x.getVersion());
					if(CommonUtil.isNotNullOrEmpty(x.getIsActive())){
						if(x.getIsActive()){
							map2.put("isActive", "Y");
						}else{
							map2.put("isActive", "N");
						}
					}else{
						map2.put("isActive", "-");
					}
					if(CommonUtil.isNotNullOrEmpty(x.getIsCancel())){
						if(x.getIsCancel()){
							map2.put("isCancel", "Y");
						}else{
							map2.put("isCancel", "N");
						}
					}else{
						map2.put("isCancel", "-");
					}
					if(CommonUtil.isNotNullOrEmpty(x.getIsCreate())){
						if(x.getIsCreate()){
							map2.put("isCreate", "Y");
						}else{
							map2.put("isCreate", "N");
						}
					}else{
						map2.put("isCreate", "-");
					}
					if(CommonUtil.isNotNullOrEmpty(x.getIsDelete())){
						if(x.getIsDelete()){
							map2.put("isDelete", "Y");
						}else{
							map2.put("isDelete", "N");
						}
					}else{
						map2.put("isDelete", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getIsPrint())){
						if(x.getIsPrint()){
							map2.put("isPrint", "Y");
						}else{
							map2.put("isPrint", "N");
						}
					}else{
						map2.put("isPrint", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getIsReport())){
						if(x.getIsReport()){
							map2.put("isReport", "Y");
						}else{
							map2.put("isReport", "N");
						}
					}else{
						map2.put("isReport", "-");
					}
					if(CommonUtil.isNotNullOrEmpty(x.getIsSupervisor())){
						if(x.getIsSupervisor()){
							map2.put("isSupervisor", "Y");
						}else{
							map2.put("isSupervisor", "N");
						}
					}else{
						map2.put("isSupervisor", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getIsSuperuser())){
						if(x.getIsSuperuser()){
							map2.put("isSuperuser", "Y");
						}else{
							map2.put("isSuperuser", "N");
						}
					}else{
						map2.put("isSuperuser", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getIsConfirm())){
						if(x.getIsConfirm()){
							map2.put("isConfirm", "Y");
						}else{
							map2.put("isConfirm", "N");
						}
					}else{
						map2.put("isConfirm", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getIsUnconfirm())){
						if(x.getIsUnconfirm()){
							map2.put("isUnconfirm", "Y");
						}else{
							map2.put("isUnconfirm", "N");
						}
					}else{
						map2.put("isUnconfirm", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getIsUpdate())){
						if(x.getIsUpdate()){
							map2.put("isUpdate", "Y");
						}else{
							map2.put("isUpdate", "N");
						}
					}else{
						map2.put("isUpdate", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getIsReprint())){
						if(x.getIsReprint()){
							map2.put("isReprint", "Y");
						}else{
							map2.put("isReprint", "N");
						}
					}else{
						map2.put("isReprint", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getCreateDate())){
						map2.put("createDate", DateUtil.defaultFormatDate(x.getCreateDate()));
					}else{
						map2.put("createDate", "unknown");
					}
					list.add(map2);
				}
				map.put("rows", list);
				
			}else {
				map.put("page", page);
				map.put("total", totalPages);
				map.put("records", totalRow);
				for(User x : all){
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("id", x.getId());
					map2.put("username", x.getUsername());
					map2.put("nama", x.getNama());
					map2.put("createBy", x.getCreateBy());
					map2.put("version", x.getVersion());
					if(CommonUtil.isNotNullOrEmpty(x.getIsActive())){
						if(x.getIsActive()){
							map2.put("isActive", "Y");
						}else{
							map2.put("isActive", "N");
						}
					}else{
						map2.put("isActive", "-");
					}
					if(CommonUtil.isNotNullOrEmpty(x.getIsCancel())){
						if(x.getIsCancel()){
							map2.put("isCancel", "Y");
						}else{
							map2.put("isCancel", "N");
						}
					}else{
						map2.put("isCancel", "-");
					}
					if(CommonUtil.isNotNullOrEmpty(x.getIsCreate())){
						if(x.getIsCreate()){
							map2.put("isCreate", "Y");
						}else{
							map2.put("isCreate", "N");
						}
					}else{
						map2.put("isCreate", "-");
					}
					if(CommonUtil.isNotNullOrEmpty(x.getIsDelete())){
						if(x.getIsDelete()){
							map2.put("isDelete", "Y");
						}else{
							map2.put("isDelete", "N");
						}
					}else{
						map2.put("isDelete", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getIsPrint())){
						if(x.getIsPrint()){
							map2.put("isPrint", "Y");
						}else{
							map2.put("isPrint", "N");
						}
					}else{
						map2.put("isPrint", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getIsReport())){
						if(x.getIsReport()){
							map2.put("isReport", "Y");
						}else{
							map2.put("isReport", "N");
						}
					}else{
						map2.put("isReport", "-");
					}
					if(CommonUtil.isNotNullOrEmpty(x.getIsSupervisor())){
						if(x.getIsSupervisor()){
							map2.put("isSupervisor", "Y");
						}else{
							map2.put("isSupervisor", "N");
						}
					}else{
						map2.put("isSupervisor", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getIsSuperuser())){
						if(x.getIsSuperuser()){
							map2.put("isSuperuser", "Y");
						}else{
							map2.put("isSuperuser", "N");
						}
					}else{
						map2.put("isSuperuser", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getIsConfirm())){
						if(x.getIsConfirm()){
							map2.put("isConfirm", "Y");
						}else{
							map2.put("isConfirm", "N");
						}
					}else{
						map2.put("isConfirm", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getIsUnconfirm())){
						if(x.getIsUnconfirm()){
							map2.put("isUnconfirm", "Y");
						}else{
							map2.put("isUnconfirm", "N");
						}
					}else{
						map2.put("isUnconfirm", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getIsUpdate())){
						if(x.getIsUpdate()){
							map2.put("isUpdate", "Y");
						}else{
							map2.put("isUpdate", "N");
						}
					}else{
						map2.put("isUpdate", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getIsReprint())){
						if(x.getIsReprint()){
							map2.put("isReprint", "Y");
						}else{
							map2.put("isReprint", "N");
						}
					}else{
						map2.put("isReprint", "-");
					}
					
					if(CommonUtil.isNotNullOrEmpty(x.getCreateDate())){
						map2.put("createDate", DateUtil.defaultFormatDate(x.getCreateDate()));
					}else{
						map2.put("createDate", "unknown");
					}
					list.add(map2);
				}
				map.put("rows", list);
			}
			
			return map;
	}
	public List<UserReport> listUser(){
		List<UserReport> listUserActive = new ArrayList<UserReport>();
		for (User user : userDAO.findAll()) {
			String hakAkses="";
			if(user.getIsCancel()){
				hakAkses+="C-";
			}if(user.getIsCreate()){
				hakAkses+="C-";
			}if(user.getIsDelete()){
				hakAkses+="D-";
			}if(user.getIsPrint()){
				hakAkses+=	"P-";
			}if(user.getIsReport()){
				hakAkses+="R-";
			}if(user.getIsSupervisor()){
				hakAkses+="S-";
			}if(user.getIsUpdate()){
				hakAkses+="U-";
			}
			
			UserReport userReport = new UserReport();
			userReport.setNama(user.getNama());
			userReport.setHakAkses(hakAkses);
			userReport.setUsername(user.getUsername());
			if(CommonUtil.isNotNullOrEmpty(user.getDateNonActive())){
				userReport.setKeterangan("Non Aktif Sejak : "+DateUtil.defaultFormatDate(user.getDateNonActive()));
			}else{
				userReport.setKeterangan("-");
			}
			userReport.setTanggalInput(DateUtil.defaultFormatDate(user.getCreateDate()));
			listUserActive.add(userReport);
			
		}
		
		return listUserActive;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Boolean generetaPrintReport(){
		Boolean result=false;
//	    JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listUser());
//	  
//	      try {
//	    	  
//	    	 /*
//	    	  Map parameters = new HashMap();
//	    	  String printFileName=null;
//	    	  printFileName = JasperFillManager.fillReportToFile(sourceFileName,   parameters,  beanColDataSource);
//	    		if(printFileName != null){
//	    		   JasperPrintManager.printReport(printFileName, true);
//	    		   JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(printFileName, parameters);
//	    		   JasperViewer viewer = new JasperViewer(jprint, false);
//	    		   viewer.setVisible(true);
//	    		   result=true;
//	    		} else{
//	    			result=false;
//	    		}*/
//	    		
//	    		
//	    		
//	    		JRDataSource dataSource = getDataSource(true);
//
//				Map parameters = new HashMap();
//				parameters.put("id", 42);
//
//				JasperReport report = (JasperReport) JRLoader.loadObject(reportDirectory+"master/user/userReport.jasper");
//
//				JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters, dataSource);
//
//				JFrame frame = new JFrame("Report");
//				frame.pack();
//
//				// make the frame half the height and width
//				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//				frame.setSize(1024, 748);
//
//				// here's the part where i center the jframe on screen
//				frame.setLocationRelativeTo(null);
//				frame.getContentPane().add(new JRViewer(jasperPrint));
//				frame.setVisible(true);
//	    		
//	    		result=true;
//	      }catch (JRException e) {
//	         e.printStackTrace();
//	      }
//	     
	     return result;
	}

	public Boolean editIsActive(User user) throws CommonException {
		User userUpdate = userDAO.findById(user.getId());
		if (userUpdate.getIsActive()) {
			userUpdate.setIsActive(false);
			userUpdate.setVersion(user.getVersion());
			userUpdate.setDateNonActive(DateUtil.now());
			userUpdate.setLastUpdateBy(UserUtil.getUsername());
			userUpdate.setLastUpdateDate(DateUtil.now());
			if(userDAO.update(userUpdate)){
				userDAO.inserAudiLog(getAuditLog(userUpdate, ActionEnum.NonActived.getVal()));
				return true;
			}else{
				return false;
			}
		} else {
			userUpdate.setIsActive(true);
			userUpdate.setVersion(user.getVersion());
			userUpdate.setLastUpdateBy(UserUtil.getUsername());
			userUpdate.setLastUpdateDate(DateUtil.now());
			if(userDAO.update(userUpdate)){
				userDAO.inserAudiLog(getAuditLog(userUpdate, ActionEnum.Actived.getVal()));
				return true;
			}else{
				return false;
			}
		}
		
		
	}

	public AuditLog getAuditLog(User obj, String action) {
		AuditLog auditLog = new AuditLog();
		auditLog.setAction(action);
		auditLog.setCreatedDate(DateUtil.now());
		auditLog.setEntityId(obj.getId());
		auditLog.setEntityName(obj.getClass().toString());
		auditLog.setUsername(UserUtil.activeUser().getUsername());
		auditLog.setDetail("USER_ID : "+obj.getId());
		auditLog.setModule(obj.getClass().getSimpleName());
		return auditLog;
	}
	
	public AuditLog getAuditLogReport(User obj,String title,StringBuffer filter,String action) {
		AuditLog auditLog = new AuditLog();
		auditLog.setAction(action);
		auditLog.setCreatedDate(DateUtil.now());
		auditLog.setEntityId(filter.toString());
		auditLog.setEntityName(obj.getClass().toString());
		auditLog.setUsername(UserUtil.activeUser().getUsername());
		auditLog.setDetail("Download Report, "+title);
		auditLog.setModule(obj.getClass().getSimpleName());
		return auditLog;
	}

	public Boolean deleteById(String id) throws CommonException {
		User user = userDAO.findById(id);
		if(userDAO.delete(user)){
			userDAO.inserAudiLog(getAuditLog(user, ActionEnum.Deleted.getVal()));
			return true;
		}else{
			return false;
		}
	}

	public Boolean edit(User user) throws CommonException {
		User userUpdate=userDAO.findById(user.getId());
		userUpdate.setLastUpdateBy(UserUtil.getUsername());
		userUpdate.setLastUpdateDate(DateUtil.now());
		userUpdate.setNama(user.getNama());
		userUpdate.setIsCancel(user.getIsCancel());
		userUpdate.setIsCreate(user.getIsCreate());
		userUpdate.setIsDelete(user.getIsDelete());
		userUpdate.setIsPrint(user.getIsPrint());
		userUpdate.setIsReport(user.getIsReport());
		userUpdate.setIsConfirm(user.getIsConfirm());
		userUpdate.setIsUnconfirm(user.getIsUnconfirm());
		userUpdate.setIsSupervisor(user.getIsSupervisor());
		userUpdate.setIsSuperuser(user.getIsSuperuser());
		userUpdate.setIsUpdate(user.getIsUpdate());
		userUpdate.setIsReprint(user.getIsReprint());
		userUpdate.setNama(user.getNama());
		userUpdate.setMruLimit(user.getMruLimit());

		if(userDAO.saveUpdate(userUpdate)){
			userDAO.inserAudiLog(getAuditLog(user, ActionEnum.Updated.getVal()));
			return true;
		}else{
			return false;
		}
	}

	public Boolean editPasswordOtorisasi(User user) throws CommonException {
		User userUpdate=userDAO.findById(user.getId());

		String plainPasswordOto = user.getPasswordOtorisasi();
		String hasdedPasswordOto = Md5Util.generateMd5(plainPasswordOto);

		userUpdate.setPasswordOtorisasi(hasdedPasswordOto);
		userUpdate.setLastUpdateBy(UserUtil.getUsername());
		userUpdate.setLastUpdateDate(DateUtil.now());
		if(userDAO.update(userUpdate)){
			userDAO.inserAudiLog(getAuditLog(userUpdate, ActionEnum.Updated.getVal()));

			return true;
		}else{
			return false;
		}
	}
	
	public Boolean editPasswordLoginByAdmin(User user) throws CommonException {
		User userUpdate=userDAO.findById(user.getId());
		String plainPassword = user.getPassword();
		String hasdedPassword = Md5Util.generateMd5(plainPassword);
		userUpdate.setPassword(hasdedPassword);
		/*userUpdate.setLastUpdateBy(UserUtil.getUsername());
		userUpdate.setLastUpdateDate(DateUtil.now());*/
		if(userDAO.update(userUpdate)){
			userDAO.inserAudiLog(getAuditLog(userUpdate, ActionEnum.Updated.getVal()));

			return true;
		}else{
			return false;
		}
	}
	
	public Boolean editPasswordLoginByUser(User user) throws CommonException {
		User userUpdate=userDAO.findById(user.getId());
		String plainPassword = user.getPassword();
		String hasdedPassword = Md5Util.generateMd5(plainPassword);
		userUpdate.setPassword(hasdedPassword);
		/*userUpdate.setLastUpdateBy(UserUtil.getUsername());
		userUpdate.setLastUpdateDate(DateUtil.now());*/
		if(userDAO.update(userUpdate)){
			userDAO.inserAudiLog(getAuditLog(userUpdate, ActionEnum.Updated.getVal()));

			return true;
		}else{
			return false;
		}
	}

	public Map<String, Object> preChangePassword() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", UserUtil.getUserActive().getUsername());
		map.put("countVersionJs", DateUtil.nowVersion());
		return map;
	}

	public Boolean checkOldPassword(String password, String userId) {
		// TODO Auto-generated method stub
		User user =userDAO.findById(userId);
		String passwordLamaInput = Md5Util.generateMd5(password);
		if(passwordLamaInput.equals(user.getPassword())){
			return true;
		}else{
			return false;
		}
	}

	public JRDataSource getDataSourceLabel(String id) {
		// TODO Auto-generated method stub
		List<User> listUser = userDAO.findByIdList(id);
		JRDataSource ds = new JRBeanCollectionDataSource(listUser);
		return ds;
	}


}
