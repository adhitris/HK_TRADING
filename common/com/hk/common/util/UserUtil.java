package com.hk.common.util;

import org.hibernate.Hibernate;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hk.base.entity.User;


public class UserUtil {
	
	public static final User activeUser(){
        User user = null;
        
        try
        {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(principal != null && principal instanceof User)
            {
                user = (User)principal;
                Hibernate.initialize(user);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return user;
    }
	
	public static final String getUsername(){
    	User user = activeUser();
    	return new StringBuffer(user.getUsername()).toString();
    }
	
	public static final User getUserActive(){
    	User user = activeUser();
    	return user;
    }
	
	
	

}
