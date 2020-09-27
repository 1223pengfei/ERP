package com.scd.erp.mapper;

import com.scd.erp.Vo.baseData.BasePackage;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasePackageMapper extends IBaseDao<BasePackage> {

    List<BasePackage> selectPackageSelf();
}