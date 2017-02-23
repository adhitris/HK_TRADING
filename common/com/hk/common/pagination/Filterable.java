package com.hk.common.pagination;

import java.util.List;


public interface Filterable {
	@SuppressWarnings("rawtypes")
	public List filter(GridViewQuery query);
	public Long count(GridViewQuery query);

}
