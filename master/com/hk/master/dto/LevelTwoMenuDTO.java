package com.hk.master.dto;

import java.util.List;

import com.hk.base.entity.Module;

public class LevelTwoMenuDTO {
	
	private String idParent;
	
	private String id;
	
	private Module module;
	
	private boolean selected;
	
	private String haveChild;
	
	private String idChild;
	
	private boolean doCreate;
	
	private boolean doUpdate;
	
	private List<LevelThreeMenuDTO> levelThreeMenuDTO;

	public void setLevelThreeMenuDTO(List<LevelThreeMenuDTO> levelThreeMenuDTO) {
		this.levelThreeMenuDTO = levelThreeMenuDTO;
	}

	public List<LevelThreeMenuDTO> getLevelThreeMenuDTO() {
		return levelThreeMenuDTO;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Module getModule() {
		return module;
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

	public void setHaveChild(String haveChild) {
		this.haveChild = haveChild;
	}

	public String getHaveChild() {
		return haveChild;
	}

	public void setIdChild(String idChild) {
		this.idChild = idChild;
	}

	public String getIdChild() {
		return idChild;
	}

	public boolean getDoCreate() {
		return doCreate;
	}

	public void setDoCreate(boolean doCreate) {
		this.doCreate = doCreate;
	}

	public boolean getDoUpdate() {
		return doUpdate;
	}

	public void setDoUpdate(boolean doUpdate) {
		this.doUpdate = doUpdate;
	}
	
}
