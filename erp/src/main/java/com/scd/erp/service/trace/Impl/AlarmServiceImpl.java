package com.scd.erp.service.trace.Impl;

import com.scd.erp.Vo.Alarm.Alarm;
import com.scd.erp.Vo.Alarm.Question;
import com.scd.erp.Vo.Person.User;
import com.scd.erp.mapper.AlarmMapper;
import com.scd.erp.mapper.QuestionMapper;
import com.scd.erp.mapper.UserMapper;
import com.scd.erp.service.trace.AlarmServie;
import com.scd.erp.utils.common.RedisUtils;
import com.scd.erp.utils.common.pinyinUtils;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AlarmServiceImpl implements AlarmServie {
    private final AlarmMapper alarmMapper;
    private final QuestionMapper questionMapper;
    private final UserMapper userMapper;
    @Autowired
    private RedisUtils redisUtils;

    private final static String ALARM = "alarm";

     @Autowired
    public AlarmServiceImpl(AlarmMapper alarmMapper, QuestionMapper questionMapper, UserMapper userMapper) {
        this.alarmMapper = alarmMapper;
        this.questionMapper = questionMapper;
        this.userMapper = userMapper;
     }

   public Map getList(Map map,Integer row, Integer page) {
      int index = -1;
      if (null != row) {
          index = page + row - 1;
      }else{
          page = 0;
      }
       Set rkeys = new HashSet();
       boolean b = true;
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       if (null == map || map.isEmpty()) {
          List<Object> objects = redisUtils.lGet(ALARM, page, index);
          long l = redisUtils.lGetListSize(ALARM);
          return remap(objects,l);
      }else{

          Object[] keys = map.keySet().toArray();
          StringBuilder sb = new StringBuilder();
          sb.append("alarm_*");
          long cs = 0,co = 0,es = 0,eo = 0;
           try {
          for (Object ob:keys) {
              Object o = map.get(ob);
              switch (ob.toString()){

                  case "crts":
                          cs = sdf.parse(o.toString()).getTime();
                          break;
                  case "crto":
                          co = sdf.parse(o.toString()).getTime();

                      Set mapKey = redisUtils.getMapKey(ALARM + "_creat");
                      for (Object obc:mapKey) {
                          long l = Long.parseLong(obc.toString());
                          if (l>cs&&l<co) {
                              rkeys.add(redisUtils.getMapValue(ALARM + "_creat", l + ""));
                          }
                       }
                      b=false;
                      break;
                  case "erts":
                      es = sdf.parse(o.toString()).getTime();
                      break;
                  case "erto":
                      eo = sdf.parse(o.toString()).getTime();
                      Set mapKeye = redisUtils.getMapKey(ALARM + "_end");
                      for (Object obc:mapKeye) {
                          long l = Long.parseLong(obc.toString());
                          if (l>es&&l<eo) {
                              rkeys.add(redisUtils.getMapValue(ALARM + "_end", l + ""));
                          }
                      }
                      b=false;
                      break;
                  case "tcz":
                      try {
                          o = pinyinUtils.toPinYin(o.toString());
                      } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                          badHanyuPinyinOutputFormatCombination.printStackTrace();
                      }

                      default:
                          sb.append(ob+"-"+ o +"_*");
                          break;
              }

          }
           } catch (ParseException e) {
               e.printStackTrace();
           }
          String sbstring = sb.toString();
          //System.out.println(sbstring);

          rkeys = b?redisUtils.getKeys(sbstring):rkeys;
           return getMap(page, index, rkeys);
      }
       //return this.alarmMapper.getList(map);
    }

    private Map getMap(int page, int index, Set rkeys) {
        List list = new LinkedList();
        for (Object key:rkeys
             ) {
            list.add(redisUtils.lGetIndex(ALARM, redisUtils.objToInt(key.toString())));
        }
        int size = list.size();
        System.out.println(size+">>"+index);
        return remap(size > index?list.subList(page, index):list.subList(page,size), size);
    }

    private Map remap(List<Object> objects, long l) {
      Map  map = new LinkedHashMap();
            map.put("count", l);
            map.put("data", objects);
            return map;
    }

    public List<Question> ques() {
        return this.questionMapper.selectAll();
    }




    @Override
    public List<User> getUser(String name) {
        Example ex = new Example(User.class);
        ex.createCriteria().andEqualTo("personname", name);
        return this.userMapper.selectByExample(ex);
    }


    public Integer getAlarmCount(Map map) {
        return Math.toIntExact(redisUtils.lGetListSize(ALARM));
    }

      public boolean Set(Alarm alarm, Question question, String mids, String nids) {
        Integer id = alarm.getAlarmid();
        int qid = this.qSet(question);
        if(redisUtils.lock(ALARM)) {
            lockAddAlarm(alarm, mids, nids, id, qid);
            lockRedis();
            return true;
        }else {
            int failCount = 1;
            while(failCount <= 5){
                // 等待100ms重试
                try {
                    Thread.sleep(100l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (redisUtils.lock(ALARM)){
                    lockAddAlarm(alarm, mids, nids, id, qid);
                    lockRedis();

                    return true;
                }else{
                    failCount ++;
                }
            }

                throw new RuntimeException("现在创建的人太多了, 请稍等再试");



        }

    }

    private void lockAddAlarm(Alarm alarm, String mids, String nids, Integer id, int qid) {
        if (id != null) {
            setupAlarm(alarm);
        } else {
            addAlarm(alarm, qid);
            linkCM(alarm, mids);
            ANlink(alarm, nids);
        }
    }

    private void lockRedis() {
        redisUtils.del(ALARM);
        List<Alarm> list = this.alarmMapper.getList(null);
        redisUtils.listSet(ALARM, list);
        //System.out.println(redisUtils.lGetListSize(ALARM));
        redisUtils.delete(ALARM);
    }

    private void ANlink(Alarm alarm, String nids) {
        String[] NIDs = nids.split("_");
        for (String nid : NIDs) {
            this.alarmMapper.linkAN(Integer.parseInt(nid), alarm.getAlarmid());
        }
    }

    private void linkCM(Alarm alarm, String mids) {
        if (mids != null && !mids.equals("")) {
            String[] MIDs = mids.split("_");
            for (String mid : MIDs) {
                this.alarmMapper.cmlink(Integer.parseInt(mid), alarm.getAlarmid());
            }
        }
    }

   //
   public void addAlarm(Alarm alarm, int qid) {
        alarm.setCreattime(new Date());
        alarm.setAlarmstate(0);
        if (alarm.getQuestionid() == null) {
            alarm.setQuestionid(qid);
        }
       this.alarmMapper.insertSelective(alarm);
   }

    private void setupAlarm(Alarm alarm) {
        this.alarmMapper.updateByPrimaryKey(alarm);
    }


    private int qSet(Question question) {
        Integer id = question.getQuestionid();
        if (id != null) {
            updataQuestion(question);
        } else {
            id = addQuestion(question);
        }
        return id;
    }


    private Integer addQuestion(Question question) {
        Integer id;
        this.questionMapper.insertSelective(question);
        id = question.getQuestionid();
        return id;
    }


    private void updataQuestion(Question question) {
        this.questionMapper.updateByPrimaryKey(question);
    }

}
