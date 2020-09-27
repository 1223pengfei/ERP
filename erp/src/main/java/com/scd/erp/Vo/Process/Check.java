package com.scd.erp.Vo.Process;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_process_check")
public class Check implements Serializable {
    @Id
    @Column(name = "checkID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer checkid;

    @Column(name = "processID")
    private Integer processid;

    @Column(name = "checkerID")
    private Integer checkerid;


    public Check(Integer processid, Integer checkerid) {
        this.processid = processid;
        this.checkerid = checkerid;
    }

    public Check() {
    }

    /**
     * @return checkID
     */
    public Integer getCheckid() {
        return checkid;
    }

    /**
     * @param checkid
     */
    public void setCheckid(Integer checkid) {
        this.checkid = checkid;
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
     * @return checkerID
     */
    public Integer getCheckerid() {
        return checkerid;
    }

    /**
     * @param checkerid
     */
    public void setCheckerid(Integer checkerid) {
        this.checkerid = checkerid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", checkid=").append(checkid);
        sb.append(", processid=").append(processid);
        sb.append(", checkerid=").append(checkerid);
        sb.append("]");
        return sb.toString();
    }
}