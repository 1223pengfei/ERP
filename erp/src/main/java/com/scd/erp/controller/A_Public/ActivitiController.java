package com.scd.erp.controller.A_Public;


import com.scd.erp.Vo.Person.Person;
import com.scd.erp.Vo.Person.User;
import com.scd.erp.Vo.Process.Annex;
import com.scd.erp.Vo.Process.AnnexRecode;
import com.scd.erp.Vo.Process.Check;
import com.scd.erp.Vo.Process.Process;
import com.scd.erp.mapper.*;
import com.scd.erp.service.A_Public.ActivityService;
import com.scd.erp.utils.extraUtil.*;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "Act")
public class ActivitiController {

    private final Logger logger = LoggerFactory.getLogger(ActivitiController.class);


    @Autowired private FlowableUtil flowableUtil;
    @Autowired private PersonMapper personMapper;

    private static final String PROCESS_ID = "materapp";

    private LoginUtile loginUtile = new LoginUtile();

    @Autowired private VersionMapper versionMapper;
    @Autowired private DocumentMapper documentMapper;
    @Autowired private AnnexLinkMapper annexLinkMapper;
    @Autowired private AnnexMapper annexMapper;

    @Autowired private ActivityService activityService;


    @PostMapping("select/annex.do/{userid}")
    public BaseResponse<List<Annex>> selectAnnex(@PathVariable Integer userid){
        return this.activityService.selectAnnex(userid);
    }

    @PostMapping("select/annex.do")
    public BaseResponse<List<Annex>> selectAnnex(HttpSession session){
        User user = loginUtile.getUser(session);
        Integer userid = user.getUserid();
        return this.activityService.selectAnnex(userid);

    }

    @PostMapping("/apply.do/{userid}")
    public BaseResponse<String> applyCountersign(@PathVariable Integer userid,Annex annex,Integer processid , String fileids){
        User user = getUser(userid);
        return this.activityService.applyCountersign(annex, processid, fileids, user);
    }

    @PostMapping("/apply.do")
    public BaseResponse<String> applyCountersign(HttpSession session,Annex annex,Integer processid , String fileids){
        User user = loginUtile.getUser(session);
        return this.activityService.applyCountersign(annex, processid, fileids, user);
    }

    @PostMapping("/getTask/{userid}")
    public BaseResponse<List<Task>> todoList(@PathVariable Integer userid){
        User user = getUser(userid);
        List<Map<String, Object>> list = this.activityService.getTask(user);

        return BaseResponse.success(list);
    }

    @PostMapping("/getTask")
    public BaseResponse<List<Task>> todoList(HttpSession session){
        User user = loginUtile.getUser(session);
        List<Map<String, Object>> list = this.activityService.getTask(user);

       return BaseResponse.success(list);
    }

    @PostMapping("check.do/{taskid}/{userid}")
    public BaseResponse<String> complet(@PathVariable String taskid
            , AnnexRecode recode,@PathVariable( value = "{userid}") Integer userid){
        User user = getUser(userid);
        this.activityService.complet(taskid, recode, user);
        return BaseResponse.success("OK");
    }

    @PostMapping("check.do/{taskid}")
    public BaseResponse<String> complet(@PathVariable String taskid
            , AnnexRecode recode,HttpSession session){
        User user = loginUtile.getUser(session);
        this.activityService.complet(taskid, recode, user);

        return BaseResponse.success("OK");
    }



    @PostMapping(value = "/approve/{taskid}/{userid}")
    public BaseResponse<String> approveProcess(
            @PathVariable( value = "{taskid}") String taskId
            , AnnexRecode recode
            ,@PathVariable( value = "{userid}") Integer userid){
        User user = getUser(userid);
        this.activityService.approve(taskId, recode, user);
        return BaseResponse.success("ok! complet!");
    }


    @PostMapping(value = "/approve/{taskid}")
    public BaseResponse<String> approveProcess(
             @PathVariable( value = "{taskid}") String taskId
            , AnnexRecode recode,HttpSession session){
        User user = loginUtile.getUser(session);
        this.activityService.approve(taskId, recode, user);
        return BaseResponse.success("ok! complet!");
    }


    //===================================================================================================================================================================

    @Autowired private ProcessMapper processMapper;
    @Autowired private CheckMapper checkMapper;

    @PostMapping("add/process.do")
    public BaseResponse<String> addProcess(Process process,String userids){
        ConsoleMsg.log(userids);
        ConsoleMsg.log(process);
           this.processMapper.insertSelective(process);
        String[] ids = userids.split("_");
        for (String id:ids) {
            this.checkMapper.insertSelective(new Check(process.getProcessid(),Integer.parseInt(id)));
        }
        return BaseResponse.success("OK");
    }

    @Autowired private UserMapper userMapper;
    @Autowired private ExampleUtile exampleUtile;

    @GetMapping("select/list.do")
    public BaseResponse<List> selectList(){
        List<Process> processes = this.processMapper.selectAll();
        for (Process p:processes) {
            List<Person> ps = new LinkedList<>();
            Person person = this.personMapper.getPersonAll(p.getApplyuserid());
            p.setAppluUser(person);
            List<Check> checks = getChecks(p.getProcessid());
            for (Check c:checks) {
                Person all = this.personMapper.getPersonAll(c.getCheckerid());
                 ps.add(all);
            }
            p.setCheckUsers(ps);
        }
        return BaseResponse.success(processes);
    }

    private List<Check> getChecks(Integer processid) {
        Example exp = exampleUtile.getExample(Check.class, new ChainMap().putObj("processid", processid));
        return this.checkMapper.selectByExample(exp);
    }

    private User getUser(@PathVariable Integer userid) {
        return userMapper.selectByPrimaryKey(userid);
    }


//    /**
//     * 撤销审批
//     * @param taskId
//     * @return
//     */
//    @PostMapping(value = "/withdraw")
//    public BaseResponse<String> withdrawProcess(
//            @RequestParam( value = "task_id") String taskId){
//        //获取当前任务，让其移动到审核节点位置
//        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//        if(task != null ) {
//            //将节点移动到审核节点
//            runtimeService
//                    .createChangeActivityStateBuilder()
//                    .processInstanceId(task.getProcessInstanceId())
//                    .moveActivityIdTo(task.getTaskDefinitionKey(),"apply")
//                    .changeState();
//        }else{
//            return BaseResponse.fail("500","撤回失败");
//        }
//
//        return BaseResponse.success("撤回成功");
//    }

//    /**
//     * 获取流程变量
//     * @param taskId
//     * @return
//     */
//    @PostMapping(value = "/variable")
//    public BaseResponse<String> getVariables(@RequestParam( value = "task_id") String taskId){
//        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//        Map<String, Object> variables = null;
//        if(task != null){
//            variables  = runtimeService.getVariables(task.getExecutionId());
//        }
//        return BaseResponse.success(variables);
//    }

//    /**
//     * 生成流程图
//     *
//     * @param processId 任务ID
//     */
//
//    @GetMapping(value = "processDiagram")
//    public void genProcessDiagram(HttpServletResponse httpServletResponse,
//                                           @RequestParam( value = "process_id") String processId) throws Exception {
//
//        System.out.println(processId);
//
//        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
//
//        System.out.println(pi);
//        //流程走完的不显示图
//        if (pi == null) {
//            return;
//        }
//        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
//        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
//        String InstanceId = task.getProcessInstanceId();
//        List<Execution> executions = runtimeService
//                .createExecutionQuery()
//                .processInstanceId(InstanceId)
//                .list();
//
//
//        //得到正在执行的Activity的Id
//        List<String> activityIds = new ArrayList<>();
//        List<String> flows = new ArrayList<>();
//        for (Execution exe : executions) {
//            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
//            activityIds.addAll(ids);
//        }
//
//        //获取流程图
//        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
//        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
//        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
//
//
//        InputStream in = diagramGenerator.generateDiagram(bpmnModel,"bmp",
//                activityIds,flows,"宋体","宋体","宋体",null,1.0,false);
//        //OutputStream out = null;
//
//        byte[] buf = new byte[1024];
//
//        int legth = 0;
//        try {
//            ServletOutputStream os = httpServletResponse.getOutputStream();
//
//            while ((legth = in.read(buf)) != -1) {
//               os.write(buf, 0, legth);
//            }
//            if (os != null) {
//                os.close();
//            }
//        } finally {
//            if (in != null) {
//                in.close();
//            }
//
//        }
//    }

//    /**
//     * 删除流程实例
//     * @param processId 流程实例ID
//     * @return
//     */
//    @PostMapping(value = "/delete/process")
//    public BaseResponse<String> deleteProcess(@RequestParam( value = "process_id") String processId){
//        runtimeService.deleteProcessInstance(processId, "");
//
//        return BaseResponse.success("删除流程实例成功");
//    }


//
//    /**
//     * 申领任务
//     * 其实申领的意思就是当在一个用户组中所有有这个权限的用户都可以同时看到这个待办信息，
//     * 这个待办信息可以理解为公布出来的任务，需要有人去领取这个任务，
//     * 那么一旦领取这个任务，其他有这个节点操作权限的用户就不会看到这个待办信息，
//     * 因为已经被这个用户领取了
//     * @param taskId
//     * @param user
//     * @return
//     */
//    @PostMapping(value = "/claim")
//    public BaseResponse<String> claim(@RequestParam( value = "task_id") String taskId,
//                                      @RequestParam( value = "user") String user){
//        taskService.claim(taskId,user);
//        return BaseResponse.success("流程申领");
//    }
//
//    /**
//     * 取消申领任务
//     * 一旦取消申领，那么有这个节点操作权限的用户在待办上又可以看到，
//     * 申领和取消申领是一种锁定机制，使得多个用户在待办操作上不会出现执行同一个当前节点的任务
//     * @param taskId
//     * @return
//     */
//    @PostMapping(value = "/unclaim")
//    public BaseResponse<String> claim(@RequestParam( value = "task_id") String taskId){
//        taskService.unclaim(taskId);
//        return BaseResponse.success("流程申领");
//    }
//
//    /**
//     * 获取流程的历史节点列表
//     * 获取的是这个流程实例走过的节点，
//     * 当然也可以获取到开始节点、网关、线等信息，下面是只过滤了用户任务节点"userTask"的信息
//     * @param processId 流程ID
//     * @return
//     */
//    @PostMapping(value = "/history/list")
//    public BaseResponse<List<HistoricActivityInstance>> historyList(@RequestParam( value = "process_id") String processId){
//        List<HistoricActivityInstance> activities =
//                historyService.createHistoricActivityInstanceQuery()
//                .processInstanceId(processId).activityType("userTask").finished()
//                .orderByHistoricActivityInstanceEndTime().desc().list();
//        return BaseResponse.success(activities);
//    }

//    @PostMapping("isFinished.do/{process_id}")
//    public boolean isFinished(@PathVariable String process_id) {
//        //historyService.
//        return historyService.createHistoricProcessInstanceQuery().finished()
//                .processInstanceId(process_id).count() > 0;
//    }
}