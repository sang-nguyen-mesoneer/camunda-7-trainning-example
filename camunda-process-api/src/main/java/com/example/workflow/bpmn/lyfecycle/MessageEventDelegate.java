package com.example.workflow.bpmn.lyfecycle;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import com.example.workflow.service.ProcessService;

@Service("messageEventDelegate")
public class MessageEventDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("messageEventDelegate");
		System.out.println(execution.getVariable(ProcessService.LOCAL_VARIABLE));
		System.out.println(execution.getVariableLocal(ProcessService.LOCAL_VARIABLE));
		System.out.println(execution.getVariable(ProcessService.PROCESS_VARIABLE));
	}
}
