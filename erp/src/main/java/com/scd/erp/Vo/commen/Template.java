package com.scd.erp.Vo.commen;

import javax.persistence.*;

@Table(name = "erp_template_msg")
public class Template {
    @Id
    @Column(name = "templateID")
    private Integer templateid;

    @Column(name = "tepName")
    private String tepname;

    @Column(name = "tepPath")
    private String teppath;

    @Column(name = "tepInfo")
    private String tepinfo;

    @Column(name = "tepType")
    private Integer teptype;

    @Column(name = "tepSize")
    private Integer tepsize;

    @Column(name = "tepSuffix")
    private String tepsuffix;

    /**
     * @return templateID
     */
    public Integer getTemplateid() {
        return templateid;
    }

    /**
     * @param templateid
     */
    public void setTemplateid(Integer templateid) {
        this.templateid = templateid;
    }

    /**
     * @return tepName
     */
    public String getTepname() {
        return tepname;
    }

    /**
     * @param tepname
     */
    public void setTepname(String tepname) {
        this.tepname = tepname == null ? null : tepname.trim();
    }

    /**
     * @return tepPath
     */
    public String getTeppath() {
        return teppath;
    }

    /**
     * @param teppath
     */
    public void setTeppath(String teppath) {
        this.teppath = teppath == null ? null : teppath.trim();
    }

    /**
     * @return tepInfo
     */
    public String getTepinfo() {
        return tepinfo;
    }

    /**
     * @param tepinfo
     */
    public void setTepinfo(String tepinfo) {
        this.tepinfo = tepinfo == null ? null : tepinfo.trim();
    }

    /**
     * @return tepType
     */
    public Integer getTeptype() {
        return teptype;
    }

    /**
     * @param teptype
     */
    public void setTeptype(Integer teptype) {
        this.teptype = teptype;
    }

    /**
     * @return tepSize
     */
    public Integer getTepsize() {
        return tepsize;
    }

    /**
     * @param tepsize
     */
    public void setTepsize(Integer tepsize) {
        this.tepsize = tepsize;
    }

    /**
     * @return tepSuffix
     */
    public String getTepsuffix() {
        return tepsuffix;
    }

    /**
     * @param tepsuffix
     */
    public void setTepsuffix(String tepsuffix) {
        this.tepsuffix = tepsuffix == null ? null : tepsuffix.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", templateid=").append(templateid);
        sb.append(", tepname=").append(tepname);
        sb.append(", teppath=").append(teppath);
        sb.append(", tepinfo=").append(tepinfo);
        sb.append(", teptype=").append(teptype);
        sb.append(", tepsize=").append(tepsize);
        sb.append(", tepsuffix=").append(tepsuffix);
        sb.append("]");
        return sb.toString();
    }
}