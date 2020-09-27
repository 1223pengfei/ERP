package com.scd.erp.Vo.Process;

import com.scd.erp.Vo.Person.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "erp_process_msg")
public class Process implements Serializable {
    @Id
    @Column(name = "processID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer processid;

    @Column(name = "applyUserID")
    private Integer applyuserid;

    @Column(name = "processName")
    private String processname;

    private Person appluUser;
    private List<Person> checkUsers;

    public List<Person> getCheckUsers() {
        return checkUsers;
    }

    public void setCheckUsers(List<Person> checkUsers) {
        this.checkUsers = checkUsers;
    }

    public Person getAppluUser() {
        return appluUser;
    }

    public void setAppluUser(Person appluUser) {
        this.appluUser = appluUser;
    }

    /**
     * @return processID
     */
    public Integer getProcessid() {
        return processid;
    }

    /**
     * @param processid
     */
    public void setProcessid(Integer processid) {
        this.processid = processid;
    }

    /**
     * @return applyUserID
     */
    public Integer getApplyuserid() {
        return applyuserid;
    }

    /**
     * @param applyuserid
     */
    public void setApplyuserid(Integer applyuserid) {
        this.applyuserid = applyuserid;
    }

    /**
     * @return processName
     */
    public String getProcessname() {
        return processname;
    }

    /**
     * @param processname
     */
    public void setProcessname(String processname) {
        this.processname = processname == null ? null : processname.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", processid=").append(processid);
        sb.append(", applyuserid=").append(applyuserid);
        sb.append(", processname=").append(processname);
        sb.append("]");
        return sb.toString();
    }
}