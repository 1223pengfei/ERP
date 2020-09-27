package com.scd.erp.Vo.plm.Note;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "erp_plm_note_renew")
public class Renew implements Serializable {
    @Id
    @Column(name = "renewID")
    private Integer renewid;

    /**
     * 更新人ID
     */
    @Column(name = "renewUserID")
    private Integer renewuserid;

    /**
     * 更新时间
     */
    @Column(name = "renewTime")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date renewtime;

    /**
     * 迭代号
     */
    @Column(name = "iterationCode")
    private String iterationcode;

    /**
     * 迭代版本号
     */
    @Column(name = "iterationVersion")
    private String iterationversion;

    @Column(name = "noteID")
    private Integer noteid;

    /**
     * 更新类别 基本 设计 工艺环保 物流 采购 周期
     */
    @Column(name = "renewType")
    private String renewtype;

    /**
     * 属性ID
     */
    @Column(name = "attrituteID")
    private Integer attrituteid;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("renewid", renewid)
                .append("renewuserid", renewuserid)
                .append("renewtime", renewtime)
                .append("iterationcode", iterationcode)
                .append("iterationversion", iterationversion)
                .append("noteid", noteid)
                .append("renewtype", renewtype)
                .append("attrituteid", attrituteid)
                .toString();
    }

    /**
     * @return renewID
     */
    public Integer getRenewid() {
        return renewid;
    }

    /**
     * @param renewid
     */
    public void setRenewid(Integer renewid) {
        this.renewid = renewid;
    }

    /**
     * 获取更新人ID
     *
     * @return renewUserID - 更新人ID
     */
    public Integer getRenewuserid() {
        return renewuserid;
    }

    /**
     * 设置更新人ID
     *
     * @param renewuserid 更新人ID
     */
    public void setRenewuserid(Integer renewuserid) {
        this.renewuserid = renewuserid;
    }

    /**
     * 获取更新时间
     *
     * @return renewTime - 更新时间
     */
    public Date getRenewtime() {
        return renewtime;
    }

    /**
     * 设置更新时间
     *
     * @param renewtime 更新时间
     */
    public void setRenewtime(Date renewtime) {
        this.renewtime = renewtime;
    }

    /**
     * 获取迭代号
     *
     * @return iterationCode - 迭代号
     */
    public String getIterationcode() {
        return iterationcode;
    }

    /**
     * 设置迭代号
     *
     * @param iterationcode 迭代号
     */
    public void setIterationcode(String iterationcode) {
        this.iterationcode = iterationcode == null ? null : iterationcode.trim();
    }

    /**
     * 获取迭代版本号
     *
     * @return iterationVersion - 迭代版本号
     */
    public String getIterationversion() {
        return iterationversion;
    }

    /**
     * 设置迭代版本号
     *
     * @param iterationversion 迭代版本号
     */
    public void setIterationversion(String iterationversion) {
        this.iterationversion = iterationversion == null ? null : iterationversion.trim();
    }

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
     * 获取更新类别 基本 设计 工艺环保 物流 采购 周期
     *
     * @return renewType - 更新类别 基本 设计 工艺环保 物流 采购 周期
     */
    public String getRenewtype() {
        return renewtype;
    }

    /**
     * 设置更新类别 基本 设计 工艺环保 物流 采购 周期
     *
     * @param renewtype 更新类别 基本 设计 工艺环保 物流 采购 周期
     */
    public void setRenewtype(String renewtype) {
        this.renewtype = renewtype == null ? null : renewtype.trim();
    }

    /**
     * 获取属性ID
     *
     * @return attrituteID - 属性ID
     */
    public Integer getAttrituteid() {
        return attrituteid;
    }

    /**
     * 设置属性ID
     *
     * @param attrituteid 属性ID
     */
    public void setAttrituteid(Integer attrituteid) {
        this.attrituteid = attrituteid;
    }
}