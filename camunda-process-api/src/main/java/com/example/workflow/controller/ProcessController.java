package com.example.workflow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workflow.service.ProcessService;

@RestController
public class ProcessController {

	private final ProcessService processService;

	public ProcessController(ProcessService processService) {
		this.processService = processService;
	}

	@PostMapping(value = "/startProcess/{action}")
	public ResponseEntity<Object> startProcess(@PathVariable() String action) {
		processService.startProcess(action);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/processIds/{processDefinitionKey}")
	public ResponseEntity<Object> getAllProcessIds(@PathVariable() String processDefinitionKey) {
		processService.getAllProcessIds(processDefinitionKey);
		return ResponseEntity.ok(processService.getAllProcessIds(processDefinitionKey));
	}

	@GetMapping("/status")
	public ResponseEntity<Object> processStatus() {
		processService.checkStatusProcess();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/triggerProcess/{type}/{processInstanceId}")
	public ResponseEntity<Object> triggerProcess(@PathVariable() String type,
			@PathVariable() String processInstanceId) {
		processService.triggerProcess(type, processInstanceId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/suspendProcess/{processInstanceId}")
	public ResponseEntity<Object> suspendProcess(@PathVariable() String processInstanceId) {
		processService.suspendProcess(processInstanceId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/activateProcess/{processInstanceId}")
	public ResponseEntity<Object> activateProcess(@PathVariable() String processInstanceId) {
		processService.activateProcess(processInstanceId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/endProcess/{processInstanceId}")
	public ResponseEntity<Object> endProcess(@PathVariable() String processInstanceId) {
		processService.endProcess(processInstanceId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/completeUserTask/{userTaskId}")
	public ResponseEntity<Object> completeUserTask(@PathVariable() String userTaskId) {
		processService.completeUserTask(userTaskId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
