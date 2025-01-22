package com.lenze.sdc.worklog.rest.controller;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lenze.sdc.worklog.rest.dto.UserRequestResponseDto;
import com.lenze.sdc.worklog.rest.service.ManageUserService;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

	private final ManageUserService userService;

	UserController(ManageUserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<HttpStatus> registerUSer(@RequestBody UserRequestResponseDto request) {
		if(userService.registerUser(request)){
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(request.getUserId()).toUri();
			return ResponseEntity.created(location).build();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<UserRequestResponseDto>> getAllUsers() {
		List<UserRequestResponseDto> users = userService.getAllUsers();
		if (Objects.nonNull(users)) {
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable(name = "id")  String id) {
		if(userService.deleteUser(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
