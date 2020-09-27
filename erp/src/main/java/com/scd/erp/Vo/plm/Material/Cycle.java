package com.scd.erp.Vo.plm.Material;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_plm_material_cycle")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cycle implements Serializable {
    @Id
    @Column(name = "cycleID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cycleid;

    /**
     * 保质期
     */
    @Column(name = "ExpireDuration")
    private Integer expireduration;

    /**
     * 使用寿命
     */
    @Column(name = "LifeDuration")
    private Integer lifeduration;

    /**
     * 剩余寿命
     */
    @Column(name = "LifeRemaining")
    private Integer liferemaining;

    /**
     * 计价单位
     */
    @Column(name = "UnitofAccount")
    private String unitofaccount;

    @Column(name = "baseID")
    private Integer baseid;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("cycleid", cycleid)
                .append("expireduration", expireduration)
                .append("lifeduration", lifeduration)
                .append("liferemaining", liferemaining)
                .append("unitofaccount", unitofaccount)
                .append("baseid", baseid)
                .toString();
    }

    /**
     * @return cycleID
     */
    public Integer getCycleid() {
        return cycleid;
    }

    /**
     * @param cycleid
     */
    public void setCycleid(Integer cycleid) {
        this.cycleid = cycleid;
    }

    /**
     * 获取保质期
     *
     * @return ExpireDuration - 保质期
     */
    public Integer getExpireduration() {
        return expireduration;
    }

    /**
     * 设置保质期
     *
     * @param expireduration 保质期
     */
    public void setExpireduration(Integer expireduration) {
        this.expireduration = expireduration;
    }

    /**
     * 获取使用寿命
     *
     * @return LifeDuration - 使用寿命
     */
    public Integer getLifeduration() {
        return lifeduration;
    }

    /**
     * 设置使用寿命
     *
     * @param lifeduration 使用寿命
     */
    public void setLifeduration(Integer lifeduration) {
        this.lifeduration = lifeduration;
    }

    /**
     * 获取剩余寿命
     *
     * @return LifeRemaining - 剩余寿命
     */
    public Integer getLiferemaining() {
        return liferemaining;
    }

    /**
     * 设置剩余寿命
     *
     * @param liferemaining 剩余寿命
     */
    public void setLiferemaining(Integer liferemaining) {
        this.liferemaining = liferemaining;
    }

    /**
     * 获取计价单位
     *
     * @return UnitofAccount - 计价单位
     */
    public String getUnitofaccount() {
        return unitofaccount;
    }

    /**
     * 设置计价单位
     *
     * @param unitofaccount 计价单位
     */
    public void setUnitofaccount(String unitofaccount) {
        this.unitofaccount = unitofaccount == null ? null : unitofaccount.trim();
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
}