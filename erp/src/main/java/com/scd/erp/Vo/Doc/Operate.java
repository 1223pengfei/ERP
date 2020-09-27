package com.scd.erp.Vo.Doc;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "erp_file_operate")
public class Operate {
    @Id
    @Column(name = "operateID")
    private Integer operateid;

    /**
     * 资料ID
     */
    @Column(name = "fileID")
    private Integer fileid;

    /**
     * 操作人员ID
     */
    @Column(name = "userID")
    private Integer userid;

    /**
     * 操作类型 1新增 2修改 3删除
     */
    @Column(name = "operaType")
    private String operatype;

    /**
     * 操作时间
     */
    @Column(name = "operaTime")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date operatime;

    /**
     * 备注
     */
    @Column(name = "operaRemark")
    private String operaremark;

    /**
     * @return operateID
     */
    public Integer getOperateid() {
        return operateid;
    }

    /**
     * @param operateid
     */
    public void setOperateid(Integer operateid) {
        this.operateid = operateid;
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
     * 获取操作人员ID
     *
     * @return userID - 操作人员ID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置操作人员ID
     *
     * @param userid 操作人员ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取操作类型 1新增 2修改 3删除
     *
     * @return operaType - 操作类型 1新增 2修改 3删除
     */
    public String getOperatype() {
        return operatype;
    }

    /**
     * 设置操作类型 1新增 2修改 3删除
     *
     * @param operatype 操作类型 1新增 2修改 3删除
     */
    public void setOperatype(String operatype) {
        this.operatype = operatype == null ? null : operatype.trim();
    }

    /**
     * 获取操作时间
     *
     * @return operaTime - 操作时间
     */
    public Date getOperatime() {
        return operatime;
    }

    /**
     * 设置操作时间
     *
     * @param operatime 操作时间
     */
    public void setOperatime(Date operatime) {
        this.operatime = operatime;
    }

    /**
     * 获取备注
     *
     * @return operaRemark - 备注
     */
    public String getOperaremark() {
        return operaremark;
    }

    /**
     * 设置备注
     *
     * @param operaremark 备注
     */
    public void setOperaremark(String operaremark) {
        this.operaremark = operaremark == null ? null : operaremark.trim();
    }
}