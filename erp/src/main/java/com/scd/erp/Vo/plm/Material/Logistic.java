package com.scd.erp.Vo.plm.Material;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scd.erp.Vo.Product.Type;
import com.scd.erp.Vo.baseData.BasePackage;
import com.scd.erp.constant.ConstantMaterials;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_plm_material_logistic")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Logistic implements Serializable {
    @Id
    @Column(name = "logisticID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logisticid;

    /**
     * 包装形式
     */
    @Column(name = "PackageID")
    private Integer packageid;

    /**
     * 计量单位
     */
    @Column(name = "Unit")
    private Integer unit;

    /**
     * 包数量
     */
    @Column(name = "PackageNumber")
    private Integer packagenumber;

    /**
     * 补给方式 采购B 生产M
     */
    @Column(name = "SupplyForm")
    private String supplyform;

    /**
     * 批次管理
     */
    @Column(name = "BatchTrace")
    private Boolean batchtrace;

    /**
     * 序列号管理
     */
    @Column(name = "SNTrace")
    private Boolean sntrace;

    /**
     * 最小库存
     */
    @Column(name = "StockMin")
    private Integer stockmin;

    /**
     * 最大库存
     */
    @Column(name = "StockMax")
    private Integer stockmax;

    /**
     * 库存类型
     */
    @Column(name = "stockTypeID")
    private Integer stocktypeid;

    /**
     * 发料方式 手工 倒冲
     */
    @Column(name = "LogisticInPlant")
    private String logisticinplant;


    @Column(name = "BaseID")
    private Integer baseid;

    private Wareroom wareroom;
    private BasePackage basePackage;

    @Override
    public String toString() {
         final StringBuffer sb = new StringBuffer("Logistic{");
        sb.append("logisticid=").append(logisticid);
        sb.append(", packageid=").append(packageid);
        sb.append(", unit='").append(unit).append('\'');
        sb.append(", packagenumber=").append(packagenumber);
        sb.append(", supplyform='").append(supplyform).append('\'');
        sb.append(", batchtrace=").append(batchtrace);
        sb.append(", sntrace=").append(sntrace);
        sb.append(", stockmin=").append(stockmin);
        sb.append(", stockmax=").append(stockmax);
        sb.append(", stocktypeid=").append(stocktypeid);
        sb.append(", logisticinplant='").append(logisticinplant).append('\'');
        sb.append(", baseid=").append(baseid);
        sb.append(", wareroom=").append(wareroom);
        sb.append(", basePackage=").append(basePackage);
        sb.append('}');
        return sb.toString();
    }

    public Wareroom getWareroom() {
        return wareroom;
    }

    public void setWareroom(Wareroom wareroom) {
        this.wareroom = wareroom;
    }


    public BasePackage getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(BasePackage basePackage) {
        this.basePackage = basePackage;
    }

    /**
     * @return logisticID
     */
    public Integer getLogisticid() {
        return logisticid;
    }

    /**
     * @param logisticid
     */
    public void setLogisticid(Integer logisticid) {
        this.logisticid = logisticid;
    }

    /**
     * 获取包装形式
     *
     * @return PackageID - 包装形式
     */
    public Integer getPackageid() {
        return packageid;
    }

    /**
     * 设置包装形式
     *
     * @param packageid 包装形式
     */
    public void setPackageid(Integer packageid) {
        this.packageid = packageid;
    }

    /**
     * 获取计量单位 个
克
包
扎
瓶
箱
米
千克
副
捆
条
块
套
     *
     * @return Unit - 计量单位 个
克
包
扎
瓶
箱
米
千克
副
捆
条
块
套
     */
    public Integer getUnit() {
        return unit;
    }
    public String getUnitI() {
        if (unit != null)
        return ConstantMaterials.UNIT[unit];
        return null;
    }
    /**
     * 设置计量单位 个

     *
     * @param unit 计量单位 个

     */
    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    /**
     * 获取包数量
     *
     * @return PackageNumber - 包数量
     */
    public Integer getPackagenumber() {
        return packagenumber;
    }

    /**
     * 设置包数量
     *
     * @param packagenumber 包数量
     */
    public void setPackagenumber(Integer packagenumber) {
        this.packagenumber = packagenumber;
    }

    /**
     * 获取补给方式 采购B 生产M
     *
     * @return SupplyForm - 补给方式 采购B 生产M
     */
    public String getSupplyform() {
        return supplyform;
    }

    /**
     * 设置补给方式 采购B 生产M
     *
     * @param supplyform 补给方式 采购B 生产M
     */
    public void setSupplyform(String supplyform) {
        this.supplyform = supplyform == null ? null : supplyform.trim();
    }

    /**
     * 获取批次管理
     *
     * @return BatchTrace - 批次管理
     */
    public Boolean getBatchtrace() {
        return batchtrace;
    }

    /**
     * 设置批次管理
     *
     * @param batchtrace 批次管理
     */
    public void setBatchtrace(Boolean batchtrace) {
        this.batchtrace = batchtrace;
    }

    /**
     * 获取序列号管理
     *
     * @return SNTrace - 序列号管理
     */
    public Boolean getSntrace() {
        return sntrace;
    }

    /**
     * 设置序列号管理
     *
     * @param sntrace 序列号管理
     */
    public void setSntrace(Boolean sntrace) {
        this.sntrace = sntrace;
    }

    /**
     * 获取最小库存
     *
     * @return StockMin - 最小库存
     */
    public Integer getStockmin() {
        return stockmin;
    }

    /**
     * 设置最小库存
     *
     * @param stockmin 最小库存
     */
    public void setStockmin(Integer stockmin) {
        this.stockmin = stockmin;
    }

    /**
     * 获取最大库存
     *
     * @return StockMax - 最大库存
     */
    public Integer getStockmax() {
        return stockmax;
    }

    /**
     * 设置最大库存
     *
     * @param stockmax 最大库存
     */
    public void setStockmax(Integer stockmax) {
        this.stockmax = stockmax;
    }

    /**
     * 获取库存类型
     *
     * @return stockTypeID - 库存类型
     */
    public Integer getStocktypeid() {
        return stocktypeid;
    }

    /**
     * 设置库存类型
     *
     * @param stocktypeid 库存类型
     */
    public void setStocktypeid(Integer stocktypeid) {
        this.stocktypeid = stocktypeid;
    }

    /**
     * 获取发料方式 手工 倒冲
     *
     * @return LogisticInPlant - 发料方式 手工 倒冲
     */
    public String getLogisticinplant() {
        return logisticinplant;
    }

    /**
     * 设置发料方式 手工 倒冲
     *
     * @param logisticinplant 发料方式 手工 倒冲
     */
    public void setLogisticinplant(String logisticinplant) {
        this.logisticinplant = logisticinplant == null ? null : logisticinplant.trim();
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