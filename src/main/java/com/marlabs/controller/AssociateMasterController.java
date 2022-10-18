package com.marlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.marlabs.helper.AssociateDetailsExcelHelper;
import com.marlabs.helper.AssociatePDFExporter;
import com.marlabs.model.AssociateDetailsMaster;
import com.marlabs.service.AssociateDetailsService;

@RestController
@CrossOrigin("*")
public class AssociateMasterController {
	
	@Autowired
	private AssociateDetailsService service;
	
	@PostMapping("/associate/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){
		if(AssociateDetailsExcelHelper.checkExcelFormate(file)) {
			this.service.save(file);
			return ResponseEntity.ok(Map.of("message","File is uploaded and data is saved in Database"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file only");
	}
	
	@GetMapping("/associate")
	public List<AssociateDetailsMaster> getAllAssociateList(){
		return this.service.getAllAssociates();
	}
	
	@GetMapping("/export")
	public void exportToPDF(HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename = associates.pdf";
		
		response.setHeader(headerKey, headerValue);
		
		List<AssociateDetailsMaster> associatelist = service.getAllAssociates();
		
		AssociatePDFExporter exporter = new AssociatePDFExporter(associatelist);
		exporter.export(response);
		
	}

}
