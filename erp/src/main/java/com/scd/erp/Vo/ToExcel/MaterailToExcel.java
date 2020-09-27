package com.scd.erp.Vo.ToExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;

import java.io.Serializable;
import java.util.List;

public class MaterailToExcel implements Serializable {
    @Excel(name = "序号")
    private Integer num;
    @Excel(name = "名称", orderNum = "2")
    private String namecn;
    @Excel(name = "零件号", orderNum = "1")
    private String customerpn;
    @Excel(name = "数量", orderNum = "4")
    private Integer amount;
    @Excel(name = "单位", orderNum = "3")
    private Integer unit;
    @ExcelCollection(name = "订购码", orderNum = "5")
    private List<String> manufacturerpartnumber;
    @Excel(name = "封装", orderNum = "8")
    private String footprint;
    @Excel(name = "位号", orderNum = "9")
    private String tag;
    @ExcelCollection(name = "厂家/品牌", orderNum = "10")
    private List<String> companys;
    @Excel(name = "备注", orderNum = "14")
    private String remark;
    @Excel(name = "父件", orderNum = "13")
    private String baba;

    public String getBaba() {
        return baba;
    }

    public void setBaba(String baba) {
        this.baba = baba;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getNamecn() {
        return namecn;
    }

    public void setNamecn(String namecn) {
        this.namecn = namecn;
    }

    public String getCustomerpn() {
        return customerpn;
    }

    public void setCustomerpn(String customerpn) {
        this.customerpn = customerpn;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public List<String> getManufacturerpartnumber() {
        return manufacturerpartnumber;
    }

    public void setManufacturerpartnumber(List<String> manufacturerpartnumber) {
        this.manufacturerpartnumber = manufacturerpartnumber;
    }

    public String getFootprint() {
        return footprint;
    }

    public void setFootprint(String footprint) {
        this.footprint = footprint;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<String> getCompanys() {
        return companys;
    }

    public void setCompanys(List<String> companys) {
        this.companys = companys;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
