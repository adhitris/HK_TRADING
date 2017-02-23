package com.hk.base.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


@Embeddable
public class AccessUserID implements Serializable{
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private User user;
	
	@ManyToOne
	private Module module;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
	

}
