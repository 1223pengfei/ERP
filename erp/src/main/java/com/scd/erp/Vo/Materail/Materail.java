package com.scd.erp.Vo.Materail;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "erp_trace_materail_msg")
public class Materail implements Serializable {
    @Id
    @Column(name = "materialID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer materialid;

    /**
     * 素材类型 1 图片 2 视频 3音频
     */
    @Column(name = "materialType")
    private Integer materialtype;

    /**
     * 素材名称
     */
    @Column(name = "materialName")
    private String materialname;

    /**
     * 素材地址
     */
    @Column(name = "materialUrl")
    private String materialurl;

    /**
     * 建立时间
     */
    @Column(name = "creatDate")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date creatdate;

    @Column(name = "creatID")
    private Integer creatid;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Materail{");
        sb.append("materialid=").append(materialid);
        sb.append(", materialtype=").append(materialtype);
        sb.append(", materialname='").append(materialname).append('\'');
        sb.append(", materialurl='").append(materialurl).append('\'');
        sb.append(", creatdate=").append(creatdate);
        sb.append(", creatid=").append(creatid);
        sb.append('}');
        return sb.toString();
    }

    /**
     * @return materialID
     */
    public Integer getMaterialid() {
        return materialid;
    }

    /**
     * @param materialid
     */
    public void setMaterialid(Integer materialid) {
        this.materialid = materialid;
    }

    /**
     * 获取素材类型 1 图片 2 视频 3音频
     *
     * @return materialType - 素材类型 1 图片 2 视频 3音频
     */
    public Integer getMaterialtype() {
        return materialtype;
    }

    /**
     * 设置素材类型 1 图片 2 视频 3音频
     *
     * @param materialtype 素材类型 1 图片 2 视频 3音频
     */
    public void setMaterialtype(Integer materialtype) {
        this.materialtype = materialtype;
    }

    /**
     * 获取素材名称
     *
     * @return materialName - 素材名称
     */
    public String getMaterialname() {
        return materialname;
    }

    /**
     * 设置素材名称
     *
     * @param materialname 素材名称
     */
    public void setMaterialname(String materialname) {
        this.materialname = materialname == null ? null : materialname.trim();
    }

    /**
     * 获取素材地址
     *
     * @return materialUrl - 素材地址
     */
    public String getMaterialurl() {
        return materialurl;
    }

    /**
     * 设置素材地址
     *
     * @param materialurl 素材地址
     */
    public void setMaterialurl(String materialurl) {
        this.materialurl = materialurl == null ? null : materialurl.trim();
    }

    /**
     * 获取建立时间
     *
     * @return creatDate - 建立时间
     */
    public Date getCreatdate() {
        return creatdate;
    }

    /**
     * 设置建立时间
     *
     * @param creatdate 建立时间
     */
    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    /**
     * @return creatID
     */
    public Integer getCreatid() {
        return creatid;
    }

    /**
     * @param creatid
     */
    public void setCreatid(Integer creatid) {
        this.creatid = creatid;
    }
}