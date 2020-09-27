package com.scd.erp.service.Impl;

import com.scd.erp.mapper.TaskMapper;
import com.scd.erp.service.TaskService;
import com.scd.erp.user.CountClass;
import com.scd.erp.user.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ITaskService implements TaskService {
    @Autowired
    private TaskMapper tm;

    @Override
    public List<Task> addTask(Task task, Integer uid, Integer pid) {
        boolean b = tm.addTak(task);
        int tid = task.getTid();
        if (b) {
            //System.out.println("新增tid为="+tid);
            boolean i = tm.addUidPidTid(uid, pid, tid);
            List<Task> list = tm.selectTask(pid, uid);
            return list;
        } else {
            List list = new ArrayList();
            boolean f = tm.delTask(tid);
            list.add("添加失败");
            return list;
        }


    }

    @Override
    public List<Task> selectTask(Integer uid, Integer pid) {
        List<Task> list = tm.selectTask(pid, uid);
        return list;
    }

    @Override
    public boolean delTask(Integer tid, Integer uid, Integer pid) {
        if (tid != null) {
            boolean b = tm.delTask(tid);
            if (b) {
                boolean a = tm.delUserProjectTask(tid, uid, pid);
                return a;
            }
        }
        return false;
    }

    @Override
    public boolean updateTask(Task task) {
        boolean b = tm.updateTask(task);
        return b;
    }

    @Override
    public List<CountClass> taskStatistics() {
        List<CountClass> list = tm.taskStatistics();
        return list;
    }

    @Override
    public boolean updateTaskState(Integer tid, Integer state) {
        boolean b = tm.updateTaskState(tid, state);
        return b;
    }
}
