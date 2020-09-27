package com.scd.erp.controller;

import com.scd.erp.service.TaskService;
import com.scd.erp.user.CountClass;
import com.scd.erp.user.Task;
import com.scd.erp.utils.extraUtil.LoginUtile;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "task/", method = RequestMethod.POST)
@Controller
public class taskController {

    private final TaskService taskService;
    private LoginUtile lu;

    @Autowired
    public taskController(TaskService taskService) {
        this.taskService = taskService;
        this.lu = new LoginUtile();
    }

    @RequiresPermissions("add:pro")
    @RequestMapping("/addTask.do")
    @ResponseBody
    public List<Task> addTask(Task task, HttpServletRequest request, Integer pid) {
        Integer userid = lu.getUser(request).getUserid();
        List<Task> i = taskService.addTask(task, userid, pid);
        return i;
    }

    @RequiresPermissions("select:pro")
    @RequestMapping("/selectTask.do")
    @ResponseBody
    public List<Task> selectTask(HttpSession session, Integer pid) {
        return taskService.selectTask(lu.getUser(session).getUserid(), pid);
    }

    @RequiresPermissions("del:pro")
    @RequestMapping("/delTask.do")
    @ResponseBody
    public List<Task> delTask(Integer tid, HttpSession session, Integer pid) {

        Integer userid = lu.getUser(session).getUserid();
        if(taskService.delTask(tid, userid, pid)){
            return taskService.selectTask(userid, pid);
        }
        return null;
    }

    @RequiresPermissions("set:pro")
    @RequestMapping("/updateTask.do")
    @ResponseBody
    public Boolean updateTask(Task task) {
        return taskService.updateTask(task);
    }

    /**
     * 修改任务状态
     *
     * @return
     */
    @RequiresPermissions("set:pro")
    @RequestMapping("/updateTaskState.do")
    @ResponseBody
    public Boolean updateTaskState(Integer tid, Integer state) {
        return taskService.updateTaskState(tid, state);
    }


    /**
     * 统计任务数据
     *
     * @return
     */
    @RequiresPermissions("count:pro")
    @RequestMapping("/taskStatistics.do")
    @ResponseBody
    public Map taskStatistics() {
        List<CountClass> list = taskService.taskStatistics();
        double sum = list.stream().mapToDouble(CountClass::getCOUNT).sum();
        Map map = new HashMap<>();
        map.put("list",list);
        map.put("sum",sum);
        return map;
    }

}
