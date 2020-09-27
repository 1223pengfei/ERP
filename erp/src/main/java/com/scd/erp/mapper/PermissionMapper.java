package com.scd.erp.mapper;

import com.scd.erp.Vo.auth.Permission;
import com.scd.erp.Vo.auth.Rule;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper extends IBaseDao<Permission> {
    List<Rule> getpermission();
}