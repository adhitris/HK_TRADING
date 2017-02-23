package com.hk.master.dto;

public class RoleWidgetDTO {

	private String namaWidget;
	private String idWidget;
	private Boolean isSelected;
	
	public RoleWidgetDTO() {
		super();
	}
	
	public RoleWidgetDTO(String namaWidget, String idWidget, Boolean isSelected) {
		super();
		this.namaWidget = namaWidget;
		this.idWidget = idWidget;
		this.isSelected = isSelected;
	}
	
	public String getNamaWidget() {
		return namaWidget;
	}
	public void setNamaWidget(String namaWidget) {
		this.namaWidget = namaWidget;
	}
	public String getIdWidget() {
		return idWidget;
	}
	public void setIdWidget(String idWidget) {
		this.idWidget = idWidget;
	}
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	
}
