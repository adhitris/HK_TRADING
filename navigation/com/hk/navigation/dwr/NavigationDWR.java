package com.hk.navigation.dwr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import com.hk.base.dao.ModuleDAO;
import com.hk.base.entity.Module;
import com.hk.common.interceptor.BaseInterceptor;
import com.hk.common.util.CommonUtil;

@RemoteProxy(name = "navigationDWR")
public class NavigationDWR extends BaseInterceptor{
	
	
	
	@Autowired
	private ModuleDAO moduleDAO;
	
	@RemoteMethod
	public synchronized List<Object> parentNode(){
		List<Object> parentNodeByRole = new ArrayList<Object>();
		try{	
			List<Module> result = moduleDAO.findModuleById(getId());	
			for(Module m : result){
				if(m.getStatus().equals("0") && (CommonUtil.isNullOrEmpty(m.getModule()))){
					Map<String, Object> map = new TreeMap<String, Object>();
					map.put("module", m.getNama());
					map.put("moduleId", m.getId());
					parentNodeByRole.add(map);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return parentNodeByRole;
	}
	
	@RemoteMethod
	public synchronized List<Object> childNode(String parentId){
		List<Object> childNodeByParent = new ArrayList<Object>();
		
		try{
			List<Module> children = moduleDAO.findModuleChildByIdParent(getId(),parentId);
			for(Module m : children){
				Map<String, Object> map = new TreeMap<String, Object>();
				if(CommonUtil.isNotNullOrEmpty(m.getStatus())){
					if( m.getStatus().equals("0") ){
						map.put("1menuId", m.getId());
						map.put("2menu", m.getNama());
						map.put("3path", m.getPath());
						map.put("4url", m.getUrl());
						map.put("5haveChild", "yes");
						map.put("parentId", m.getId());
						childNodeByParent.add(map);
					}else{
						map.put("1menuId", m.getId());
						map.put("2menu", m.getNama());
						map.put("3path", m.getPath());
						map.put("4url", m.getUrl());
						map.put("5haveChild", "no");
						map.put("parentId", m.getId());
						childNodeByParent.add(map);
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return childNodeByParent;
	}

	
	@RemoteMethod
	public synchronized Map<String, Object> getParentChild(){
		List<Object> list = new ArrayList<Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("name", "link11");
			map1.put("url", "www.domain11.com");
			map1.put("parent", "null");
			list.add(map1);
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("name", "link2");
			map2.put("url", "www.domain12.com");
			map2.put("parent", "null");
			list.add(map2);
			
			map.put("menu", list);
			
		}catch(Exception e){
			
		}
		
		return map;
	}
}
