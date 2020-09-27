package com.scd.erp.service;

import com.scd.erp.user.CountClass;
import com.scd.erp.user.Project;
import com.scd.erp.user.ProjectRole;

import java.util.List;

public interface ProjectService {

    boolean saveProject(Project project);

    List<Project> selectProject();

    boolean deleteProject(Integer pid);

    boolean updateProject(Project project);

    List<Project> addProjectPerson(Integer pid, Integer uid);

    List updateProjectPerson(ProjectRole projectRole, Integer pid);

    List<Project> selectALLProjectMsg(Integer pid);

    List<Project> deleteProjectPerson(Integer uid, Integer pid);

    List<Project> selectProjectByName(String name);

    List<CountClass> projectStatistics();

    List<Project> selectProjectByPname(String pname);

    List<Project> selectProjectByuid(Integer uid);
}
