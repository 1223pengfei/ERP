package com.scd.erp.mapper;

import com.scd.erp.Vo.Person.Contact;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContactMapper extends IBaseDao<Contact> {
}