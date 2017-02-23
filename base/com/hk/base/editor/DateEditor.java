package com.hk.base.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Component;

import com.hk.common.util.DateUtil;



@Component
public class DateEditor extends PropertyEditorSupport {
	
	public void setAsText(String date) throws IllegalArgumentException{
		setValue(DateUtil.toDateOrDateTime(date));
	}
	
}
