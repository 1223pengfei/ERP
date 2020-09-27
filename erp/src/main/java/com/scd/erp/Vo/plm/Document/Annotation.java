package com.scd.erp.Vo.plm.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "erp_plm_document_version_annotation")
public class Annotation implements Serializable {
    @Id
    @Column(name = "annotationID")
    private Integer annotationid;

    /**
     * 批注人
     */
    @Column(name = "annUserID")
    private Integer annuserid;

    /**
     * 批注内容
     */
    private String annotation;

    /**
     * 批注时间
     */
    @Column(name = "creatTime")
    private Date creattime;

    @Column(name = "versionID")
    private Integer versionid;

    /**
     * @return annotationID
     */
    public Integer getAnnotationid() {
        return annotationid;
    }

    /**
     * @param annotationid
     */
    public void setAnnotationid(Integer annotationid) {
        this.annotationid = annotationid;
    }

    /**
     * 获取批注人
     *
     * @return annUserID - 批注人
     */
    public Integer getAnnuserid() {
        return annuserid;
    }

    /**
     * 设置批注人
     *
     * @param annuserid 批注人
     */
    public void setAnnuserid(Integer annuserid) {
        this.annuserid = annuserid;
    }

    /**
     * 获取批注内容
     *
     * @return annotation - 批注内容
     */
    public String getAnnotation() {
        return annotation;
    }

    /**
     * 设置批注内容
     *
     * @param annotation 批注内容
     */
    public void setAnnotation(String annotation) {
        this.annotation = annotation == null ? null : annotation.trim();
    }

    /**
     * 获取批注时间
     *
     * @return creatTime - 批注时间
     */
    public Date getCreattime() {
        return creattime;
    }

    /**
     * 设置批注时间
     *
     * @param creattime 批注时间
     */
    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    /**
     * @return versionID
     */
    public Integer getVersionid() {
        return versionid;
    }

    /**
     * @param versionid
     */
    public void setVersionid(Integer versionid) {
        this.versionid = versionid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", annotationid=").append(annotationid);
        sb.append(", annuserid=").append(annuserid);
        sb.append(", annotation=").append(annotation);
        sb.append(", creattime=").append(creattime);
        sb.append(", versionid=").append(versionid);
        sb.append("]");
        return sb.toString();
    }
}