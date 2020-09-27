package com.scd.erp.Vo.plm.Material;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_plm_material_layout")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Layout implements Serializable {
    @Id
    @Column(name = "layoutID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer layoutid;

    /**
     * 相关技术认证
     */
    @Column(name = "Automotive")
    private String automotive;

    /**
     * PIN脚数量
     */
    @Column(name = "TotalPins")
    private Integer totalpins;

    /**
     * 特殊特性
     */
    @Column(name = "SpecialCharacteristics")
    private String specialcharacteristics;

    /**
     * 颜色
     */
    @Column(name = "Color")
    private String color;

    /**
     * 参数值
     */
    @Column(name = "Value")
    private String value;

    /**
     * 容差（百分比）
     */
    @Column(name = "Tolerance")
    private String tolerance;

    /**
     * 工作温度范围（min/max）
     */
    @Column(name = "Temperature")
    private String temperature;

    /**
     * 电压范围
     */
    @Column(name = "VoltageRange")
    private String voltagerange;

    /**
     * 电流范围
     */
    @Column(name = "CurrentRange")
    private String currentrange;

    /**
     * 功率范围
     */
    @Column(name = "PowerRange")
    private String powerrange;

    /**
     * 重量 克g
     */
    @Column(name = "Weight_g")
    private Double weightG;

    /**
     * 静电等级
     */
    @Column(name = "ESD")
    private String esd;

    /**
     * 失效率（百分比）
     */
    @Column(name = "FIT")
    private String fit;

    /**
     * PCB封装名称
     */
    @Column(name = "Footprint")
    private String footprint;

    @Column(name = "BaseID")
    private Integer baseid;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Layout{");
        sb.append("layoutid=").append(layoutid);
        sb.append(", automotive='").append(automotive).append('\'');
        sb.append(", totalpins=").append(totalpins);
        sb.append(", specialcharacteristics='").append(specialcharacteristics).append('\'');
        sb.append(", color='").append(color).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", tolerance='").append(tolerance).append('\'');
        sb.append(", temperature='").append(temperature).append('\'');
        sb.append(", voltagerange='").append(voltagerange).append('\'');
        sb.append(", currentrange='").append(currentrange).append('\'');
        sb.append(", powerrange='").append(powerrange).append('\'');
        sb.append(", weightG=").append(weightG);
        sb.append(", esd='").append(esd).append('\'');
        sb.append(", fit='").append(fit).append('\'');
        sb.append(", footprint='").append(footprint).append('\'');
        sb.append(", baseid=").append(baseid);
        sb.append('}');
        return sb.toString();
    }

    /**
     * @return layoutID
     */
    public Integer getLayoutid() {
        return layoutid;
    }

    /**
     * @param layoutid
     */
    public void setLayoutid(Integer layoutid) {
        this.layoutid = layoutid;
    }

    /**
     * 获取相关技术认证
     *
     * @return Automotive - 相关技术认证
     */
    public String getAutomotive() {
        return automotive;
    }

    /**
     * 设置相关技术认证
     *
     * @param automotive 相关技术认证
     */
    public void setAutomotive(String automotive) {
        this.automotive = automotive == null ? null : automotive.trim();
    }

    /**
     * 获取PIN脚数量
     *
     * @return TotalPins - PIN脚数量
     */
    public Integer getTotalpins() {
        return totalpins;
    }

    /**
     * 设置PIN脚数量
     *
     * @param totalpins PIN脚数量
     */
    public void setTotalpins(Integer totalpins) {
        this.totalpins = totalpins;
    }

    /**
     * 获取特殊特性
     *
     * @return SpecialCharacteristics - 特殊特性
     */
    public String getSpecialcharacteristics() {
        return specialcharacteristics;
    }

    /**
     * 设置特殊特性
     *
     * @param specialcharacteristics 特殊特性
     */
    public void setSpecialcharacteristics(String specialcharacteristics) {
        this.specialcharacteristics = specialcharacteristics == null ? null : specialcharacteristics.trim();
    }

    /**
     * 获取颜色
     *
     * @return Color - 颜色
     */
    public String getColor() {
        return color;
    }

    /**
     * 设置颜色
     *
     * @param color 颜色
     */
    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    /**
     * 获取参数值
     *
     * @return Value - 参数值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置参数值
     *
     * @param value 参数值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * 获取容差（百分比）
     *
     * @return Tolerance - 容差（百分比）
     */
    public String getTolerance() {
        return tolerance;
    }

    /**
     * 设置容差（百分比）
     *
     * @param tolerance 容差（百分比）
     */
    public void setTolerance(String tolerance) {
        this.tolerance = tolerance == null ? null : tolerance.trim();
    }

    /**
     * 获取工作温度范围（min/max）
     *
     * @return Temperature - 工作温度范围（min/max）
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * 设置工作温度范围（min/max）
     *
     * @param temperature 工作温度范围（min/max）
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    /**
     * 获取电压范围
     *
     * @return VoltageRange - 电压范围
     */
    public String getVoltagerange() {
        return voltagerange;
    }

    /**
     * 设置电压范围
     *
     * @param voltagerange 电压范围
     */
    public void setVoltagerange(String voltagerange) {
        this.voltagerange = voltagerange == null ? null : voltagerange.trim();
    }

    /**
     * 获取电流范围
     *
     * @return CurrentRange - 电流范围
     */
    public String getCurrentrange() {
        return currentrange;
    }

    /**
     * 设置电流范围
     *
     * @param currentrange 电流范围
     */
    public void setCurrentrange(String currentrange) {
        this.currentrange = currentrange == null ? null : currentrange.trim();
    }

    /**
     * 获取功率范围
     *
     * @return PowerRange - 功率范围
     */
    public String getPowerrange() {
        return powerrange;
    }

    /**
     * 设置功率范围
     *
     * @param powerrange 功率范围
     */
    public void setPowerrange(String powerrange) {
        this.powerrange = powerrange == null ? null : powerrange.trim();
    }

    /**
     * 获取重量 克g
     *
     * @return Weight_g - 重量 克g
     */
    public Double getWeightG() {
        return weightG;
    }

    /**
     * 设置重量 克g
     *
     * @param weightG 重量 克g
     */
    public void setWeightG(Double weightG) {
        this.weightG = weightG;
    }

    /**
     * 获取静电等级
     *
     * @return ESD - 静电等级
     */
    public String getEsd() {
        return esd;
    }

    /**
     * 设置静电等级
     *
     * @param esd 静电等级
     */
    public void setEsd(String esd) {
        this.esd = esd == null ? null : esd.trim();
    }

    /**
     * 获取失效率（百分比）
     *
     * @return FIT - 失效率（百分比）
     */
    public String getFit() {
        return fit;
    }

    /**
     * 设置失效率（百分比）
     *
     * @param fit 失效率（百分比）
     */
    public void setFit(String fit) {
        this.fit = fit == null ? null : fit.trim();
    }

    /**
     * 获取PCB封装名称
     *
     * @return Footprint - PCB封装名称
     */
    public String getFootprint() {
        return footprint;
    }

    /**
     * 设置PCB封装名称
     *
     * @param footprint PCB封装名称
     */
    public void setFootprint(String footprint) {
        this.footprint = footprint == null ? null : footprint.trim();
    }

    /**
     * @return BaseID
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