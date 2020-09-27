package com.scd.erp.Vo.Center;

import javax.persistence.*;

@Table(name = "erp_center_rule_persission")
public class Center_rule_persission {
    @Id
    @Column(name = "rule_persission_id")
    private Integer rulePersissionId;

    @Column(name = "rulID")
    private Integer rulid;

    @Column(name = "persID")
    private Integer persid;

    public Center_rule_persission() {
    }

    public Center_rule_persission(Integer rulid, Integer persid) {
        this.rulid = rulid;
        this.persid = persid;
    }

    /**
     * @return rule_persission_id
     */
    public Integer getRulePersissionId() {
        return rulePersissionId;
    }

    /**
     * @param rulePersissionId
     */
    public void setRulePersissionId(Integer rulePersissionId) {
        this.rulePersissionId = rulePersissionId;
    }

    /**
     * @return rulID
     */
    public Integer getRulid() {
        return rulid;
    }

    /**
     * @param rulid
     */
    public void setRulid(Integer rulid) {
        this.rulid = rulid;
    }

    /**
     * @return persID
     */
    public Integer getPersid() {
        return persid;
    }

    /**
     * @param persid
     */
    public void setPersid(Integer persid) {
        this.persid = persid;
    }
}