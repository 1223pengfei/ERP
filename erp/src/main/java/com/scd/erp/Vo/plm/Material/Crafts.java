package com.scd.erp.Vo.plm.Material;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_plm_material_crafts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Crafts implements Serializable {
    @Id
    @Column(name = "craftsID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer craftsid;

    /**
     * 耐回流焊次数
     */
    @Column(name = "ReflowCycles")
    private Integer reflowcycles;

    /**
     * 环保标准
     */
    @Column(name = "Environment")
    private String environment;

    @Column(name = "baseID")
    private Integer baseid;


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("craftsid", craftsid)
                .append("reflowcycles", reflowcycles)
                .append("environment", environment)
                .append("baseid", baseid)
                .toString();
    }

    /**
     * @return craftsID
     */
    public Integer getCraftsid() {
        return craftsid;
    }

    /**
     * @param craftsid
     */
    public void setCraftsid(Integer craftsid) {
        this.craftsid = craftsid;
    }

    /**
     * 获取耐回流焊次数
     *
     * @return ReflowCycles - 耐回流焊次数
     */
    public Integer getReflowcycles() {
        return reflowcycles;
    }

    /**
     * 设置耐回流焊次数
     *
     * @param reflowcycles 耐回流焊次数
     */
    public void setReflowcycles(Integer reflowcycles) {
        this.reflowcycles = reflowcycles;
    }

    /**
     * 获取环保标准
     *
     * @return Environment - 环保标准
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * 设置环保标准
     *
     * @param environment 环保标准
     */
    public void setEnvironment(String environment) {
        this.environment = environment == null ? null : environment.trim();
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