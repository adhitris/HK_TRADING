package com.hk.base.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hk.common.model.BaseModel;

@SuppressWarnings("serial")
@Entity
@Table(name = "M_MODULE")
public class Module extends BaseModel implements Serializable{

	@Id
	@Column(name = "MODULE_ID")
	private String id;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name = "NAMA",length=50)
    private String nama;
	
	@Column(name = "PATH",length=255)
    private String path;
	
	@Column(name = "URL",length=255)
    private String url;
	
	@Column(name = "URUTAN",length=255)
    private Integer urutan;
	
	@Column(name = "STATUS",length=5)
    private String status;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "module",cascade=CascadeType.ALL, orphanRemoval = true)
	private Set<Module> ModuleSet = new HashSet<Module>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.module", cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<AccessUser> accessUserSet=new HashSet<AccessUser>();

	@JoinColumn(name = "MODULE_FK")
    @ManyToOne
    private Module module;
	
	@Column(name = "MODULE_FK", insertable = false, updatable = false)
	private String moduleId;


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setModuleSet(Set<Module> moduleSet) {
		ModuleSet = moduleSet;
	}

	public Set<Module> getModuleSet() {
		return ModuleSet;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Module getModule() {
		return module;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getUrutan() {
		return urutan;
	}

	public void setUrutan(Integer urutan) {
		this.urutan = urutan;
	}
	
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public Set<AccessUser> getAccessUserSet() {
		return accessUserSet;
	}

	public void setAccessUserSet(Set<AccessUser> accessUserSet) {
		this.accessUserSet = accessUserSet;
	}
	
	
}
