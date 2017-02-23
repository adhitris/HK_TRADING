package com.hk.common.factory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.hk.common.pagination.AbstractFilterCriteria;
import com.hk.common.pagination.FilterCriteria;
import com.hk.common.transformer.HttpRequestTransformer;
import com.hk.common.util.UrlUtil;
import com.hk.common.validator.CommonValidator;


@Component
public class FilterCriteriaFactory {
	
	public FilterCriteria create(HttpServletRequest request, Class<? extends AbstractFilterCriteria> filter){
		AbstractFilterCriteria criteria = null;
		
		try{
			criteria = (AbstractFilterCriteria) HttpRequestTransformer.transform(request, filter);

			criteria.setTarget(CommonValidator.getValidTarget(request.getParameter("target")));
			criteria.setPage(CommonValidator.getValidPageIndex(request.getParameter("page")));
			criteria.setUrl(UrlUtil.generateParamBased(request));
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return criteria;
	}
	
}
