package com.scd.erp.Vo.Alarm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scd.erp.Vo.Materail.Materail;
import com.scd.erp.Vo.Nail.Nail;
import com.scd.erp.Vo.Person.User;
import com.scd.erp.Vo.Product.Serial;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "erp_trace_alarm_msg")
public class Alarm implements Serializable {
    @Id
    @Column(name = "alarmID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer alarmid;

    @Column(name = "FuserID")
    private Integer fuserid;
    private User fuser;
    @Column(name = "TuserID")
    private Integer tuserid;
    private User tuser;
    @Column(name = "serailID")
    private Integer serailid;
    private Serial serial;

    @Column(name = "questionID")
    private Integer questionid;
    private Question quest;

    @Column(name = "creatTime")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date creattime;

    @Column(name = "alarmState")
    private Integer alarmstate;

    @Column(name = "oemID")
    private Integer oemid;
    private OEM oem;

    private String remark;

    @Column(name = "ENDTime")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date endtime;


    private List<Materail> materails;
    private List<Nail> nails;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Alarm{");
        sb.append("alarmid=").append(alarmid);
        sb.append(", fuserid=").append(fuserid);
        sb.append(", fuser=").append(fuser);
        sb.append(", tuserid=").append(tuserid);
        sb.append(", tuser=").append(tuser);
        sb.append(", serailid=").append(serailid);
        sb.append(", serial=").append(serial);
        sb.append(", questionid=").append(questionid);
        sb.append(", quest=").append(quest);
        sb.append(", creattime=").append(creattime);
        sb.append(", alarmstate=").append(alarmstate);
        sb.append(", oemid=").append(oemid);
        sb.append(", oem=").append(oem);
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", endtime=").append(endtime);
        sb.append(", materails=").append(materails);
        sb.append(", nails=").append(nails);
        sb.append('}');
        return sb.toString();
    }


    public List<Nail> getNails() {
        return nails;
    }

    public void setNails(List<Nail> nails) {
        this.nails = nails;
    }

    public Integer getFuserid() {
        return fuserid;
    }

    public void setFuserid(Integer fuserid) {
        this.fuserid = fuserid;
    }

    public User getFuser() {
        return fuser;
    }

    public void setFuser(User fuser) {
        this.fuser = fuser;
    }

    public Integer getTuserid() {
        return tuserid;
    }

    public void setTuserid(Integer tuserid) {
        this.tuserid = tuserid;
    }

    public User getTuser() {
        return tuser;
    }

    public void setTuser(User tuser) {
        this.tuser = tuser;
    }

    public Serial getSerial() {
        return serial;
    }

    public void setSerial(Serial serial) {
        this.serial = serial;
    }

    public Question getQuest() {
        return quest;
    }

    public void setQuest(Question quest) {
        this.quest = quest;
    }

    public OEM getOem() {
        return oem;
    }

    public void setOem(OEM oem) {
        this.oem = oem;
    }

    public List<Materail> getMaterails() {
        return materails;
    }

    public void setMaterails(List<Materail> materails) {
        this.materails = materails;
    }

    /**
     * @return alarmID
     */
    public Integer getAlarmid() {
        return alarmid;
    }

    /**
     * @param alarmid
     */
    public void setAlarmid(Integer alarmid) {
        this.alarmid = alarmid;
    }


    /**
     * @return serailID
     */
    public Integer getSerailid() {
        return serailid;
    }

    /**
     * @param serailid
     */
    public void setSerailid(Integer serailid) {
        this.serailid = serailid;
    }

    /**
     * @return questionID
     */
    public Integer getQuestionid() {
        return questionid;
    }

    /**
     * @param questionid
     */
    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    /**
     * @return creatTime
     */
    public Date getCreattime() {
        return creattime;
    }

    /**
     * @param creattime
     */
    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    /**
     * @return alarmState
     */
    public Integer getAlarmstate() {
        return alarmstate;
    }

    /**
     * @param alarmstate
     */
    public void setAlarmstate(Integer alarmstate) {
        this.alarmstate = alarmstate;
    }

    /**
     * @return oemID
     */
    public Integer getOemid() {
        return oemid;
    }

    /**
     * @param oemid
     */
    public void setOemid(Integer oemid) {
        this.oemid = oemid;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return ENDTime
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * @param endtime
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}