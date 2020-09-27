package com.scd.erp.Vo.Doc;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scd.erp.Vo.Department.Department;
import com.scd.erp.Vo.Person.Person;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "erp_file_belong")
public class   Belong {
    @Id
    @Column(name = "belongID")
    private Integer belongid;

    /**
     * 资料ID
     */
    @Column(name = "fileID")
    private Integer fileid;

    /**
     * 上传者ID
     */
    @Column(name = "updataUserID")
    private Integer updatauserid;
    private Person upPerson;

    /**
     * 部门ID
     */
    @Column(name = "departmentID")
    private Integer departmentid;
    private Department dep;
    /**
     * 是否对上传者可见
     */
    @Column(name = "isbelongUser")
    private Boolean isbelonguser;

    /**
     * 当前归属人
     */
    @Column(name = "nowBelongUserID")
    private Integer nowbelonguserid;
    private Person nowPerson;
    /**
     * 最后变更时间
     */
    @Column(name = "lastTime")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date lasttime;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Belong{");
        sb.append("belongid=").append(belongid);
        sb.append(", fileid=").append(fileid);
        sb.append(", updatauserid=").append(updatauserid);
        sb.append(", upPerson=").append(upPerson);
        sb.append(", departmentid=").append(departmentid);
        sb.append(", dep=").append(dep);
        sb.append(", isbelonguser=").append(isbelonguser);
        sb.append(", nowbelonguserid=").append(nowbelonguserid);
        sb.append(", nowPerson=").append(nowPerson);
        sb.append(", lasttime=").append(lasttime);
        sb.append('}');
        return sb.toString();
    }

    public Person getUpPerson() {
        return upPerson;
    }

    public void setUpPerson(Person upPerson) {
        this.upPerson = upPerson;
    }

    public Department getDep() {
        return dep;
    }

    public void setDep(Department dep) {
        this.dep = dep;
    }

    public Person getNowPerson() {
        return nowPerson;
    }

    public void setNowPerson(Person nowPerson) {
        this.nowPerson = nowPerson;
    }

    /**
     * @return belongID
     */
    public Integer getBelongid() {
        return belongid;
    }

    /**
     * @param belongid
     */
    public void setBelongid(Integer belongid) {
        this.belongid = belongid;
    }

    /**
     * 获取资料ID
     *
     * @return fileID - 资料ID
     */
    public Integer getFileid() {
        return fileid;
    }

    /**
     * 设置资料ID
     *
     * @param fileid 资料ID
     */
    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    /**
     * 获取上传者ID
     *
     * @return updataUserID - 上传者ID
     */
    public Integer getUpdatauserid() {
        return updatauserid;
    }

    /**
     * 设置上传者ID
     *
     * @param updatauserid 上传者ID
     */
    public void setUpdatauserid(Integer updatauserid) {
        this.updatauserid = updatauserid;
    }

    /**
     * 获取部门ID
     *
     * @return departmentID - 部门ID
     */
    public Integer getDepartmentid() {
        return departmentid;
    }

    /**
     * 设置部门ID
     *
     * @param departmentid 部门ID
     */
    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * 获取是否对上传者可见
     *
     * @return isbelongUser - 是否对上传者可见
     */
    public Boolean getIsbelonguser() {
        return isbelonguser;
    }

    /**
     * 设置是否对上传者可见
     *
     * @param isbelonguser 是否对上传者可见
     */
    public void setIsbelonguser(Boolean isbelonguser) {
        this.isbelonguser = isbelonguser;
    }

    /**
     * 获取当前归属人
     *
     * @return nowBelongUserID - 当前归属人
     */
    public Integer getNowbelonguserid() {
        return nowbelonguserid;
    }

    /**
     * 设置当前归属人
     *
     * @param nowbelonguserid 当前归属人
     */
    public void setNowbelonguserid(Integer nowbelonguserid) {
        this.nowbelonguserid = nowbelonguserid;
    }

    /**
     * 获取最后变更时间
     *
     * @return lastTime - 最后变更时间
     */
    public Date getLasttime() {
        return lasttime;
    }

    /**
     * 设置最后变更时间
     *
     * @param lasttime 最后变更时间
     */
    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }
}