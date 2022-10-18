package com.marlabs.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marlabs.model.BatchMaster;
import com.marlabs.service.BatchMasterService;
import java.util.*;

@RestController
@CrossOrigin("*")
public class BatchController {
	
	@Autowired
	private BatchMasterService service;
	
	@PostMapping("/batchsave")
	public ResponseEntity<?> saveData(){
		
		
		BatchMaster batch = new BatchMaster();
		batch.setBatch_code("HYD15CJ002");
		batch.setBatch_owner_id(789);
		batch.setMentor_id(5645);
		batch.setAssessor_id(5456);
		
		LocalDate start_date = LocalDate.of(2022,05,20);
		batch.setBatch_start_date(start_date);
		
		LocalDate end_date = LocalDate.of(2022,05,15);
		batch.setBatch_end_date(end_date);
		
		this.service.saveBatch(batch);
		
		return ResponseEntity.ok(Map.of("message","File is uploaded and data is save in DB"));
	}
	
	@GetMapping("/batchdata")
	public List<BatchMaster> getAllBatchData(){
		return this.service.getAllBatches();
	}

}
