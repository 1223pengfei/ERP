package com.scd.erp.Vo.ToExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

public class PersonToExcel implements Serializable {
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "性别", orderNum = "3", replace = {"女_F", "男_M", "其他_N"})
    private String sex;
    @Excel(name = "年龄", orderNum = "4")
    private String age;
    @Excel(name = "户籍省份", orderNum = "5")
    private String adr;
    @Excel(name = "身份证号", orderNum = "6")
    private String idcard;
    @Excel(name = "公司邮箱", orderNum = "7")
    private String fixMail;
    @Excel(name = "全部联系方式", orderNum = "8")
    private String cantact;
    @Excel(name = "部门", orderNum = "1")
    private String depart;
    @Excel(name = "职位", orderNum = "2")
    private String job;

    public PersonToExcel() {
    }

    public PersonToExcel(String name, String sex, String age, String adr, String idcard, String fixMail, String cantact) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.adr = adr;
        this.idcard = idcard;
        this.fixMail = fixMail;
        this.cantact = cantact;
    }

    public PersonToExcel(String name, String sex, String age, String adr, String idcard, String fixMail, String cantact, String depart, String job) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.adr = adr;
        this.idcard = idcard;
        this.fixMail = fixMail;
        this.cantact = cantact;
        this.depart = depart;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getFixMail() {
        return fixMail;
    }

    public void setFixMail(String fixMail) {
        this.fixMail = fixMail;
    }

    public String getCantact() {
        return cantact;
    }

    public void setCantact(String cantact) {
        this.cantact = cantact;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
