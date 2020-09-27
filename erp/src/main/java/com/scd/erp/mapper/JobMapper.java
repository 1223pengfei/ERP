package com.scd.erp.mapper;

import com.scd.erp.Vo.Department.Job;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JobMapper extends IBaseDao<Job> {
    Job getJob(@Param("pid") Integer presonid);

    List<Job> allJob();
}