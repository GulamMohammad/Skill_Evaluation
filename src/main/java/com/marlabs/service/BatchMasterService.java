package com.marlabs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlabs.model.BatchMaster;
import com.marlabs.repository.BatchRepository;

@Service
public class BatchMasterService {

	@Autowired
	private BatchRepository repo;

	public void saveBatch(BatchMaster master) {
		
		repo.save(master);
	}
	
	public List<BatchMaster> getAllBatches(){
		return repo.findAll();
	}

}
