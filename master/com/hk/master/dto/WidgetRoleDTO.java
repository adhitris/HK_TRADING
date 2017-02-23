package com.hk.master.dto;

public class WidgetRoleDTO {

	private String namaRole;
	private String idRole;
	private Boolean isSelected;
	
	public WidgetRoleDTO() {
		super();
	}

	public WidgetRoleDTO(String namaRole, String idRole, Boolean isSelected) {
		super();
		this.namaRole = namaRole;
		this.idRole = idRole;
		this.isSelected = isSelected;
	}

	public String getNamaRole() {
		return namaRole;
	}

	public void setNamaRole(String namaRole) {
		this.namaRole = namaRole;
	}

	public String getIdRole() {
		return idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	
	
	
}
