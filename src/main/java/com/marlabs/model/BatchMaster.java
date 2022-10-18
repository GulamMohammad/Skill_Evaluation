package com.marlabs.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "batch_master_table")
public class BatchMaster {
	
	@Id
	@Column(name="BATCH_CODE",length = 10)
	private String batch_code;
	
	@Column(name="BATCH_OWNER_ID",length = 6)
	private Integer batch_owner_id;
	
	@Column(name="MENTOR_ID", length = 6)
	private Integer mentor_id;
	
	@Column(name="ASSESSOR_ID",length = 6)
	private Integer assessor_id;
	
	@Column(name="BATCH_START_DATE")
	private LocalDate batch_start_date;
	
	@Column(name="BATCH_END_DATE")
	private LocalDate batch_end_date;
	
	@Column(name="MENTOR_FEEDBACK")
	private String mentor_feedback;
	
	
	

}
