package com.hk.common.util;

import java.util.HashMap;
import java.util.Map;

public class ComboGridUtil {
	
	public static synchronized String checkKeyword(String keyword){
		String searchTerm = "";
		if(keyword != null){
			searchTerm = keyword.toLowerCase();
		}
		
		return searchTerm;
	}

	public static synchronized Map<String, Integer> dataForJSON(int totalRow, String page, String rows){
		Map<String, Integer> map = new HashMap<String, Integer>();
		int totalPages = 0;
		int limit = Integer.parseInt(rows);
		int pageRequested = Integer.parseInt(page);
		
		if( totalRow > 0 ) {
			totalPages = (int) Math.ceil((double)totalRow/(double)limit);
		} else {
			totalPages = 0;
		}
		
		if (pageRequested > totalPages) pageRequested=totalPages;
		int rowStart = pageRequested*limit-limit;
		int rowEnd = limit;
		
		map.put("page", Integer.valueOf(page));
		map.put("total", totalPages);
		map.put("records", totalRow);
		map.put("rowStart", rowStart);
		map.put("rowEnd", rowEnd);
		
		return map;
	}
	
}
