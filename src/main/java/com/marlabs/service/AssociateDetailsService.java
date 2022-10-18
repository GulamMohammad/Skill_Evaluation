package com.marlabs.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marlabs.helper.AssociateDetailsExcelHelper;
import com.marlabs.model.AssociateDetailsMaster;
import com.marlabs.repository.AssociateRepository;

@Service
public class AssociateDetailsService {
	
	@Autowired
	private AssociateRepository repository;
	
	public void save(MultipartFile file) {
		
		try {
			List<AssociateDetailsMaster> as = AssociateDetailsExcelHelper.ConvertExcel(file.getInputStream());
			this.repository.saveAll(as);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<AssociateDetailsMaster> getAllAssociates(){
		return this.repository.findAll();
	}

}
