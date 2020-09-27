package com.scd.erp.listener;

import com.scd.erp.Vo.Alarm.Alarm;
import com.scd.erp.Vo.plm.Document.Version;
import com.scd.erp.constant.ConstantMaterials;
import com.scd.erp.mapper.*;
import com.scd.erp.utils.common.RedisUtils;
import com.scd.erp.utils.common.pinyinUtils;
import com.scd.erp.utils.extraUtil.ConsoleMsg;
import com.scd.erp.utils.extraUtil.ExampleUtile;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class CenterListenter implements ApplicationListener<ContextRefreshedEvent> {

    private final RedisUtils redisUtils;
    private final AlarmMapper alarmMapper;
    private final BaseMaterialMapper materialMapper;
    private final MenuMapper menuMapper;
    private String[] Keys = ConstantMaterials.KEYS;

    @Autowired private DocumentMapper documentMapper;
    @Autowired private VersionMapper versionMapper;
    @Autowired private ExampleUtile exampleUtile;

    @Autowired
    public CenterListenter(RedisUtils redisUtils, AlarmMapper alarmMapper,BaseMaterialMapper materialMapper,MenuMapper menuMapper) {
        this.redisUtils = redisUtils;
        this.alarmMapper = alarmMapper;
        this.materialMapper = materialMapper;
        this.menuMapper = menuMapper;
    }

    @Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		 if(event.getApplicationContext().getParent() == null) {

           redisUtils.del(Keys);
           redisUtils.delete(Keys[2]+"*");
           redisUtils.delete(redisUtils.getKeys(Keys[1]));
			//加载追溯信息
             List<Alarm> list = alarmMapper.getList(null);
             redisUtils.listSet(Keys[0],list);
             Map mapc = new HashMap();
             Map mape = new HashMap();
             for (int i = 0;i < list.size();i++) {
                 Alarm al = list.get(i);
                 StringBuilder sb = new StringBuilder();

                 long crts = al.getCreattime().getTime();
                 mapc.put(crts+"",i+"");
                 long crto = al.getEndtime()!=null?al.getEndtime().getTime():0;
                 mape.put(crto+"",i+"");
                 String key = sb.toString();
                 redisUtils.set(key,i);

                 sb = new StringBuilder();
                 sb.append(Keys[0]);
                 Integer alarmstate = al.getAlarmstate();
                 sb.append("_state-"+alarmstate);

                 Integer fuserid = al.getFuserid();
                 sb.append("_fzr-"+fuserid+"_");

                 Integer nailid = al.getNails().get(0).getNailid();
                 sb.append("_que-"+nailid);
                 String tname = al.getTuser().getPerson().getPersonname();
                 try {
                     sb.append("_tcz-"+ pinyinUtils.toPinYin(tname));
                 } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                     badHanyuPinyinOutputFormatCombination.printStackTrace();
                 }

                 Integer oemid = al.getOem().getOemid();
                 sb.append("_oem-"+oemid);

                 String serialnumber = al.getSerial().getSerialnumber();
                 sb.append("_num-"+serialnumber);

                 Integer typeid = al.getSerial().getRemark().getProduct().getTypeid();
                 sb.append("_type-"+typeid);

                 Integer productid = al.getSerial().getRemark().getProduct().getProductid();
                 sb.append("_pid-"+productid);

                 key = sb.toString();
                 redisUtils.set(key,i);
             }
                redisUtils.setMap(Keys[0]+"_creat",mapc);
                redisUtils.setMap(Keys[0]+"_end",mape);
             //加载ERPnum
             List<String> erpnum = this.materialMapper.getERPNUM();

             for (String num:erpnum) {
                 String prefix = num.substring(0, 2);
                 int n = Integer.parseInt(num.substring(2, num.length()));
                 String key = Keys[2] + prefix;
                 ConsoleMsg.log(key+":"+n);
                 redisUtils.set(key,n);
             }

             redisUtils.listSet(Keys[3],this.menuMapper.getAll());

             List<String> documents = this.documentMapper.getDocNum();
             for (String doc :documents) {
                 String[] nums = doc.split("_");
                 int i = Integer.parseInt(nums[2]);
                 String key = Keys[4] + nums[0]+"_" + nums[1];
                 ConsoleMsg.log(key+":"+i);
                 redisUtils.set(key,i);
             }

             List<Version> vernums = this.versionMapper.getNowVersion();
             for (Version v:vernums) {
                 String key = Keys[5] + v.getDocid();
                 Integer value = v.getVersioncode();
                 ConsoleMsg.log(key+":"+value);
                 redisUtils.set(key, value);
             }



         }

	}

}
