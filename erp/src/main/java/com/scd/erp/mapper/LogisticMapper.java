package com.scd.erp.mapper;

import com.scd.erp.Vo.plm.Material.Logistic;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogisticMapper extends IBaseDao<Logistic> {
    Logistic getLogistic(Integer id);
}