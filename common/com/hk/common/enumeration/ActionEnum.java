package com.hk.common.enumeration;

public enum ActionEnum {
	

	Saved("INSERT"),
	Updated("UPDATE"),
	Deleted("DELETE"),
	Actived("ACTIVATE"),
	NonActived("NONACTIVATE"),
	Canceled("CANCELED"),
	UnCanceled("UNCANCELED"),
	Confirm("CONFIRM"),
	UnConfirm("UNCONFIRM"),
	Ignore("IGNORE"),
	UnIgnore("UNIGNORE"),
	Report("REPORT"),
	Print("PRINT"),
	RePrint("REPRINT");
	
	
	private String val;

	ActionEnum(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}
}
