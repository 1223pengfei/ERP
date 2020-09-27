package com.scd.erp.Vo.Center;

import javax.persistence.*;

@Table(name = "erp_center_job_rule")
public class Center_job_rule {
    @Id
    @Column(name = "job_rule_ID")
    private Integer jobRuleId;

    @Column(name = "jobID")
    private Integer jobid;

    @Column(name = "ruleID")
    private Integer ruleid;

    public Center_job_rule() {
    }

    public Center_job_rule(Integer jobid, Integer ruleid) {
        this.jobid = jobid;
        this.ruleid = ruleid;
    }

    /**
     * @return job_rule_ID
     */
    public Integer getJobRuleId() {
        return jobRuleId;
    }

    /**
     * @param jobRuleId
     */
    public void setJobRuleId(Integer jobRuleId) {
        this.jobRuleId = jobRuleId;
    }

    /**
     * @return jobID
     */
    public Integer getJobid() {
        return jobid;
    }

    /**
     * @param jobid
     */
    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    /**
     * @return ruleID
     */
    public Integer getRuleid() {
        return ruleid;
    }

    /**
     * @param ruleid
     */
    public void setRuleid(Integer ruleid) {
        this.ruleid = ruleid;
    }
}