package com.scd.erp.utils.extraUtil;

import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FlowableUtil
 * @Description 工作流工具
 * @Version 1.0
 **/
@Component
public class FlowableUtil {
    @Autowired private RepositoryService repositoryService;
    @Autowired private RuntimeService runtimeService;
    @Autowired private TaskService taskService;
    @Autowired private ProcessEngine processEngine;
    @Autowired private HistoryService historyService;



    /**
     * 启动流程
     *
     * @param processKey  流程定义key(流程图ID)
     * @param map         参数键值对
     * @return 流程实例ID
     */
    public String start(String processKey, Map<String, Object> map) {

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey,  map);
        return processInstance.getId();
    }

    /**
     * 终止流程
     *
     * @param processInstanceId 流程实例ID
     * @param reason            终止理由
     */
    public void stop(String processInstanceId, String reason) {

        runtimeService.deleteProcessInstance(processInstanceId, reason);
    }


    /**
     * 获取指定用户的任务列表（创建时间倒序）
     *
     * @param userId 用户ID
     * @return 任务列表
     */
    public List<Task> getListByUserId(String userId) {

        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
        return tasks;
    }

    /**
     * 获取指定用户组的任务列表
     *
     * @param group 用户组
     * @return 任务列表
     */
    public List<Task> getListByGroup(String group) {

        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(group).orderByTaskCreateTime().desc().list();
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
        return tasks;
    }


    /**
     * 完成指定任务
     *  @param taskId 任务ID
     * @param map    变量键值对
     */
    public void complete(String taskId, Map<String, Object> map) {

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            System.out.println("流程不存在");
        }
        taskService.complete(taskId, map);
    }

    /**
     * 获取指定任务列表中的特定任务
     *
     * @param list        任务列表
     * @param businessKey 业务key
     * @return 任务
     */
    public Task getOneByBusinessKey(List<Task> list, String businessKey) {

        Task task = null;
        for (Task t : list) {
            // 通过任务对象获取流程实例
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(t.getProcessInstanceId()).singleResult();
            if (businessKey.equals(pi.getBusinessKey())) {
                task = t;
            }
        }
        return task;
    }

    /**
     * 创建流程并完成第一个任务
     *
     * @param processKey  流程定义key(流程图ID)
     * @param map         变量键值对
     */
    public void startAndComplete(String processKey,  HashMap<String, Object> map) {

        String processInstanceId = start(processKey, map);
        Task task = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.complete(task.getId(), map);

    }

    /**
     * 退回到指定任务节点
     *
     * @param currentTaskId 当前任务ID
     * @param targetTaskKey 目标任务节点key
     */
    public void backToStep(String currentTaskId, String targetTaskKey) {

        Task currentTask = taskService.createTaskQuery().taskId(currentTaskId).singleResult();
        if (currentTask == null) {
            System.out.println("当前任务节点不存在");
        }
        List<String> currentTaskKeys = new ArrayList<>();
        currentTaskKeys.add(currentTask.getTaskDefinitionKey());
        runtimeService.createChangeActivityStateBuilder().processInstanceId(currentTask.getProcessInstanceId()).moveActivityIdsToSingleActivityId(currentTaskKeys, targetTaskKey);
    }



    public Map<String, Object> getVariables(Task task) {
        return getVariables(task.getId());
    }

    public Map<String, Object> getVariables(String taskid) {
        return taskService.getVariables(taskid);
    }

    public void setVariables(String taskid,String key,Object value){
        taskService.setVariable(taskid,key,value);
    }
}