package com.scd.erp.Vo.plm.Material;

import com.scd.erp.Vo.Person.User;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "erp_plm_materail_bom")
public class Bom implements Serializable {
    @Id
    @Column(name = "BomID")
    private Integer bomid;

    @Column(name = "baseID")
    private Integer baseid;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 位号
     */
    @Column(name = "Tag")
    private String tag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 单位
     */
    private Integer unit;

    @Column(name = "creatTime")
    private Date creattime;

    @Column(name = "creatUserID")
    private Integer creatuserid;

    private BaseMaterial baseMaterial;
    private User creatUser;

    public User getCreatUser() {
        return creatUser;
    }

    public void setCreatUser(User creatUser) {
        this.creatUser = creatUser;
    }

    public BaseMaterial   getBaseMaterial() {

        return baseMaterial;
    }

    public void setBaseMaterial(BaseMaterial baseMaterial) {
        this.baseMaterial = baseMaterial;
    }

    /**
     * @return BomID
     */
    public Integer getBomid() {
        return bomid;
    }

    /**
     * @param bomid
     */
    public void setBomid(Integer bomid) {
        this.bomid = bomid;
    }

    /**
     * @return baseID
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

    /**
     * 获取数量
     *
     * @return amount - 数量
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置数量
     *
     * @param amount 数量
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取位号
     *
     * @return Tag - 位号
     */
    public String getTag() {
        return tag;
    }

    /**
     * 设置位号
     *
     * @param tag 位号
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取单位
     *
     * @return unit - 单位
     */
    public Integer getUnit() {
        return unit;
    }

    /**
     * 设置单位
     *
     * @param unit 单位
     */
    public void setUnit(Integer unit) {
        this.unit = unit;
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
     * @return creatUserID
     */
    public Integer getCreatuserid() {
        return creatuserid;
    }

    /**
     * @param creatuserid
     */
    public void setCreatuserid(Integer creatuserid) {
        this.creatuserid = creatuserid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bomid=").append(bomid);
        sb.append(", baseid=").append(baseid);
        sb.append(", amount=").append(amount);
        sb.append(", tag=").append(tag);
        sb.append(", remark=").append(remark);
        sb.append(", unit=").append(unit);
        sb.append(", creattime=").append(creattime);
        sb.append(", creatuserid=").append(creatuserid);
        sb.append("]");
        return sb.toString();
    }
}