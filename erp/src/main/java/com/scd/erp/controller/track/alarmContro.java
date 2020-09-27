package com.scd.erp.controller.track;

import com.scd.erp.Vo.Alarm.Alarm;
import com.scd.erp.Vo.Alarm.Question;
import com.scd.erp.Vo.Nail.Feature;
import com.scd.erp.Vo.Nail.Nail;
import com.scd.erp.Vo.Person.User;
import com.scd.erp.Vo.ToExcel.TraceToExcel;
import com.scd.erp.service.trace.AlarmServie;
import com.scd.erp.utils.common.JsonUtil;
import com.scd.erp.utils.extraUtil.LoginUtile;
import com.scd.erp.utils.trace.easyPoiUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("alarm")
public class alarmContro {

    private final AlarmServie alarmServie;
    private LoginUtile lu;

    @Autowired
    public alarmContro(AlarmServie alarmServie) {
        this.alarmServie = alarmServie;
        this.lu = new LoginUtile();
    }

    @RequiresPermissions("add:alarm")
    @PostMapping("Set.do")
    @ResponseBody
    public Map Set(Alarm alarm, Question question,
                   @RequestParam("MIDs") String mids,
                   @RequestParam("NIDs") String nids,
                   HttpServletRequest req) {

        Integer userid = lu.getUser(req).getUserid();
        alarm.setTuserid(userid);
        if (alarm.getSerailid() != null) {
            this.alarmServie.Set(alarm, question, mids, nids);
        }
        return this.getList(null, 10, 1);
    }


    @RequiresPermissions("select:alarm")
    @PostMapping("List.do")
    @ResponseBody
    public Map getList(@Param("select") String select, @Param("row") Integer row,
                        @Param("page") Integer page) {
        Map map = null;
        if (select != null && !select.equals("")) {
            map = getMap(select);
        }
        if (row != null && page != null) {
            page = (page - 1) * row;
        }
        return this.alarmServie.getList(map,row,page);

    }

    private Map getMap(String select) {
        Map map = null;
        //System.out.println("s:"+select);
        if (select != null && !select.equals("") && !select.equals("null")) {
            map = new LinkedHashMap();
            String[] p = select.split("_");
            for (String se : p) {
                //System.out.println(se);
                String[] s = se.split("&");
                if (s.length>1&&!s[1].equals("null")) {
                    map.put(s[0], s[1]);
                }
            }
        }
        return map;
    }

    @PostMapping("questions.do")
    @ResponseBody
    public List<Question> ques() {
        return this.alarmServie.ques();
    }

    @RequiresPermissions("down:alarm")
    @PostMapping("download.do")
    @ResponseBody
    public void down(String select, HttpServletResponse response) {

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String fileName = sdf.format(new Date())+"追溯信息";
        List list = (List) this.getList(select, null, null).get("data");
        List<TraceToExcel> elist = new LinkedList<>();
        for (Object object : list) {
            Alarm alarm = JsonUtil.objToPojo(object,Alarm.class);
            List<Nail> nails = alarm.getNails();
            StringBuilder sbNail = new StringBuilder();
            StringBuilder sbFeater = new StringBuilder();
            for (Nail nail : nails) {
                sbNail.append(nail.getNailname());
                sbNail.append("\r\n");
                List<Feature> features = nail.getFeatures();
                if (null != features && features.size() != 0) {
                    for (Feature f : features) {
                        sbFeater.append(f.getFeature());
                        sbFeater.append("\r\n");
                    }
                }
            }
            TraceToExcel excel = new TraceToExcel(
                    alarm.getCreattime(),
                    alarm.getTuser().getPerson().getJob().getDepartment().getDepname(),
                    alarm.getTuser().getPerson().getPersonname(),
                    alarm.getSerial().getRemark().getProduct().getTypeid(),
                    alarm.getOem().getOemname(),
                    alarm.getSerial().getRemark().getProduct().getProname(),
                    alarm.getSerial().getRemark().getProduct().getProcode(),
                    alarm.getSerial().getSerialnumber(),
                    alarm.getFuser().getPerson().getPersonname(),
                    alarm.getQuest().getCause(),
                    alarm.getQuest().getSolution(),
                    alarm.getRemark(),
                    alarm.getAlarmstate(),
                    alarm.getEndtime(),
                    sbNail.toString(),
                    sbFeater.toString()

            );
            elist.add(excel);
        }
        easyPoiUtil.exportExcel(elist, fileName, "shit1",
                TraceToExcel.class, fileName + ".xls", response);
    }

    @RequiresPermissions("select")
    @PostMapping("getUser.do")
    @ResponseBody
    public List<User> getUser(String name) {
        return this.alarmServie.getUser(name);

    }
}
