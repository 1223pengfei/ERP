package com.scd.erp.mapper;

import com.scd.erp.Vo.Doc.Collect;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollectMapper extends IBaseDao<Collect> {

    List<Collect> withPerson(@Param("personid") Integer personID);

    Collect getCollect(@Param("collectid") Integer collectid);
}