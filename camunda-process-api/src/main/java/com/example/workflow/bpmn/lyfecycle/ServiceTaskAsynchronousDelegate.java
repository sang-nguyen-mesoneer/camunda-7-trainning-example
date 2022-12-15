package com.example.workflow.bpmn.lyfecycle;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("serviceTaskAsynchronousDelegate")
public class ServiceTaskAsynchronousDelegate  implements JavaDelegate  {
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("serviceTaskAsynchronousDelegate");
	}
}
