package com.scd.erp.service.trace;

import com.scd.erp.Vo.Alarm.Alarm;
import com.scd.erp.Vo.Alarm.Question;
import com.scd.erp.Vo.Person.User;

import java.util.List;
import java.util.Map;

public interface AlarmServie {


    boolean Set(Alarm alarm, Question question, String mids, String nids);

    Map getList(Map map, Integer row, Integer page);

    List<Question> ques();

    List<User> getUser(String name);

    Integer getAlarmCount(Map map);
}
