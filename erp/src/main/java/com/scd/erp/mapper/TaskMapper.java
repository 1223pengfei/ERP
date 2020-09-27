package com.scd.erp.mapper;

import com.scd.erp.user.CountClass;
import com.scd.erp.user.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskMapper {
    boolean addTak(Task task);

    boolean addUidPidTid(@Param("uid") Integer uid, @Param("pid") Integer pid, @Param("tid") int tid);

    boolean delTask(int tid);

    List<Task> selectTask(@Param("pid") Integer pid, @Param("uid") Integer uid);

    boolean selectTask(Integer tid);

    boolean delUserProjectTask(@Param("tid") Integer tid, @Param("uid") Integer uid, @Param("pid") Integer pid);

    boolean updateTask(Task task);

    List<CountClass> taskStatistics();

    boolean updateTaskState(@Param("tid") Integer tid, @Param("state") Integer state);
}
