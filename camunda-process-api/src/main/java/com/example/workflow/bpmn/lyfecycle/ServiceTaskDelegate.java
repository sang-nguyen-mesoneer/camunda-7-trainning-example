package com.example.workflow.bpmn.lyfecycle;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import com.example.workflow.service.ProcessService;

@Service("serviceTaskDelegate")
public class ServiceTaskDelegate implements JavaDelegate  {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.setVariable(ProcessService.PROCESS_VARIABLE, "PROCESS_VARIABLE");
		execution.setVariableLocal(ProcessService.LOCAL_VARIABLE, "LOCAL_VARIABLE");
		System.out.println("serviceTaskDelegate");
//		System.out.println(execution.getVariable(ProcessService.LOCAL_VARIABLE));
//		System.out.println(execution.getVariableLocal(ProcessService.LOCAL_VARIABLE));
	}

}
