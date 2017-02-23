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
@Table(name = "M_UNIT")
@org.hibernate.annotations.Entity(dynamicUpdate = true,optimisticLock = OptimisticLockType.ALL)
public class Unit extends BaseModel implements Serializable {
 
	@Id
	@Column(name = "UNIT_ID")
	private String id;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name = "NAMA")
	private String nama;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsActive() {
		return isActive;
	}

}
