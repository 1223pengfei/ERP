package com.scd.erp.Vo.Person;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.scd.erp.Vo.Department.Job;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "erp_person_msg")
public class Person implements Serializable {
    @Id
    @Column(name = "personID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer personid;

    /**
     * 姓名
     */
    @Column(name = "personName")
    @Excel(name="姓名")
    private String personname;

    /**
     * 身份证号
     */
    @Column(name = "IDcard")
    @Excel(name="身份证号",orderNum = "1")
    private String idcard;

    /**
     * 企业邮箱
     */
    @Column(name = "fixedMail")
    private String fixedmail;

    private List<Contact> contacts;
    private User user;
    private Job job;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Person{");
        sb.append("personid=").append(personid);
        sb.append(", personname='").append(personname).append('\'');
        sb.append(", idcard='").append(idcard).append('\'');
        sb.append(", fixedmail='").append(fixedmail).append('\'');
        sb.append(", contacts=").append(contacts);
        sb.append(", user=").append(user);
        sb.append(", job=").append(job);
        sb.append(", idcardX=").append(idcardX);
        sb.append('}');
        return sb.toString();
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return personID
     */
    public Integer getPersonid() {
        return personid;
    }

    /**
     * @param personid
     */
    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    /**
     * 获取姓名
     *
     * @return personName - 姓名
     */
    public String getPersonname() {
        return personname;
    }

    /**
     * 设置姓名
     *
     * @param personname 姓名
     */
    public void setPersonname(String personname) {
        this.personname = personname == null ? null : personname.trim();
    }

    /**
     * 获取身份证号
     *
     * @return IDcard - 身份证号
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 设置身份证号
     *
     * @param idcard 身份证号
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();

    }

    /**
     * 获取企业邮箱
     *
     * @return fixedMail - 企业邮箱
     */
    public String getFixedmail() {
        return fixedmail;
    }

    /**
     * 设置企业邮箱
     *
     * @param fixedmail 企业邮箱
     */
    public void setFixedmail(String fixedmail) {
        this.fixedmail = fixedmail == null ? null : fixedmail.trim();
    }


    private PersonX idcardX;

    public PersonX getIdcardX() {
        if (this.idcard != null) {
            return new PersonX(idcard);
        } else {
            return new PersonX();
        }
    }



}