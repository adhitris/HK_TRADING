package com.hk.master.dto;

import java.util.List;

import com.hk.base.entity.Module;

public class LevelThreeMenuDTO {
	
	private String idParent;
	
	private String id;
	
	private Module module;
	
	private boolean selected;
	
	private String idChild;
	
	private List<LevelFourMenuDTO> levelFourMenuDTO;

	public void setLevelFourMenuDTO(List<LevelFourMenuDTO> levelFourMenuDTO) {
		this.levelFourMenuDTO = levelFourMenuDTO;
	}

	public List<LevelFourMenuDTO> getLevelFourMenuDTO() {
		return levelFourMenuDTO;
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

	public void setIdParent(String idParent) {
		this.idParent = idParent;
	}

	public String getIdParent() {
		return idParent;
	}

	public void setIdChild(String idChild) {
		this.idChild = idChild;
	}

	public String getIdChild() {
		return idChild;
	}
}
