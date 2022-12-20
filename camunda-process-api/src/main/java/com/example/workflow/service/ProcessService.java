package com.example.workflow.service;

import java.util.List;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {

	public static String PROCESS_VARIABLE = "PROCESS_VARIABLE";
	public static String LOCAL_VARIABLE = "LOCAL_VARIABLE";

	private final RuntimeService runtimeService;
	private final TaskService taskService;

	public ProcessService(RuntimeService runtimeService, TaskService taskService) {
		this.runtimeService = runtimeService;
		this.taskService = taskService;
	}

	public void startProcess(String action) {
		switch (action) {
		case "signal": {
			runtimeService.signalEventReceived("Signal_Start");
			break;
		}
		case "manual": {
			runtimeService.startProcessInstanceByKey("start_process");
			break;
		}
		case "message": {
			runtimeService.createMessageCorrelation("Message_Start").correlate();
			break;
		}
		case "async": {
			runtimeService.startProcessInstanceByKey("start_process_async");
			break;
		}
		case "throw": {
			runtimeService.startProcessInstanceByKey("Process_Exception");
			break;
		}
		case "variable": {
			runtimeService.startProcessInstanceByKey("Process_Variable");
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	public void endProcess(String processInstanceId) {
		runtimeService.deleteProcessInstance(processInstanceId, "End process");
	}

	public void checkStatusProcess() {
		List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().list();
		UserTask t;
		System.out.println(processInstances);
		processInstances.forEach(processInstance -> {
			ExecutionEntity e = (ExecutionEntity) processInstance;
			System.out.println(e.getActivityInstanceState());
			System.out.println(e.isSuspended());
			System.out.println(e.getSuspensionState());
			System.out.println(e.isActive());
			System.out.println(e.isCanceled());
			System.out.println(e.isCompleteScope());
			System.out.println(e.isStarting());
			System.out.println(e.isEnded());
			System.out.println(e.isAsyncAfterScopeWithoutTransition());
			System.out.println(processInstance.isSuspended());
		});
	}

	public void triggerProcess(String type, String processInstanceId) {
		switch (type) {
		case "signal": {
			final List<Execution> executions = runtimeService.createExecutionQuery().
	                processInstanceId(processInstanceId).
	                signalEventSubscriptionName("Signal_Event").list();
			System.out.println(executions);
			runtimeService.signalEventReceived("Signal_Event", executions.get(0).getId());
			break;
		}
		case "message": {
			runtimeService.createMessageCorrelation("Message_Trigger").processInstanceId(processInstanceId).correlate();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

	public void suspendProcess(String processInstanceId) {
		
		runtimeService.createEventSubscriptionQuery().list();
		
		runtimeService.suspendProcessInstanceById(processInstanceId);
	}

	public void activateProcess(String processInstanceId) {
		runtimeService.activateProcessInstanceById(processInstanceId);
	}

	public List<String> getAllProcessIds(String key) {
		List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().processDefinitionKey(key)
				.list();
		return processInstances.stream().map(ProcessInstance::getId).collect(Collectors.toList());

	}

	public void completeUserTask(String usertaskId) {
		Task task =
		        taskService
		            .createTaskQuery()
		            .initializeFormKeys()
		            .taskId(usertaskId)
		            .singleResult();
		System.out.println(taskService.getVariableLocal(task.getId(), LOCAL_VARIABLE));
		taskService.complete(usertaskId);
	}

}
