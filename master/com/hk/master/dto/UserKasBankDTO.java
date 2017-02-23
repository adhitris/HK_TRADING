package com.hk.master.dto;


public class UserKasBankDTO {
	
	private String namaKasBank;
	private String idKasBank;
	private Boolean isSelected;
	
	public UserKasBankDTO() {
		super();
	}
	
	public UserKasBankDTO(String namaKasBank, String idKasBank, Boolean isSelected) {
		super();
		this.namaKasBank = namaKasBank;
		this.idKasBank = idKasBank;
		this.isSelected = isSelected;
	}
	
	public String getNamaKasBank() {
		return namaKasBank;
	}
	public void setNamaKasBank(String namaKasBank) {
		this.namaKasBank = namaKasBank;
	}
	public String getIdKasBank() {
		return idKasBank;
	}
	public void setIdKasBank(String idKasBank) {
		this.idKasBank = idKasBank;
	}
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	
}
