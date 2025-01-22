package com.lenze.sdc.worklog.rest.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lenze.sdc.worklog.rest.dto.DailyStatusRequest;
import com.lenze.sdc.worklog.rest.dto.DailyStatusResponse;
import com.lenze.sdc.worklog.rest.service.DailyStatusService;

@RestController
@RequestMapping(path = "/api/worklog")
public class DailyStatusController {

	private final DailyStatusService dailyStatusService;

	DailyStatusController(DailyStatusService dailyStatusService) {
		this.dailyStatusService = dailyStatusService;
	}

	@PostMapping(value = "/daily", consumes = "application/json", produces = "application/json")
	public ResponseEntity<HttpStatus> postDailyStatus(@RequestBody DailyStatusRequest dailyStatusRequest) {
		String id = dailyStatusService.postDaily(dailyStatusRequest);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/daily/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<HttpStatus> updateWorklog(@RequestBody DailyStatusRequest dailyStatusRequest,
			@PathVariable(name = "id") String worklogId) {
		if (dailyStatusService.updateWorklog(dailyStatusRequest, worklogId)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/daily/{userName}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<DailyStatusResponse>> getByUserName(@PathVariable(name = "userName") String userName) {
		List<DailyStatusResponse> worklogList = dailyStatusService.findByUserName(userName);
		if (Objects.nonNull(worklogList)) {
			return new ResponseEntity<>(worklogList, HttpStatus.OK);
		}
		return new ResponseEntity<>(worklogList, HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/daily", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<DailyStatusResponse>> getAllWorklog() {
		List<DailyStatusResponse> worklogList = dailyStatusService.getAllWorklog();
		if (Objects.nonNull(worklogList)) {
			return new ResponseEntity<>(worklogList, HttpStatus.OK);
		}
		return new ResponseEntity<>(worklogList, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/daily/{userName}/{keyword}")
	public ResponseEntity<List<Object>> getUserAdditionalDEtailsByUserName(@PathVariable(name = "userName") String userName,
			@PathVariable(name = "keyword") String keyword) {
		List<Object> result = dailyStatusService.getWorklogByUserNameFilterInput(userName, keyword);
		if (Objects.nonNull(result)) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
	    }
	
	@GetMapping("/daily/{projectName}/pending")
    public List<String> getWhoIsPending(@PathVariable(name = "projectName") String projectName) {
        return dailyStatusService.getWhoIsPending(projectName, LocalDate.now());
    }
}
