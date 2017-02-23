package com.hk.common.pagination;

import java.util.List;


import org.hibernate.Session;
@SuppressWarnings("rawtypes")
public interface GridViewQuery {
	public Filterable getFilterable();
	public void setFilterable(Filterable filterable);
	
	public FilterCriteria getFilterCriteria();
	public void setFilterCriteria(FilterCriteria filterCriteria);
	
	public void setSession(Session session);
	public Session getSession();
	
	public Long count();
	public List execute();
}
