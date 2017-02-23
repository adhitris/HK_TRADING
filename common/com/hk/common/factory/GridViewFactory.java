package com.hk.common.factory;

import com.hk.common.pagination.FilterCriteria;
import com.hk.common.pagination.GridViewQuery;


public class GridViewFactory {
	
	public static synchronized GridViewQuery create(FilterCriteria filter, Class<? extends GridViewQuery> clazz){
		GridViewQuery query = null;
		
		try{
			query = clazz.newInstance();
			query.setFilterCriteria(filter);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return query;
	}
	
}
