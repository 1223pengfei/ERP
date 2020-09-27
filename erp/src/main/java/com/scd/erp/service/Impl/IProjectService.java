package com.scd.erp.service.Impl;

import com.scd.erp.mapper.ProjectMapper;
import com.scd.erp.service.ProjectService;
import com.scd.erp.user.CountClass;
import com.scd.erp.user.Project;
import com.scd.erp.user.ProjectRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class IProjectService implements ProjectService {
    private final ProjectMapper pm;

    @Autowired
    public IProjectService(ProjectMapper pm) {
        this.pm = pm;
    }

    @Override
    public boolean saveProject(Project project) {
        return pm.saveProject(project);
    }

    @Override
    public List<Project> selectProject() {
            return pm.selectProject();
    }

    @Override
    public List<Project> selectProjectByuid(Integer uid) {
        return pm.selectProjectByuid(uid);
    }

    @Override
    public boolean deleteProject(Integer pid) {
        return pm.deleteProject(pid);
    }

    @Override
    public boolean updateProject(Project project) {
        return pm.updateProject(project);
    }


    @Override
    public List<Project> addProjectPerson(Integer pid, Integer uid) {
        int i = pm.selectUid(pid, uid);
        if (i!=0) {
            List list2 = new ArrayList<Map<String, Object>>();
            list2.add("用户已添加");
            return list2;
        } else {
            boolean b = pm.addProjectPersonTrue(pid, uid);
            return pm.selectALLProjectMsg(pid);
        }


    }


    @Override
    public List<Project> deleteProjectPerson(Integer uid, Integer pid) {


        if (pm.deleteProjectPerson(uid, pid)) {
            return pm.selectALLProjectMsg(pid);
        } else {
            List list2 = new ArrayList<Map<String, Object>>();
            list2.add("删除失败");
            return list2;
        }
    }

    @Override
    public List<Project> selectProjectByName(String name) {
        return pm.selectProjectByName(name);
    }

    /**
     * 项目数据统计
     *
     * @return
     */

    @Override
    public List<CountClass> projectStatistics() {
        return pm.projectStatistics();
    }

    @Override
    public List<Project> updateProjectPerson(ProjectRole projectRole, Integer pid) {

        if (pm.updateProjectPerson(projectRole)) {
            return pm.selectALLProjectMsg(pid);
        } else {
            List list2 = new ArrayList<Map<String, Object>>();
            list2.add("修改失败");
            return list2;

        }

    }

    @Override
    public List<Project> selectALLProjectMsg(Integer pid) {
        return pm.selectALLProjectMsg(pid);
    }

    /**
     * 条件查询项目信息
     * @param pname
     * @return
     */
    @Override
    public List<Project> selectProjectByPname(String pname) {
        return pm.selectProjectByPname(pname);
    }




}
