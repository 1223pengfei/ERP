package com.scd.erp.controller.auth;

import com.scd.erp.Vo.Department.Department;
import com.scd.erp.Vo.Department.Job;
import com.scd.erp.mapper.DepartmentMapper;
import com.scd.erp.mapper.JobMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Controller
@RequestMapping("department")
public class departmentContro {

    private final DepartmentMapper departmentMapper;
    private final JobMapper jobMapper;

    @Autowired
    public departmentContro(DepartmentMapper departmentMapper, JobMapper jobMapper) {
        this.departmentMapper = departmentMapper;
        this.jobMapper = jobMapper;
    }

    @RequiresPermissions("add:department")
    @PostMapping("add.do")
    @ResponseBody
    public int add(Department department) {
        //System.out.println(department);
        String code = getCode(department);
        department.setDepcode(code);
        return this.departmentMapper.insertSelective(department);
    }

    private String getCode(Department department) {
        //System.out.println(department);
        int depcode;
        Integer updepid = department.getUpdepid();
        Example ex = new Example(Department.class);
        ex.createCriteria().andEqualTo("updepid", updepid);
        List<Department> departments = this.departmentMapper.selectByExample(ex);
        if (departments.size() != 0) {
            Department dep = departments.get(departments.size() - 1);
            //System.out.println(dep);
            depcode = Integer.parseInt(dep.getDepcode());
            //System.out.println(updepid);
            if (null == updepid || updepid == 0) {
                depcode += 100;
            } else {
                depcode += 1;
            }
        } else {
            Department dep = this.departmentMapper.selectByPrimaryKey(updepid);
            depcode = Integer.parseInt(dep.getDepcode());
            depcode++;
        }
        return "0" + depcode;
    }
    @RequiresPermissions("del:department")
    @PostMapping("del.do")
    @ResponseBody
    public void del(@RequestParam("depid") Integer depid) {
        this.departmentMapper.deleteByPrimaryKey(depid);
    }

    @RequiresPermissions("add:job")
    @PostMapping("addJob.do")
    @ResponseBody
    public List<Job> addJob(Job job) {
        this.jobMapper.insertSelective(job);
        return getJobs(job);
    }

    private List<Job> getJobs(Job job) {
        Example ex = new Example(Job.class);
        ex.createCriteria().andEqualTo("depid", job.getDepid());
        return this.jobMapper.selectByExample(ex);
    }

    @RequiresPermissions("del:job")
    @PostMapping("delJob.do")
    @ResponseBody
    public List<Job> deljob(@RequestParam("jobid") Integer jobid) {
        Job job = this.jobMapper.selectByPrimaryKey(jobid);
        this.jobMapper.deleteByPrimaryKey(jobid);
        return getJobs(job);
    }

    @PostMapping("list.do")
    @ResponseBody
    public List<Department> List() {
        return this.departmentMapper.getList();
    }

   
}
