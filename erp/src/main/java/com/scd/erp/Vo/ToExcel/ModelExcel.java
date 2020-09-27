package com.scd.erp.Vo.ToExcel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

public class ModelExcel implements Serializable {
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "身份证号", orderNum = "1")
    private String code;

    public ModelExcel() {
    }

    public ModelExcel(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
