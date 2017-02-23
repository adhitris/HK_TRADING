package com.hk.common.enumeration;

public enum KainGrupEnum {
	


	Grup("G"),
	Warna("W"),
	Motif("M");
	
	private String val;

	KainGrupEnum(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}
}
