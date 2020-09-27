package com.scd.erp.handler.flowableHandler;

import com.scd.erp.Vo.Process.Annex;
import com.scd.erp.utils.extraUtil.ConsoleMsg;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.api.Task;
import org.flowable.task.service.delegate.DelegateTask;

public class AuditorHandler extends EndHandler implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("会签监听");

        String exId = delegateTask.getExecutionId();
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();
        boolean pass = (Boolean) runtimeService.getVariable(exId, "pass");

        if(pass == false){
            runtimeService.setVariable(exId, "result", "N");
            String processInstanceId = delegateTask.getProcessInstanceId();
            Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
            ConsoleMsg.log("会签结束，有人拒绝");
        }else{
            Integer complete = (Integer) runtimeService.getVariable(exId, "nrOfCompletedInstances");
            Integer all = (Integer) runtimeService.getVariable(exId, "nrOfInstances");
            if((complete + 1) / all == 1){
                runtimeService.setVariable(exId, "result", "Y");
                String processInstanceId = delegateTask.getProcessInstanceId();
                Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
                ConsoleMsg.log("会签结束，无人拒绝");
                handlethis(runtimeService.getVariable(exId,"annex",Annex.class));
            }
        }

    }
}
