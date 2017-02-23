package com.hk.master.dto;

import com.hk.base.entity.Module;

public class LevelFourMenuDTO {
	
	private String idParent;
	
	private String id;
	
	private Module module;

	private boolean selected;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setIdParent(String idParent) {
		this.idParent = idParent;
	}

	public String getIdParent() {
		return idParent;
	}
	
}
