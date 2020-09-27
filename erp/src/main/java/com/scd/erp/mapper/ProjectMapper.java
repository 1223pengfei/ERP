package com.scd.erp.mapper;

import com.scd.erp.user.CountClass;
import com.scd.erp.user.Project;
import com.scd.erp.user.ProjectRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {

    boolean saveProject(Project project);

    List<Project> selectProject();

    boolean deleteProject(Integer pid);

    boolean updateProject(Project project);

    List<Project> selectProjectByuid(Integer uid);

    boolean addProjectPersonTrue(@Param("uid") Integer uid, @Param("pid") Integer pid);

    int selectUid(@Param("pid") Integer pid, @Param("uid") Integer uid);

    boolean updateProjectPerson(ProjectRole projectRole);

    List<Project> selectALLProjectMsg(Integer pid);

    boolean deleteProjectPerson(@Param("uid") Integer uid, @Param("pid") Integer pid);

    List<Project> selectProjectByName(String name);

    List<CountClass> projectStatistics();

    List<Project> selectProjectByPname(String pname);
}
