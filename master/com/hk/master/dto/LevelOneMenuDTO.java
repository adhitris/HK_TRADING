package com.hk.master.dto;

import java.util.List;

import com.hk.base.entity.Module;

public class LevelOneMenuDTO {

	private String id;
	
	private Module module;
	
	private boolean selected;
	
	private String idChild;
	
	private List<LevelTwoMenuDTO> levelTwoMenuDTO;

	public void setLevelTwoMenuDTO(List<LevelTwoMenuDTO> levelTwoMenuDTO) {
		this.levelTwoMenuDTO = levelTwoMenuDTO;
	}

	public List<LevelTwoMenuDTO> getLevelTwoMenuDTO() {
		return levelTwoMenuDTO;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Module getModule() {
		return module;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setIdChild(String idChild) {
		this.idChild = idChild;
	}

	public String getIdChild() {
		return idChild;
	}
	
}
