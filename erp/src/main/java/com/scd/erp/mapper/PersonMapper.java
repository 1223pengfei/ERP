package com.scd.erp.mapper;

import com.scd.erp.Vo.Person.Person;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonMapper extends IBaseDao<Person> {
    List<Person> List(Map maps);

    Person getPersonAll(@Param("pid") Integer presonid);

    int getCount();

    List<Person> getNames();
}