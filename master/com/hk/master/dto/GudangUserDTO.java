package com.hk.master.dto;

public class GudangUserDTO {

	private String namaUser;
	private String idUser;
	private Boolean isSelected;
	
	public GudangUserDTO() {
		super();
	}
	
	public GudangUserDTO(String namaUser, String idUser, Boolean isSelected) {
		super();
		this.namaUser = namaUser;
		this.idUser = idUser;
		this.isSelected = isSelected;
	}
	
	public String getNamaUser() {
		return namaUser;
	}
	public void setNamaUser(String namaUser) {
		this.namaUser = namaUser;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	
}
