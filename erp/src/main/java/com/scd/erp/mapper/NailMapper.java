package com.scd.erp.mapper;

import com.scd.erp.Vo.Nail.Nail;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NailMapper extends IBaseDao<Nail> {
    @Insert("insert into erp_trace_nail_feature_center (nailID,featureID) values (#{nid},#{fid})")
    void linkNF(@Param("nid") int nid, @Param("fid") int fid);


    List<Nail> getList();
}