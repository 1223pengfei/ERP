package com.scd.erp.Vo.Process;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "erp_process_annex_recode")
public class AnnexRecode implements Serializable {
    @Id
    @Column(name = "ApplyRecodeID")
    private Integer applyrecodeid;

    @Column(name = "AppllerID")
    private Integer appllerid;

    @Column(name = "ApplyTime")
    private Date applytime;

    private String label;

    private Boolean result;

    @Column(name = "annexID")
    private Integer annexid;

    /**
     * @return ApplyRecodeID
     */
    public Integer getApplyrecodeid() {
        return applyrecodeid;
    }

    /**
     * @param applyrecodeid
     */
    public void setApplyrecodeid(Integer applyrecodeid) {
        this.applyrecodeid = applyrecodeid;
    }

    /**
     * @return AppllerID
     */
    public Integer getAppllerid() {
        return appllerid;
    }

    /**
     * @param appllerid
     */
    public void setAppllerid(Integer appllerid) {
        this.appllerid = appllerid;
    }

    /**
     * @return ApplyTime
     */
    public Date getApplytime() {
        return applytime;
    }

    /**
     * @param applytime
     */
    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    /**
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     */
    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    /**
     * @return result
     */
    public Boolean getResult() {
        return result;
    }

    /**
     * @param result
     */
    public void setResult(Boolean result) {
        this.result = result;
    }

    /**
     * @return annexID
     */
    public Integer getAnnexid() {
        return annexid;
    }

    /**
     * @param annexid
     */
    public void setAnnexid(Integer annexid) {
        this.annexid = annexid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", applyrecodeid=").append(applyrecodeid);
        sb.append(", appllerid=").append(appllerid);
        sb.append(", applytime=").append(applytime);
        sb.append(", label=").append(label);
        sb.append(", result=").append(result);
        sb.append(", annexid=").append(annexid);
        sb.append("]");
        return sb.toString();
    }
}