package com.hk.common.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Davin Ardian
 * Email : davin.ardian@gmail.com
 */
@MappedSuperclass
public abstract class SimpleModel {
	
	@Id
	@GeneratedValue(
		    strategy=GenerationType.SEQUENCE, 
		    generator="SEQ_GEN")
	@javax.persistence.SequenceGenerator(
			name="SEQ_GEN",
			sequenceName="SQ_NAME",
			allocationSize=20
			)
	@Column(name = "ID")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
