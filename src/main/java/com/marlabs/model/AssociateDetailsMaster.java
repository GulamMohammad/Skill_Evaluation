package com.marlabs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="associate_details_master")
public class AssociateDetailsMaster {
	
	@Column(name="BATCH_CODE", length = 10)
	//@OneToOne
	//@JoinColumn(name = "BATCH_CODE")
	private String batchCode;
	@Id
	@Column(name="ASSOCIATE_ID", length = 6)
	private Integer associateId;
	@Column(name="ASSOCIATE_NAME")
	private String associateName;
	@Column(name="ASSESSOR_MARK")
	private Double assessorMark;
	@Column(name="MENTOR_MARK")
	private Double mentorMark;
	@Column(name="ASSESSOR_REMARKS")
	private String assessorRemarks;
	@Column(name="MENTOR_REMARKS")
	private String mentorRemarks;

}
