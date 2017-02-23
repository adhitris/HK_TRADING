package com.hk.common.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.hk.base.dao.ModuleDAO;
import com.hk.base.dao.UserDAO;
import com.hk.base.entity.Module;
import com.hk.base.entity.User;
import com.hk.common.email.EmailContentProperty;
import com.hk.common.enumeration.KodeTransaksiEnum;
import com.hk.common.exception.CommonException;
import com.hk.common.exception.ServiceException;
import com.hk.common.factory.GridViewFactory;
import com.hk.common.pagination.AbstractGridViewQuery;
import com.hk.common.pagination.FilterAndPaging;
import com.hk.common.pagination.FilterCriteria;
import com.hk.common.pagination.Filterable;
import com.hk.common.report.Exporter;
import com.hk.common.util.CommonUtil;
import com.hk.common.util.DateUtil;
import com.hk.common.util.EmailSender;
import com.hk.common.util.UserUtil;



public abstract class AbstractService<T> {
	public Map<String, Object> search() throws ServiceException{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isCreate", UserUtil.getUserActive().getIsCreate());
		map.put("isUpdate", UserUtil.getUserActive().getIsUpdate());
		map.put("isDelete", UserUtil.getUserActive().getIsDelete());
		
		map.put("isCancel", UserUtil.getUserActive().getIsCancel());
		map.put("isPrint", UserUtil.getUserActive().getIsPrint());
		map.put("isReport", UserUtil.getUserActive().getIsReport());
		map.put("isConfirm", UserUtil.getUserActive().getIsConfirm());
		map.put("isUnconfirm", UserUtil.getUserActive().getIsUnconfirm());
		
		map.put("isSupervisor", UserUtil.getUserActive().getIsSupervisor());
		map.put("isSuperuser", UserUtil.getUserActive().getIsSuperuser());
		
		map.put("isReprint", UserUtil.getUserActive().getIsReprint());
		
		map.put("dateStart", DateUtil.getFirstDateOfCurrentMonth());
		map.put("dateFinish", DateUtil.getLastDateOfCurrentMonth());
		map.put("dateNow", DateUtil.defaultFormatDate(DateUtil.now()));
		map.put("countVersionJs", DateUtil.nowVersion());
		
		map.put("userId", UserUtil.getUsername());
		
		return map;
	}
}
