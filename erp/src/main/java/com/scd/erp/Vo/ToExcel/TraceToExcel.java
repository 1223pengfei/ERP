package com.scd.erp.Vo.ToExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

public class TraceToExcel implements Serializable {

    @Excel(name = "反馈时间", databaseFormat = "yyyyMMddHHmmss",
            format = "yyyy-MM-dd", isImportField = "true_st", width = 20)
    private Date creatTime;

    @Excel(name = "部门", orderNum = "1")
    private String department;
    @Excel(name = "提交人", orderNum = "2")
    private String tname;
    @Excel(name = "产品线", orderNum = "3", replace = {"麦克风_1", "后视镜_2", "胎压_3", "方向盘_4"})

    private Integer proType;
    @Excel(name = "主机厂", orderNum = "4")
    private String oem;
    @Excel(name = "产品名", orderNum = "5")
    private String proName;
    @Excel(name = "产品编号", orderNum = "6")
    private String proCode;
    @Excel(name = "产品序列号", orderNum = "7")
    private String proNum;
    @Excel(name = "负责人", orderNum = "8")
    private String fname;
    @Excel(name = "原因分析", orderNum = "11")
    private String question;
    @Excel(name = "解决方案", orderNum = "12")
    private String cause;
    @Excel(name = "备注", orderNum = "15")
    private String remark;

    @Excel(name = "报告状态", replace = {"未完成_0", "已完成_1"}, orderNum = "13")
    private Integer state;

    @Excel(name = "完成时间", databaseFormat = "yyyyMMddHHmmss",
            format = "yyyy-MM-dd", isImportField = "true_st", width = 20, orderNum = "14")
    private Date endTime;

    @Excel(name = "故障点", orderNum = "9")
    private String nail;
    @Excel(name = "故障原因", orderNum = "10")
    private String feater;

    public String getNail() {
        return nail;
    }

    public void setNail(String nail) {
        this.nail = nail;
    }

    public String getFeater() {
        return feater;
    }

    public void setFeater(String feater) {
        this.feater = feater;
    }

    public TraceToExcel(Date creatTime, String department, String tname, Integer proType, String oem, String proName, String proCode, String proNum, String fname, String question, String cause, String remark, Integer state, Date endTime, String nail, String feater) {
        this.creatTime = creatTime;
        this.department = department;
        this.tname = tname;
        this.proType = proType;
        this.oem = oem;
        this.proName = proName;
        this.proCode = proCode;
        this.proNum = proNum;
        this.fname = fname;
        this.question = question;
        this.cause = cause;
        this.remark = remark;
        this.state = state;
        this.endTime = endTime;
        this.nail = nail;
        this.feater = feater;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Integer getProType() {
        return proType;
    }

    public void setProType(Integer proType) {
        this.proType = proType;
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProNum() {
        return proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
