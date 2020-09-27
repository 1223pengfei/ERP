package com.scd.erp.controller.auth;

import com.scd.erp.Vo.Center.Center_file_collect;
import com.scd.erp.Vo.Center.Center_job_rule;
import com.scd.erp.Vo.Center.Center_person_job;
import com.scd.erp.Vo.Center.Center_rule_persission;
import com.scd.erp.Vo.Department.Job;
import com.scd.erp.Vo.Doc.Belong;
import com.scd.erp.Vo.Doc.Collect;
import com.scd.erp.Vo.Doc.Docement;
import com.scd.erp.Vo.Person.User;
import com.scd.erp.Vo.auth.Rule;
import com.scd.erp.mapper.*;
import com.scd.erp.service.doc.DocService;
import com.scd.erp.utils.extraUtil.ChainMap;
import com.scd.erp.utils.extraUtil.ExampleUtile;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("link")
@Controller
public class linkContro {

    private final static Logger logger = LoggerFactory.getLogger(linkContro.class);

    private final Center_rule_persissionMapper center_rule_persissionMapper;
    private final Center_file_collectMapper center_file_collectMapper;
    private final Center_job_ruleMapper center_job_ruleMapper;
    private final Center_person_jobMapper center_person_jobMapper;
    private final Center_file_packageMapper center_file_packageMapper;


    private final RuleMapper ruleMapper;
    private final JobMapper jobMapper;
    private final CollectMapper collectMapper;
    @Autowired
    private BelongMapper belongMapper;
    @Autowired
    private  DocementMapper docementMapper;

    @Autowired
    public linkContro(Center_rule_persissionMapper center_rule_persissionMapper, Center_file_collectMapper center_file_collectMapper, Center_job_ruleMapper center_job_ruleMapper, Center_person_jobMapper center_person_jobMapper, Center_file_packageMapper center_file_packageMapper, RuleMapper ruleMapper, JobMapper jobMapper, DocService docService, CollectMapper collectMapper){
        this.eu = new ExampleUtile();
        this.center_rule_persissionMapper = center_rule_persissionMapper;
        this.center_file_collectMapper = center_file_collectMapper;
        this.center_job_ruleMapper = center_job_ruleMapper;
        this.center_person_jobMapper = center_person_jobMapper;
        this.center_file_packageMapper = center_file_packageMapper;
        this.ruleMapper = ruleMapper;
        this.jobMapper = jobMapper;
        this.collectMapper = collectMapper;
    }

    private ExampleUtile eu;

    @RequiresPermissions("set:jp")
    @PostMapping("jp.do")
    @ResponseBody
    public String job_person(@RequestParam("jobid") Integer jobid,
                             @RequestParam("personid") String personid) {
        String[] pids = personid.split("_");
        if(pids.length > 0 && !pids[0].equals("1")) {
            Example cex = eu.getExample(Center_person_job.class);
            for (String pid : pids) {
                int pesonid = Integer.parseInt(pid);
                cex.createCriteria().andEqualTo("pesonid", pesonid);
                List<Center_person_job> list = this.center_person_jobMapper.selectByExample(cex);
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        center_person_jobMapper.deleteByPrimaryKey(list.get(i).getPersonJobId());
                    }
                }

                this.center_person_jobMapper.insertSelective(new Center_person_job(pesonid, jobid));
            }
            return "ok";
        }

        logger.error("jobid is:"+jobid);
        logger.error("personid is:"+personid);
        return "no ok";
    }


    @RequiresPermissions("del:jp")
    @PostMapping("disJP.do")
    @ResponseBody
    public String disJP(@RequestParam("personid") Integer personid,
                        @RequestParam("isBelong") boolean belong,
                        HttpServletRequest request) {
        if (null != personid && personid != 1) {
            Job job = this.jobMapper.getJob(personid);
            //System.out.println(job);
            if(null != job) {
                Example expj = eu.getExample(Center_person_job.class, new ChainMap().putObj("jobid", job.getJobid())
                        .putObj("pesonid", personid));
                this.center_person_jobMapper.deleteByExample(expj);

                Example exbl = eu.getExample(Belong.class, new ChainMap().putObj("updatauserid", personid));
                List<Belong> belongs = this.belongMapper.selectByExample(exbl);

                User user = (User) request.getSession().getAttribute("user");
                Integer controid = 2;
                if (null != user) controid = user.getPresonid();
                if (!belong) {
                    for (Belong b : belongs) {
                        b.setIsbelonguser(belong);
                        b.setNowbelonguserid(controid);
                        b.setLasttime(new Date());
                        this.belongMapper.updateByPrimaryKey(b);
                    }
                }
                return "ok";
            }
            logger.error("job is null");
            return "no ok";
        }
        logger.error("personid is:"+personid);
        return "no ok";
    }


    @PostMapping("cf.do")
    @ResponseBody
    public Docement file_collect(Center_file_collect center_file_collect) {
        //System.out.println(center_file_collect);

            this.center_file_collectMapper.insertSelective(center_file_collect);
        Docement docement = this.docementMapper.selectByPrimaryKey(center_file_collect.getFileid());
        Collect c = new Collect();
        c.setCollectid(center_file_collect.getCollectid());
        docement.setCollect(c);
        return docement;


    }


    @PostMapping("disfc.do")
    @ResponseBody
    public Map disfc(@RequestParam("docid") Integer docid,
                     @RequestParam("collectid") Integer collectid) {

            Example ex = eu.getExample(Center_file_collect.class,
                    new ChainMap().putObj("collectid", collectid)
                    .putObj("fileid", docid));
        this.center_file_collectMapper.deleteByExample(ex);
        Docement docement = this.docementMapper.selectByPrimaryKey(docid);
        Collect collect = this.collectMapper.getCollect(collectid);
        return new ChainMap().putObj("doc",docement).putObj("collect",collect);

    }

    @RequiresPermissions("set:jr")
    @PostMapping("jr.do")
    @ResponseBody
    public List<Job> Job_Rule(@RequestParam("jobid") Integer jobid,
                         @RequestParam("ruleid") String ruleids) {
        if (null != jobid && jobid != 1) {
            this.center_job_ruleMapper.deleteByExample(eu.getExample(Center_job_rule.class, new ChainMap().putObj("jobid", jobid)));
            String[] rids = ruleids.split("_");
            for (String rid : rids) {
                if (!rid.equals(""))
                this.center_job_ruleMapper.insertSelective(new Center_job_rule(jobid, Integer.parseInt(rid)));
            }
            return getJobs();
        }
        return null;
    }

    @PostMapping("jobs.do")
    @ResponseBody
    public List<Job> getJobs() {
        return this.jobMapper.allJob();
    }


    @RequiresPermissions("set:rp")
    @PostMapping("rp.do")
    @ResponseBody
    public Map Rule_permission(@RequestParam("ruleid") Integer ruleid,
                                @RequestParam("perid") String perids) {
        if (null != ruleid && ruleid != 1) {
            try {
                String[] pids = perids.split("_");
                Example ex = eu.getExample(Center_rule_persission.class);
                ex.createCriteria().andEqualTo("rulid", ruleid);
                this.center_rule_persissionMapper.deleteByExample(ex);
                for (String pid : pids) {
                    this.center_rule_persissionMapper.insertSelective(new Center_rule_persission(ruleid, Integer.parseInt(pid)));
                }

                return new ChainMap().putObj("msg", "ok").putObj("rules", getValue());
            } catch (Exception e) {
                e.printStackTrace();
                return new ChainMap().putObj("msg", "fail");
            }
        }
        return null;
    }



    @PostMapping("getRules.do")
    @ResponseBody
    public List<Rule> getValue() {
        return this.ruleMapper.allRule();
    }


}
