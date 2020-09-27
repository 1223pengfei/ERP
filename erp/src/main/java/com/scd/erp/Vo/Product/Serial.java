package com.scd.erp.Vo.Product;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_trace_product_serial")
public class Serial implements Serializable {
    @Id
    @Column(name = "serialID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serialid;

    @Column(name = "serialNumber")
    private String serialnumber;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Serial{");
        sb.append("serialid=").append(serialid);
        sb.append(", serialnumber='").append(serialnumber).append('\'');
        sb.append(", remarkid=").append(remarkid);
        sb.append(", remark=").append(remark);
        sb.append('}');
        return sb.toString();
    }

    public Remark getRemark() {
        return remark;
    }

    public void setRemark(Remark remark) {
        this.remark = remark;
    }

    @Column(name = "remarkID")
    private Integer remarkid;

    private Remark remark;


    /**
     * @return serialID
     */
    public Integer getSerialid() {
        return serialid;
    }

    /**
     * @param serialid
     */
    public void setSerialid(Integer serialid) {
        this.serialid = serialid;
    }

    /**
     * @return serialNumber
     */
    public String getSerialnumber() {
        return serialnumber;
    }

    /**
     * @param serialnumber
     */
    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber == null ? null : serialnumber.trim();
    }

    /**
     * @return remarkID
     */
    public Integer getRemarkid() {
        return remarkid;
    }

    /**
     * @param remarkid
     */
    public void setRemarkid(Integer remarkid) {
        this.remarkid = remarkid;
    }
}