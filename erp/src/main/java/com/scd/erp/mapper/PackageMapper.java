package com.scd.erp.mapper;

import com.scd.erp.Vo.Doc.Package;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PackageMapper extends IBaseDao<Package> {
    List<Package> allFile(@Param("personid") Integer userPresonid, @Param("departid") Integer departid, @Param("packageid") Integer presonid);
}