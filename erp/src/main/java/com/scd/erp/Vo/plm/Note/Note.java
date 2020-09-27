package com.scd.erp.Vo.plm.Note;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "erp_plm_note_msg")
public class Note implements Serializable {
    @Id
    @Column(name = "noteID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noteid;

    /**
     * 创建人ID
     */
    @Column(name = "creatUserID")
    private Integer creatuserid;

    /**
     * 创建时间
     */
    @Column(name = "creatTime")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date creattime;

    /**
     * 状态
     */
    private String state;

    private List<Renew> renews;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Note{");
        sb.append("noteid=").append(noteid);
        sb.append(", creatuserid=").append(creatuserid);
        sb.append(", creattime=").append(creattime);
        sb.append(", state='").append(state).append('\'');
        sb.append(", renews=").append(renews);
        sb.append(", baseid=").append(baseid);
        sb.append('}');
        return sb.toString();
    }

    public List<Renew> getRenews() {
        return renews;
    }

    public void setRenews(List<Renew> renews) {
        this.renews = renews;
    }

    @Column(name = "BaseID")
    private Integer baseid;

    /**
     * @return noteID
     */
    public Integer getNoteid() {
        return noteid;
    }

    /**
     * @param noteid
     */
    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    /**
     * 获取创建人ID
     *
     * @return creatUserID - 创建人ID
     */
    public Integer getCreatuserid() {
        return creatuserid;
    }

    /**
     * 设置创建人ID
     *
     * @param creatuserid 创建人ID
     */
    public void setCreatuserid(Integer creatuserid) {
        this.creatuserid = creatuserid;
    }

    /**
     * 获取创建时间
     *
     * @return creatTime - 创建时间
     */
    public Date getCreattime() {
        return creattime;
    }

    /**
     * 设置创建时间
     *
     * @param creattime 创建时间
     */
    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    /**
     * 获取状态
     *
     * @return state - 状态
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * @return BaseID
     */
    public Integer getBaseid() {
        return baseid;
    }

    /**
     * @param baseid
     */
    public void setBaseid(Integer baseid) {
        this.baseid = baseid;
    }
}