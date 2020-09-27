package com.scd.erp.mapper;

import com.scd.erp.Vo.Department.Department;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper extends IBaseDao<Department> {
    List<Department> getList();
}