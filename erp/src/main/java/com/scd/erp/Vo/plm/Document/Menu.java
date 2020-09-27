package com.scd.erp.Vo.plm.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "erp_plm_document_menu")
public class Menu implements Serializable {
    @Id
    @Column(name = "menuID")
    private Integer menuid;

    @Column(name = "menuName")
    private String menuname;

    @Column(name = "menuUpID")
    private Integer menuupid;

    /**
     * 文档编码前缀
     */
    @Column(name = "DocCodePrefix")
    private String doccodeprefix;

    @Column(name = "DocName")
    private String docname;

    /**
     * Pc:项目编号 Pn:项目名称 Dn:文档名称 Cn:产品名称 Cc:产品ERP编码
     */
    @Column(name = "DocSubName")
    private String docsubname;

    private List<Menu> menus;
    private List<Document> documents;

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Menu{");
        sb.append("menuid=").append(menuid);
        sb.append(", menuname='").append(menuname).append('\'');
        sb.append(", menuupid=").append(menuupid);
        sb.append(", doccodeprefix='").append(doccodeprefix).append('\'');
        sb.append(", docname='").append(docname).append('\'');
        sb.append(", docsubname='").append(docsubname).append('\'');
        sb.append(", menus=").append(menus);
        sb.append('}');
        return sb.toString();
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    /**
     * @return menuID
     */
    public Integer getMenuid() {
        return menuid;
    }

    /**
     * @param menuid
     */
    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    /**
     * @return menuName
     */
    public String getMenuname() {
        return menuname;
    }

    /**
     * @param menuname
     */
    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    /**
     * @return menuUpID
     */
    public Integer getMenuupid() {
        return menuupid;
    }

    /**
     * @param menuupid
     */
    public void setMenuupid(Integer menuupid) {
        this.menuupid = menuupid;
    }

    /**
     * 获取文档编码前缀
     *
     * @return DocCodePrefix - 文档编码前缀
     */
    public String getDoccodeprefix() {
        return doccodeprefix;
    }

    /**
     * 设置文档编码前缀
     *
     * @param doccodeprefix 文档编码前缀
     */
    public void setDoccodeprefix(String doccodeprefix) {
        this.doccodeprefix = doccodeprefix == null ? null : doccodeprefix.trim();
    }

    /**
     * @return DocName
     */
    public String getDocname() {
        return docname;
    }

    /**
     * @param docname
     */
    public void setDocname(String docname) {
        this.docname = docname == null ? null : docname.trim();
    }

    /**
     * 获取Pc:项目编号 Pn:项目名称 Dn:文档名称 Cn:产品名称 Cc:产品ERP编码
     *
     * @return DocSubName - Pc:项目编号 Pn:项目名称 Dn:文档名称 Cn:产品名称 Cc:产品ERP编码
     */
    public String getDocsubname() {
        return docsubname;
    }

    /**
     * 设置Pc:项目编号 Pn:项目名称 Dn:文档名称 Cn:产品名称 Cc:产品ERP编码
     *
     * @param docsubname Pc:项目编号 Pn:项目名称 Dn:文档名称 Cn:产品名称 Cc:产品ERP编码
     */
    public void setDocsubname(String docsubname) {
        this.docsubname = docsubname == null ? null : docsubname.trim();
    }

}