package com.hk.common.util;

import javax.servlet.http.HttpServletRequest;


public class UrlUtil {
	
	public static String getServletPath(HttpServletRequest request){
		return request.getServletPath();		
	}
	
	
	public static String toCompleteUrl(String partialUrl, HttpServletRequest request){
		return generateBasedUrl(request) + partialUrl;
	}

	public static String generateBasedUrl(HttpServletRequest request){
		String scheme = request.getScheme();             // http
		String serverName = request.getServerName();     // hostname.com
		int serverPort = request.getServerPort();        // 80
		String contextPath = request.getContextPath();   // /mywebapp
		String servletPath = request.getServletPath();   // /servlet/MyServlet

		return scheme + "://" + serverName+ ":" + serverPort + contextPath + servletPath;
	}
	
	public static String generateParamBased(HttpServletRequest request){
		String url = generateBasedUrl(request) + "?";
		
		if (request.getPathInfo() != null){
		    url += request.getPathInfo();
		}
		
		if (request.getQueryString() != null) {
			StringBuffer formattedQuery = new StringBuffer();
			
			String[] queries = request.getQueryString().split("&");
			for(int i = queries.length-1; i >= 0; i--){
				String[] params = queries[i].split("=");
				if(!params[0].equalsIgnoreCase("page")){
					if(params.length > 1){
						formattedQuery.append(params[0]).append("=").append(params[1]);
						
						if(i < queries.length){
							formattedQuery.append("&");
						}
					}
				}
			}
		    url += formattedQuery.toString();
		}

		return url;
	}
	
	public static String getPreviousUrl(HttpServletRequest request){
		return request.getHeader("Referer");
	}
}
