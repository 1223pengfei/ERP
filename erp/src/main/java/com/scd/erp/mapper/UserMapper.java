package com.scd.erp.mapper;

import com.scd.erp.Vo.Person.User;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends IBaseDao<User> {
}