/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hk.base.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.OptimisticLockType;
import com.hk.common.model.BaseModel;



/**
 *
 * @author Adik
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "M_MATA_UANG")
@org.hibernate.annotations.Entity(dynamicUpdate = true,optimisticLock = OptimisticLockType.ALL)
public class MataUang extends BaseModel implements Serializable {
 
	@Id
	@Column(name = "MATA_UANG_ID")
	private String id;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


}
