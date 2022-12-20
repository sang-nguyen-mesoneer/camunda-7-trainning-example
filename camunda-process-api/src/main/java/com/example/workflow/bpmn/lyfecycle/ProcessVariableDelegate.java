package com.example.workflow.bpmn.lyfecycle;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import com.example.workflow.service.ProcessService;

@Service("processVariableDelegate")
public class ProcessVariableDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("processVariableDelegate");
		System.out.println(execution.getVariableLocal(ProcessService.LOCAL_VARIABLE));
	}

}
