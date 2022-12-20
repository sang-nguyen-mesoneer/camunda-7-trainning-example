package com.example.workflow.bpmn.lyfecycle;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import com.example.workflow.service.ProcessService;

@Service("beforeUserTaskDelegate")
public class BeforeUserTaskDelegate implements TaskListener  {


	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("beforeUserTaskDelegate");
		delegateTask.setVariableLocal(ProcessService.LOCAL_VARIABLE, "LOCAL_VARIABLE");
	}

}

