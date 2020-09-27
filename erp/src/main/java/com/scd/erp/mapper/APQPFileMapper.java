package com.scd.erp.mapper;

import com.scd.erp.user.APQP.APQPFile;
import com.scd.erp.user.APQP.APQPFmea;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface APQPFileMapper extends Mapper<APQPFile> {
    void saveFileMsg(APQPFile apqpFile);

    APQPFile selectByID(Integer ID);

    List<APQPFile> select(Integer id);
}
