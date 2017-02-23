/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hk.base.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.OptimisticLockType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@SuppressWarnings("serial")
@Entity
@Table(name = "M_USER")
@org.hibernate.annotations.Entity(dynamicUpdate = true,optimisticLock = OptimisticLockType.ALL)
public class User implements UserDetails {
	
	@Id
	@Column(name = "USER_ID")
	private String id;
	
	@Version
	@Column(name = "VERSION")
	private Integer version;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name = "CREATE_DATE")
	private Date createDate;
	
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
	
	@Column(name = "CREATE_BY",length=8)
	private String createBy;
	
	@Column(name = "LAST_UPDATE_BY",length=8)
	private String lastUpdateBy;
	
	@Column(name = "DATE_NON_ACTIVE")
	private Date dateNonActive;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "NAMA",length=50)
	private String nama;
	
	@Column(name = "IS_CREATE")
	private Boolean isCreate;
	
	@Column(name = "IS_DELETE")
	private Boolean isDelete;
	
	@Column(name = "IS_UPDATE")
	private Boolean isUpdate;
	
	@Column(name = "IS_PRINT")
	private Boolean isPrint;
	
	@Column(name = "IS_CANCEL")
	private Boolean isCancel;
	
	@Column(name = "IS_REPORT")
	private Boolean isReport;
	
	@Column(name = "IS_SUPERVISOR")
	private Boolean isSupervisor;
	
	@Column(name = "IS_CONFIRM")
	private Boolean isConfirm;
	
	@Column(name = "IS_UNCONFIRM")
	private Boolean isUnconfirm;
	
	@Column(name = "IS_SUPERUSER")
	private Boolean isSuperuser;
	
	@Column(name = "IS_REPRINT")
	private Boolean isReprint;
	
	@Column(name = "NIP")
	private String nip;
	
	@Column(name = "PASSWORD_OTORISASI")
	private String passwordOtorisasi;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user", cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<AccessUser> accessUserSet=new HashSet<AccessUser>();
	
	@Column(name = "IS_ACCOUNT_NON_EXPIRED")
	private Character isAccountNonExpired;

	@Column(name = "IS_ACCOUNT_NON_LOCKED")
	private Character isAccountNonLocked;

	@Column(name = "IS_CREDENTIALS_NON_EXPIRED")
	private Character isCredentialNonExpired;

	@Column(name = "IS_ENABLED")
	private Character isEnabled;

	@Column(name = "BARCODE")
	private String barcode;
	
	@Column(name = "MRU_LIMIT")
	private Integer mruLimit;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
	
			list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		return list;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Boolean getIsAccountNonExpired() {
		if (isAccountNonExpired == null) return null;
	    return isAccountNonExpired.equals('Y') ? Boolean.TRUE : Boolean.FALSE;
	}

	public void setIsAccountNonExpired(Boolean isAccountNonExpired) {
		if (isAccountNonExpired == null) {
		      this.isAccountNonExpired = null;
		    } else {
		      this.isAccountNonExpired = isAccountNonExpired==true ? 'Y' : 'N';
		    }
	}

	public Boolean getIsAccountNonLocked() {
		if (isAccountNonLocked == null) return null;
	    return isAccountNonLocked.equals('Y') ? Boolean.TRUE : Boolean.FALSE;
	}

	public void setIsAccountNonLocked(Boolean isAccountNonLocked) {
		if (isAccountNonLocked == null) {
		      this.isAccountNonLocked = null;
		    } else {
		      this.isAccountNonLocked = isAccountNonLocked==true ? 'Y' : 'N';
		    }
	}

	public Boolean getIsCredentialNonExpired() {
		if (isCredentialNonExpired == null) return null;
	    return isCredentialNonExpired.equals('Y') ? Boolean.TRUE : Boolean.FALSE;
	}

	public void setIsCredentialNonExpired(Boolean isCredentialNonExpired) {
		if (isCredentialNonExpired == null) {
		      this.isCredentialNonExpired = null;
		    } else {
		      this.isCredentialNonExpired = isCredentialNonExpired==true ? 'Y' : 'N';
		    }
	}

	public Boolean getIsEnabled() {
		if (isEnabled == null) return null;
	    return isEnabled.equals('Y') ? Boolean.TRUE : Boolean.FALSE;
	}

	public void setIsEnabled(Boolean isEnabled) {
		if (isEnabled == null) {
		      this.isEnabled = null;
		    } else {
		      this.isEnabled = isEnabled == true ? 'Y' : 'N';
		    }
	}

	public Set<AccessUser> getAccessUserSet() {
		return accessUserSet;
	}

	public void setAccessUserSet(Set<AccessUser> accessUserSet) {
		this.accessUserSet = accessUserSet;
	}

	public Boolean getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(Boolean isCreate) {
		this.isCreate = isCreate;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public Boolean getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(Boolean isPrint) {
		this.isPrint = isPrint;
	}

	public Boolean getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Boolean isCancel) {
		this.isCancel = isCancel;
	}

	public Boolean getIsReport() {
		return isReport;
	}

	public void setIsReport(Boolean isReport) {
		this.isReport = isReport;
	}

	public Boolean getIsSupervisor() {
		return isSupervisor;
	}

	public void setIsSupervisor(Boolean isSupervisor) {
		this.isSupervisor = isSupervisor;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getPasswordOtorisasi() {
		return passwordOtorisasi;
	}

	public void setPasswordOtorisasi(String passwordOtorisasi) {
		this.passwordOtorisasi = passwordOtorisasi;
	}
	
	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	@Override
	public String getUsername() {
		return this.id;
	}
	

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getDateNonActive() {
		return dateNonActive;
	}

	public void setDateNonActive(Date dateNonActive) {
		this.dateNonActive = dateNonActive;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Boolean getIsSuperuser() {
		return isSuperuser;
	}

	public void setIsSuperuser(Boolean isSuperuser) {
		this.isSuperuser = isSuperuser;
	}

	public Boolean getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Boolean isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Boolean getIsUnconfirm() {
		return isUnconfirm;
	}

	public void setIsUnconfirm(Boolean isUnconfirm) {
		this.isUnconfirm = isUnconfirm;
	}

	public Integer getMruLimit() {
		return mruLimit;
	}

	public void setMruLimit(Integer mruLimit) {
		this.mruLimit = mruLimit;
	}

	public Boolean getIsReprint() {
		return isReprint;
	}

	public void setIsReprint(Boolean isReprint) {
		this.isReprint = isReprint;
	}

	
}
