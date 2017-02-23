package com.hk.common.pagination;


public interface FilterCriteria {
	public void setPage(int page);
	public int getPage();
	
	public void setNextPage(int nextPage);
	public int getNextPage();
	
	public void setPrevPage(int prevPage);
	public int getPrevPage();
	
	public void setTotalPage(int totalPage);
	public int getTotalPage();
	
	public void setMax(int max);
	public int getMax();
	
	public void setUrl(String url);
	public String getUrl();
	
	public String getTarget();
	public void setTarget(String target);
}
