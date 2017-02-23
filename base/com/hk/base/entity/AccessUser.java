package com.hk.base.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "M_ACCESS_USER")
@AssociationOverrides({
	@AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "USER_ID")),
	@AssociationOverride(name = "pk.module", joinColumns = @JoinColumn(name = "MODULE_ID"))
})
public class AccessUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
    protected AccessUserID pk = new AccessUserID();
	

	public AccessUserID getPk() {
		return pk;
	}

	public void setPk(AccessUserID pk) {
		this.pk = pk;
	}
	
	@Transient
	public User getUser() {
		return getPk().getUser();
	}

	public void setUser(User user) {
		getPk().setUser(user);
	}

	@Transient
	public Module getModule() {
		return getPk().getModule();
	}

	public void setModule(Module module) {
		getPk().setModule(module);
	}
	
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}



}
