package com.scd.erp.mapper;

import com.scd.erp.Vo.plm.Material.BaseMaterial;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseMaterialMapper extends IBaseDao<BaseMaterial> {
    List<String> getERPNUM();

    BaseMaterial getMaterail(Integer materailid);

}