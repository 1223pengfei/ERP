package com.scd.erp.mapper;

import com.scd.erp.Vo.plm.Document.Version;
import com.scd.erp.utils.IBaseDao.IBaseDao;

import java.util.List;

public interface VersionMapper extends IBaseDao<Version> {
    List<Version> getNowVersion();
}