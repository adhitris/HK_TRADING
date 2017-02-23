package com.hk.master.dto;


public class UserGudangDTO {
	
	private String namaGudang;
	private String idGudang;
	private Boolean isSelected;
	
	public UserGudangDTO() {
		super();
	}
	
	public UserGudangDTO(String namaGudang, String idGudang, Boolean isSelected) {
		super();
		this.namaGudang = namaGudang;
		this.idGudang = idGudang;
		this.isSelected = isSelected;
	}
	
	public String getNamaGudang() {
		return namaGudang;
	}
	public void setNamaGudang(String namaGudang) {
		this.namaGudang = namaGudang;
	}
	public String getIdGudang() {
		return idGudang;
	}
	public void setIdGudang(String idGudang) {
		this.idGudang = idGudang;
	}
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	
}
