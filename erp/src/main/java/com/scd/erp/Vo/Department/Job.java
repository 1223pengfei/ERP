package com.scd.erp.Vo.Department;

import com.scd.erp.Vo.Person.Person;
import com.scd.erp.Vo.auth.Rule;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Table(name = "erp_department_job")
public class Job implements Serializable {
    @Id
    @Column(name = "jobID")
    private Integer jobid;

    /**
     * 职位编号
     */
    @Column(name = "jobCode")
    private String jobcode;

    /**
     * 职位名称
     */
    @Column(name = "jobName")
    private String jobname;

    /**
     * 上级职位ID
     */
    @Column(name = "upJobID")
    private Integer upjobid;

    /**
     * 所属部门ID
     */
    @Column(name = "depID")
    private Integer depid;

    private List<Person> people;
    private Department department;
    private List<Rule> rules;

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Job{");
        sb.append("jobid=").append(jobid);
        sb.append(", jobcode='").append(jobcode).append('\'');
        sb.append(", jobname='").append(jobname).append('\'');
        sb.append(", upjobid=").append(upjobid);
        sb.append(", depid=").append(depid);
        sb.append(", people=").append(people);
        sb.append(", department=").append(department);
        sb.append(", rules=").append(rules);
        sb.append('}');
        return sb.toString();
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
     * 获取职位编号
     *
     * @return jobCode - 职位编号
     */
    public String getJobcode() {
        return jobcode;
    }

    /**
     * 设置职位编号
     *
     * @param jobcode 职位编号
     */
    public void setJobcode(String jobcode) {
        this.jobcode = jobcode == null ? null : jobcode.trim();
    }

    /**
     * 获取职位名称
     *
     * @return jobName - 职位名称
     */
    public String getJobname() {
        return jobname;
    }

    /**
     * 设置职位名称
     *
     * @param jobname 职位名称
     */
    public void setJobname(String jobname) {
        this.jobname = jobname == null ? null : jobname.trim();
    }

    /**
     * 获取上级职位ID
     *
     * @return upJobID - 上级职位ID
     */
    public Integer getUpjobid() {
        return upjobid;
    }

    /**
     * 设置上级职位ID
     *
     * @param upjobid 上级职位ID
     */
    public void setUpjobid(Integer upjobid) {
        this.upjobid = upjobid;
    }

    /**
     * 获取所属部门ID
     *
     * @return depID - 所属部门ID
     */
    public Integer getDepid() {
        return depid;
    }

    /**
     * 设置所属部门ID
     *
     * @param depid 所属部门ID
     */
    public void setDepid(Integer depid) {
        this.depid = depid;
    }
}