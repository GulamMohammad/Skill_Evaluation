package com.marlabs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "technology_master")
public class TechnologyMaster {
	
	@Id
	@Column(name = "TECH_ID",length = 2)
	private String techId;
	
	@Column(name = "TECH_NAME",length = 15)
	private String techName;

}
