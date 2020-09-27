package com.scd.erp.user;

import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;

public class CountClass implements Serializable {

    private Long COUNT;

    private Integer state;

    private Long sum;

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Long getCOUNT() {
        return COUNT;
    }

    public void setCOUNT(Long COUNT) {
        this.COUNT = COUNT;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
