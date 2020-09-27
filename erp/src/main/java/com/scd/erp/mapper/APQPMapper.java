package com.scd.erp.mapper;

import com.scd.erp.user.APQP.APQPFmea;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface APQPMapper extends Mapper<APQPFmea> {
    int insertAPQPFmea(APQPFmea apqpFmea);

    void saveAPQPFileID(@Param("fmeaId") Integer fmeaId, @Param("fid") Integer fid);

    List<APQPFmea> selectAPQPMsg(Integer id);
}
