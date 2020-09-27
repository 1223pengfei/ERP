package com.scd.erp.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scd.erp.Vo.Person.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Project implements Serializable {
    private Integer pid;

    private String pname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date starttime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endtime;

    private String pbudget;

    private String description;

    private Double speed;

    private Integer state;

    private Integer uid;

    private List<User> users;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Project{");
        sb.append("pid=").append(pid);
        sb.append(", pname='").append(pname).append('\'');
        sb.append(", starttime=").append(starttime);
        sb.append(", endtime=").append(endtime);
        sb.append(", pbudget='").append(pbudget).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", speed=").append(speed);
        sb.append(", state=").append(state);
        sb.append(", uid=").append(uid);
        sb.append(", users=").append(users);
        sb.append('}');
        return sb.toString();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getPbudget() {
        return pbudget;
    }

    public void setPbudget(String pbudget) {
        this.pbudget = pbudget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }
}
