package com.scd.erp.Vo.Department;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Table(name = "erp_department_msg")
public class Department implements Serializable {
    @Id
    @Column(name = "departmentID")
    private Integer departmentid;

    /**
     * 部门名称
     */
    @Column(name = "depName")
    private String depname;

    /**
     * 部门类型
     */
    @Column(name = "depType")
    private String deptype;

    /**
     * 部门编号
     */
    @Column(name = "depCode")
    private String depcode;

    /**
     * 上级部门
     */
    @Column(name = "upDepID")
    private Integer updepid;

    private Department depart;
    private List<Department> departments;
    private List<Job> jobs;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Department{");
        sb.append("departmentid=").append(departmentid);
        sb.append(", depname='").append(depname).append('\'');
        sb.append(", deptype='").append(deptype).append('\'');
        sb.append(", depcode='").append(depcode).append('\'');
        sb.append(", updepid=").append(updepid);
        sb.append(", depart=").append(depart);
        sb.append(", departments=").append(departments);
        sb.append(", jobs=").append(jobs);
        sb.append('}');
        return sb.toString();
    }

    public Department getDepart() {
        return depart;
    }

    public void setDepart(Department depart) {
        this.depart = depart;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    /**
     * @return departmentID
     */
    public Integer getDepartmentid() {
        return departmentid;
    }

    /**
     * @param departmentid
     */
    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * 获取部门名称
     *
     * @return depName - 部门名称
     */
    public String getDepname() {
        return depname;
    }

    /**
     * 设置部门名称
     *
     * @param depname 部门名称
     */
    public void setDepname(String depname) {
        this.depname = depname == null ? null : depname.trim();
    }

    /**
     * 获取部门类型
     *
     * @return depType - 部门类型
     */
    public String getDeptype() {
        return deptype;
    }

    /**
     * 设置部门类型
     *
     * @param deptype 部门类型
     */
    public void setDeptype(String deptype) {
        this.deptype = deptype == null ? null : deptype.trim();
    }

    /**
     * 获取部门编号
     *
     * @return depCode - 部门编号
     */
    public String getDepcode() {
        return depcode;
    }

    /**
     * 设置部门编号
     *
     * @param depcode 部门编号
     */
    public void setDepcode(String depcode) {
        this.depcode = depcode == null ? null : depcode.trim();
    }

    /**
     * 获取上级部门
     *
     * @return upDepID - 上级部门
     */
    public Integer getUpdepid() {
        return updepid;
    }

    /**
     * 设置上级部门
     *
     * @param updepid 上级部门
     */
    public void setUpdepid(Integer updepid) {
        this.updepid = updepid;
    }
}