package com.scd.erp.mapper;

import com.scd.erp.Vo.Process.Check;
import com.scd.erp.utils.IBaseDao.IBaseDao;

import java.util.List;

public interface CheckMapper extends IBaseDao<Check> {
    List<Integer> getIDs(Integer processid);
}