package com.lenze.sdc.worklog.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lenze.sdc.worklog.rest.service.FileStorageService;

@RestController
@RequestMapping(path = "/api/dailyworklog")
public class UploadFileController {

	@Autowired
	private FileStorageService fileStorageService;

//	@PostMapping(value= "/upload")
//	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//		try {
//			fileStorageService.store(file);
//			return ResponseEntity.ok("File uploaded successfully");
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body("Could not upload the file: " + file.getOriginalFilename() + "!");
//		}
//	}
}
