package com.marlabs.controller;

import java.util.Collections;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marlabs.helper.MyExcelHelper;
import com.marlabs.model.TechnologyMaster;
import com.marlabs.service.TechnologyMasterService;

@RestController
@CrossOrigin("*")
public class TechController {
	
	@Autowired
	private TechnologyMasterService service;
	
	@PostMapping("/technology/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){
		
		if(MyExcelHelper.checkExcelFormate(file)) {
			
			this.service.save(file);
			
			return ResponseEntity.ok(Map.of("message","File is uploaded and data is save in DB"));
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pleas upload excel file");
	}
	
	@GetMapping("/technologies")
	public List<TechnologyMaster> getAllTechnology(){
		
		return this.service.getAllTechnologies();
	}

}
