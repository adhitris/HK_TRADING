package com.hk.master.mapper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hk.base.entity.User;
import com.hk.common.util.CommonUtil;

public class UserMapper {
	@Autowired
	
	public static User userMapper(HttpServletRequest request)throws IOException {

		User user = new User();
		String jsonBody = IOUtils.toString(request.getInputStream());
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonBody);
		if(CommonUtil.isNotNullOrEmpty((String) jsonObject.get("nama"))){
			user.setNama((String) jsonObject.get("nama"));
		}
		if(CommonUtil.isNotNullOrEmpty(jsonObject.get("mruLimit"))){
			user.setMruLimit(new Integer((String) jsonObject.get("mruLimit")));
		}
		if(CommonUtil.isNotNullOrEmpty((String) jsonObject.get("version"))){
			user.setVersion(Integer.parseInt((String) jsonObject.get("version")));
		}
		
		if(CommonUtil.isNotNullOrEmpty((String) jsonObject.get("id"))){
			user.setId((String) jsonObject.get("id"));
		}
		if(CommonUtil.isNotNullOrEmpty((String) jsonObject.get("passwordOtorisasi"))){
			user.setPasswordOtorisasi((String) jsonObject.get("passwordOtorisasi"));
		}
		if(CommonUtil.isNotNullOrEmpty(jsonObject.get("isCreate"))){
			user.setIsCreate(new Boolean(jsonObject.get("isCreate").toString()));
		}
		
		if(CommonUtil.isNotNullOrEmpty(jsonObject.get("isUpdate"))){
			user.setIsUpdate(new Boolean(jsonObject.get("isUpdate").toString()));
		}
		
		if(CommonUtil.isNotNullOrEmpty(jsonObject.get("isDelete"))){
			user.setIsDelete(new Boolean(jsonObject.get("isDelete").toString()));
		}
		
		if(CommonUtil.isNotNullOrEmpty(jsonObject.get("isCancel"))){
			user.setIsCancel(new Boolean(jsonObject.get("isCancel").toString()));
		}
		
		if(CommonUtil.isNotNullOrEmpty(jsonObject.get("isPrint"))){
			user.setIsPrint(new Boolean( jsonObject.get("isPrint").toString()));
		}
		
		if(CommonUtil.isNotNullOrEmpty(jsonObject.get("isReport"))){
			user.setIsReport(new Boolean( jsonObject.get("isReport").toString()));
		}
		
		if(CommonUtil.isNotNullOrEmpty(jsonObject.get("isConfirm"))){
			user.setIsConfirm(new Boolean( jsonObject.get("isConfirm").toString()));
		}
		
		if(CommonUtil.isNotNullOrEmpty(jsonObject.get("isUnconfirm"))){
			user.setIsUnconfirm(new Boolean( jsonObject.get("isUnconfirm").toString()));
		}
		
		if(CommonUtil.isNotNullOrEmpty(jsonObject.get("isSupervisor"))){
			user.setIsSupervisor(new Boolean( jsonObject.get("isSupervisor").toString()));
		}
		
		if(CommonUtil.isNotNullOrEmpty(jsonObject.get("isSuperuser"))){
			user.setIsSuperuser(new Boolean( jsonObject.get("isSuperuser").toString()));
		}
		
		if(CommonUtil.isNotNullOrEmpty(jsonObject.get("isReprint"))){
			user.setIsReprint(new Boolean( jsonObject.get("isReprint").toString()));
		}
		
		
		return user;
		
		
	}

	public static String userMapperGetId(HttpServletRequest request)
			throws IOException {
		String jsonBody = IOUtils.toString(request.getInputStream());
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonBody);
		return (String) jsonObject.get("id");
	}
	
	public static User userMapperUbahPassOtorisasi(HttpServletRequest request)throws IOException {

		User user = new User();
		String jsonBody = IOUtils.toString(request.getInputStream());
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonBody);
		user.setId((String) jsonObject.get("id"));
		user.setPasswordOtorisasi((String) jsonObject.get("passwordOtorisasi"));		
		return user;
		
		
	}
	
	public static User userMapperUbahPassLogin(HttpServletRequest request)throws IOException {

		User user = new User();
		String jsonBody = IOUtils.toString(request.getInputStream());
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(jsonBody);
		user.setId((String) jsonObject.get("id"));
		user.setPassword((String) jsonObject.get("password"));		
		return user;
		
		
	}
}
