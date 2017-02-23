package com.hk.common.pagination;

import org.hibernate.Session;

public abstract class AbstractGridViewQuery implements GridViewQuery{
	public Filterable filterable;
	public Session session;
	public FilterCriteria filterCriteria;
	
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public FilterCriteria getFilterCriteria() {
		return filterCriteria;
	}
	public void setFilterCriteria(FilterCriteria filterCriteria) {
		this.filterCriteria = filterCriteria;
	}
	public Filterable getFilterable() {
		return filterable;
	}
	public void setFilterable(Filterable filterable) {
		this.filterable = filterable;
	}
}
