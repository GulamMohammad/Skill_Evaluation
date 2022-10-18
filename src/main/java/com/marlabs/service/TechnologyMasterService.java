package com.marlabs.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marlabs.helper.MyExcelHelper;
import com.marlabs.model.TechnologyMaster;
import com.marlabs.repository.TechnologyMasterRepository;

import lombok.experimental.Helper;

@Service
public class TechnologyMasterService {
	
	@Autowired
	private TechnologyMasterRepository repository;
	
	public void save(MultipartFile file) {
		
		try {
			
		List<TechnologyMaster>	tm = MyExcelHelper.convertExcelToListOfTechnologyMaster(file.getInputStream());
			this.repository.saveAll(tm);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public List<TechnologyMaster> getAllTechnologies(){
		
		return this.repository.findAll();
	}
	

}
