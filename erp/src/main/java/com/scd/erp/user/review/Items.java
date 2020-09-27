package com.scd.erp.user.review;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_project_review_items")
public class Items implements Serializable {
    @Id
    private Integer iid;

    /**
     * 评审项目
     */
    private String items;

    /**
     * @return iid
     */
    public Integer getIid() {
        return iid;
    }

    /**
     * @param iid
     */
    public void setIid(Integer iid) {
        this.iid = iid;
    }

    /**
     * 获取评审项目
     *
     * @return items - 评审项目
     */
    public String getItems() {
        return items;
    }

    /**
     * 设置评审项目
     *
     * @param items 评审项目
     */
    public void setItems(String items) {
        this.items = items == null ? null : items.trim();
    }
}