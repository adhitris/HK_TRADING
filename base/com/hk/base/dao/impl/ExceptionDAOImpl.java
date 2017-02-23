package com.hk.base.dao.impl;

import org.springframework.stereotype.Controller;

import com.hk.base.dao.ExceptionEventDAO;
import com.hk.base.entity.ExceptionEvent;
import com.hk.common.dao.AbstractDAO;



@Controller
public class ExceptionDAOImpl extends AbstractDAO<ExceptionEvent> implements ExceptionEventDAO{

	@Override
	protected Class<ExceptionEvent> getDomainClass() {
		// TODO Auto-generated method stub
		return ExceptionEvent.class;
	}

}
