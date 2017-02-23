package com.hk.common.transformer;

import java.lang.reflect.Field;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.hk.common.util.DateUtil;
import com.hk.common.validator.CommonValidator;

public class HttpRequestTransformer {
	
	public static Transformable transform(HttpServletRequest request, Class<? extends AbstractTransformer> object){
		AbstractTransformer transformer = null;
		try{
			transformer = object.newInstance();
			
			Field[] fields = object.getDeclaredFields();
			for (Field field : fields) {
				
				String param = request.getParameter(field.getName());
				if(CommonValidator.validateParam(param)){
					field.setAccessible(true);
					
					if(field.getType().equals(String.class)){
						field.set(transformer, param);
					}else if(field.getType().equals(Long.class)){
						field.set(transformer, CommonValidator.getValidLongParam(param));
					}else if(field.getType().equals(Date.class)){
						field.set(transformer, DateUtil.toDate(param));
					}else if(field.getType().equals(Integer.class)){
						field.set(transformer, CommonValidator.getValidIntegerParam(param));
					}else if(field.getType().equals(Boolean.class)){
						field.set(transformer, Boolean.valueOf(param));
					}
					
				}
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return transformer;
	}}
