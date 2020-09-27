package com.scd.erp.mapper;

import com.scd.erp.Vo.Alarm.Alarm;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AlarmMapper extends IBaseDao<Alarm> {

    List<Alarm> getList(Map map);

    @Insert("INSERT into erp_trace_alarm_center (alarmID,materailID) VALUES (#{alarmid},#{mid})")
    void cmlink(@Param("mid") int i, @Param("alarmid") Integer alarmid);

    @Insert("insert into erp_trace_nail_alarm_center (nailID,alarmID) values (#{nid},#{aid})")
    void linkAN(@Param("nid") int nid, @Param("aid") int aid);

    List<Map> countOEM(Integer where);

    Integer alarmCount(Map map);
}