package com.scd.erp.service;

import com.scd.erp.user.CountClass;
import com.scd.erp.user.Task;

import java.util.List;

public interface TaskService {

    List<Task> addTask(Task task, Integer uid, Integer pid);

    List<Task> selectTask(Integer uid, Integer pid);

    boolean delTask(Integer tid, Integer uid, Integer pid);

    boolean updateTask(Task task);

    List<CountClass> taskStatistics();

    boolean updateTaskState(Integer tid, Integer state);
}
