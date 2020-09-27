package com.scd.erp.Vo.Center;

import javax.persistence.*;

@Table(name = "erp_center_person_job")
public class Center_person_job {
    @Id
    @Column(name = "person_Job_ID")
    private Integer personJobId;

    @Column(name = "pesonID")
    private Integer pesonid;

    @Column(name = "jobID")
    private Integer jobid;

    public Center_person_job() {
    }

    public Center_person_job(Integer pesonid, Integer jobid) {
        this.pesonid = pesonid;
        this.jobid = jobid;
    }

    /**
     * @return person_Job_ID
     */
    public Integer getPersonJobId() {
        return personJobId;
    }

    /**
     * @param personJobId
     */
    public void setPersonJobId(Integer personJobId) {
        this.personJobId = personJobId;
    }

    /**
     * @return pesonID
     */
    public Integer getPesonid() {
        return pesonid;
    }

    /**
     * @param pesonid
     */
    public void setPesonid(Integer pesonid) {
        this.pesonid = pesonid;
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
}