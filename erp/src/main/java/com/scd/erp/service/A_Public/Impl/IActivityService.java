package com.scd.erp.service.A_Public.Impl;

import com.scd.erp.Vo.Person.Person;
import com.scd.erp.Vo.Person.User;
import com.scd.erp.Vo.Process.Annex;
import com.scd.erp.Vo.Process.AnnexLink;
import com.scd.erp.Vo.Process.AnnexRecode;
import com.scd.erp.Vo.Process.Process;
import com.scd.erp.Vo.plm.Document.Document;
import com.scd.erp.Vo.plm.Document.Version;
import com.scd.erp.mapper.*;
import com.scd.erp.service.A_Public.ActivityService;
import com.scd.erp.utils.common.JsonUtil;
import com.scd.erp.utils.extraUtil.*;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
@Service
public class IActivityService implements ActivityService {

    @Autowired private FlowableUtil flowableUtil;
    @Autowired private PersonMapper personMapper;

    private static final String PROCESS_ID = "materapp";

    private LoginUtile loginUtile = new LoginUtile();

    @Autowired private UserMapper userMapper;
    @Autowired private ExampleUtile exampleUtile;
    @Autowired private VersionMapper versionMapper;
    @Autowired private DocumentMapper documentMapper;
    @Autowired private AnnexLinkMapper annexLinkMapper;
    @Autowired private AnnexMapper annexMapper;
    @Autowired private AnnexRecodeMapper recodeMapper;
    @Autowired private ProcessMapper processMapper;
    @Autowired private CheckMapper checkMapper;

    public void complet(@PathVariable String taskid, AnnexRecode recode, User user) {
        Annex annex = getAnnex(taskid, recode, user);
        String schedule = annex.ScheduleHandle(1);
        ConsoleMsg.log("完成率："+schedule);
        annex.setState(schedule.contains("100")?2:1);
        flowableUtil.setVariables(taskid,"annex",annex);
        this.annexMapper.updateByPrimaryKeySelective(annex);
        Map<String, Object> map = new HashMap<>();
        map.put("pass",recode.getResult());
        flowableUtil.complete(taskid, map);
    }

    public void approve(@PathVariable(value = "{taskid}") String taskId, AnnexRecode recode, User user) {
        Annex annex = getAnnex(taskId, recode, user);
        annex.setState(recode.getResult()?3:-1);
        flowableUtil.setVariables(taskId,"annex",annex);
        this.annexMapper.updateByPrimaryKeySelective(annex);
        Map<String,Object> map = new HashMap<>();
        //设置流程变量
        map.put("approved", recode.getResult());
        //再流转下一个节点
        flowableUtil.complete(taskId, map);
    }

    public List<Map<String, Object>> getTask(User user) {
        List<Task> tasks = flowableUtil.getListByUserId(user.getUserid()+"");
        List<Map<String,Object>> list = new ArrayList<>();
        for(Task task : tasks){
            Map<String,Object> processVariables= flowableUtil.getVariables(task);
            Map<String,Object> tempMap = new LinkedHashMap<>();
            tempMap.put("taskid",task.getId());
            tempMap.put("taskName",task.getName());
            Object annex = processVariables.get("annex");
            tempMap.put("annex", annex);
            System.out.println(annex);
            list.add(tempMap);
        }
        return list;
    }

    public BaseResponse<List<Annex>> selectAnnex(@PathVariable Integer userid) {
        Example ex = exampleUtile.getExample(Annex.class, new ChainMap().putObj("creatuserid", userid));
        List<Annex> annexes = this.annexMapper.selectByExample(ex);
        return BaseResponse.success(annexes);
    }


    public BaseResponse<String> applyCountersign(Annex annex, Integer processid, String fileids, User user) {
        List<Integer> iDs = this.checkMapper.getIDs(processid);
        annex.setCreattime(new Date());
        annex.setState(0);
        annex.setChecknumber(iDs.size());
        annex.setCheckednumber(0);
        annex.setCreatuserid(user.getUserid());
        ConsoleMsg.log(annex);
        this.annexMapper.insertSelective(annex);

        Person person = this.personMapper.getPersonAll(user.getPresonid());
        Process process = this.processMapper.selectByPrimaryKey(processid);
        user.setPerson(person);
        annex.setPerson(person);
        String[] vids = fileids.split("_");
        List<Document> documents = new LinkedList<>();
        for (String id:vids) {
            Version version = this.versionMapper.selectByPrimaryKey(id);
            Document document = this.documentMapper.selectByPrimaryKey(version.getDocid());
            document.setVersion(version);
            documents.add(document);
            this.annexLinkMapper.insertSelective(new AnnexLink(annex.getAnnexid(),Integer.parseInt(id)));
        }
        annex.setDocuments(documents);


        Map<String,Object> map = new HashMap<>();
        map.put("appller",process.getApplyuserid());
        map.put("auditors", iDs);
        map.put("annex",annex);
        map.put("taskUser",user.getUserid());
        String start = flowableUtil.start(PROCESS_ID, map);
        return BaseResponse.success("发起流程成功");
    }


    private Annex getAnnex(String taskid, AnnexRecode recode, User user) {
        Annex annex = JsonUtil.objToPojo(flowableUtil.getVariables(taskid).get("annex"), Annex.class);
        List<AnnexRecode> recodes = annex.getRecodes();
        if (null == recodes)recodes = new ArrayList<>();
        recode.setAppllerid(user.getUserid());
        recode.setApplytime(new Date());
        recode.setAnnexid(annex.getAnnexid());
        recodes.add(recode);
        this.recodeMapper.insertSelective(recode);
        annex.setRecodes(recodes);
        return annex;
    }
}
