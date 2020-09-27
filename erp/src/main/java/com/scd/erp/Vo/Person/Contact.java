package com.scd.erp.Vo.Person;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_person_contact")
public class Contact implements Serializable {
    @Id
    @Column(name = "contactID")
    private Integer contactid;

    /**
     * 联系方式类型
     * 1手机2座机3微信4钉钉5QQ6个人邮箱0其他
     */
    @Column(name = "contactType")
    private String contacttype;

    /**
     * 联系方式号码
     */
    @Column(name = "contactNumber")
    private String contactnumber;

    /**
     * 备注
     */
    @Column(name = "contactRemark")
    private String contactremark;

    /**
     * 人员ID
     */
    @Column(name = "personID")
    private Integer personid;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Contact{");
        sb.append("contactid=").append(contactid);
        sb.append(", contacttype='").append(contacttype).append('\'');
        sb.append(", contactnumber='").append(contactnumber).append('\'');
        sb.append(", contactremark='").append(contactremark).append('\'');
        sb.append(", personid=").append(personid);
        sb.append('}');
        return sb.toString();
    }

    /**
     * @return contactID
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * @param contactid
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

    /**
     * 获取联系方式类型
     * 1手机2座机3微信4钉钉5QQ6个人邮箱0其他
     *
     * @return contactType - 联系方式类型
     * 1手机2座机3微信4钉钉5QQ6个人邮箱0其他
     */
    public String getContacttype() {
        return contacttype;
    }

    /**
     * 设置联系方式类型
     * 1手机2座机3微信4钉钉5QQ6个人邮箱0其他
     *
     * @param contacttype 联系方式类型
     *                    1手机2座机3微信4钉钉5QQ6个人邮箱0其他
     */
    public void setContacttype(String contacttype) {
        this.contacttype = contacttype == null ? null : contacttype.trim();
    }

    /**
     * 获取联系方式号码
     *
     * @return contactNumber - 联系方式号码
     */
    public String getContactnumber() {
        return contactnumber;
    }

    /**
     * 设置联系方式号码
     *
     * @param contactnumber 联系方式号码
     */
    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber == null ? null : contactnumber.trim();
    }

    /**
     * 获取备注
     *
     * @return contactRemark - 备注
     */
    public String getContactremark() {
        return contactremark;
    }

    /**
     * 设置备注
     *
     * @param contactremark 备注
     */
    public void setContactremark(String contactremark) {
        this.contactremark = contactremark == null ? null : contactremark.trim();
    }

    /**
     * 获取人员ID
     *
     * @return personID - 人员ID
     */
    public Integer getPersonid() {
        return personid;
    }

    /**
     * 设置人员ID
     *
     * @param personid 人员ID
     */
    public void setPersonid(Integer personid) {
        this.personid = personid;
    }
}