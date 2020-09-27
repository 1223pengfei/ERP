package com.scd.erp.Vo.plm.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "erp_plm_document_msg")
public class Document implements Serializable {
    @Id
    @Column(name = "documentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer documentid;

    /**
     * 文档名称
     */
    @Column(name = "docName")
    private String docname;

    /**
     * 文档编号
     */
    @Column(name = "docNum")
    private String docnum;

    /**
     * 文档阶段
     */
    @Column(name = "docPhase")
    private String docphase;

    /**
     * 创建者ID
     */
    @Column(name = "creatUserID")
    private Integer creatuserid;



    /**
     * 创建时间
     */
    @Column(name = "createTime")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    @Column(name = "menuID")
    private Integer menuid;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Document{");
        sb.append("documentid=").append(documentid);
        sb.append(", docname='").append(docname).append('\'');
        sb.append(", docnum='").append(docnum).append('\'');
        sb.append(", docphase='").append(docphase).append('\'');
        sb.append(", creatuserid=").append(creatuserid);
        sb.append(", createtime=").append(createtime);
        sb.append(", menuid=").append(menuid);
        sb.append(", versions=").append(versions);
        sb.append('}');
        return sb.toString();
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    private List<Version> versions;
    private Version version;

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    /**
     * @return documentID
     */
    public Integer getDocumentid() {
        return documentid;
    }

    /**
     * @param documentid
     */
    public void setDocumentid(Integer documentid) {
        this.documentid = documentid;
    }

    /**
     * 获取文档名称
     *
     * @return docName - 文档名称
     */
    public String getDocname() {
        return docname;
    }

    /**
     * 设置文档名称
     *
     * @param docname 文档名称
     */
    public void setDocname(String docname) {
        this.docname = docname == null ? null : docname.trim();
    }

    /**
     * 获取文档编号
     *
     * @return docNum - 文档编号
     */
    public String getDocnum() {
        return docnum;
    }

    /**
     * 设置文档编号
     *
     * @param docnum 文档编号
     */
    public void setDocnum(String docnum) {
        this.docnum = docnum == null ? null : docnum.trim();
    }


    /**
     * 获取创建者ID
     *
     * @return creatUserID - 创建者ID
     */
    public Integer getCreatuserid() {
        return creatuserid;
    }

    /**
     * 设置创建者ID
     *
     * @param creatuserid 创建者ID
     */
    public void setCreatuserid(Integer creatuserid) {
        this.creatuserid = creatuserid;
    }


    /**
     * 获取创建时间
     *
     * @return createTime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDocphase() {
        return docphase;
    }

    public void setDocphase(String docphase) {
        this.docphase = docphase;
    }

}