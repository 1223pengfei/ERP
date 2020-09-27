package com.scd.erp.Vo.plm.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scd.erp.constant.ConstantMaterials;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "erp_plm_document_version")
public class Version implements Serializable {
    @Id
    @Column(name = "versionID")
    private Integer versionid;

    /**
     * 文档编号
     */
    @Column(name = "versionCode")
    private Integer versioncode;

    /**
     * 更新人ID
     */
    @Column(name = "setUserID")
    private Integer setuserid;

    /**
     * 文件地址
     */
    @Column(name = "docUrl")
    private String docurl;

    /**
     * 文件大小
     */
    @Column(name = "docSize")
    private Integer docsize;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 更新时间
     */
    @Column(name = "updataTime")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date updatatime;

    /**
     * 文档ID
     */
    @Column(name = "docID")
    private Integer docid;
    @Column(name = "verState")
    private Integer verstate;

    public String state(){
        Integer state = this.verstate;
        if (state !=null){
            return ConstantMaterials.VERSTATE[state];
        }
        return null;
    }

    @Column(name = "verIte")
    private Integer verIte;

    public Integer getVerIte() {
        return verIte;
    }

    public void setVerIte(Integer verIte) {
        this.verIte = verIte;
    }

    public Integer getVerstate() {
        return verstate;
    }

    public void setVerstate(Integer verstate) {
        this.verstate = verstate;
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

    /**
     * 获取文档编号
     *
     * @return versionCode - 文档编号
     */
    public Integer getVersioncode() {
        return versioncode;
    }

    /**
     * 设置文档编号
     *
     * @param versioncode 文档编号
     */
    public void setVersioncode(Integer versioncode) {
        this.versioncode = versioncode ;
    }

    /**
     * 获取更新人ID
     *
     * @return setUserID - 更新人ID
     */
    public Integer getSetuserid() {
        return setuserid;
    }

    /**
     * 设置更新人ID
     *
     * @param setuserid 更新人ID
     */
    public void setSetuserid(Integer setuserid) {
        this.setuserid = setuserid;
    }

    /**
     * 获取文件地址
     *
     * @return docUrl - 文件地址
     */
    public String getDocurl() {
        return docurl;
    }

    /**
     * 设置文件地址
     *
     * @param docurl 文件地址
     */
    public void setDocurl(String docurl) {
        this.docurl = docurl == null ? null : docurl.trim();
    }

    /**
     * 获取文件大小
     *
     * @return docSize - 文件大小
     */
    public Integer getDocsize() {
        return docsize;
    }

    /**
     * 设置文件大小
     *
     * @param docsize 文件大小
     */
    public void setDocsize(Integer docsize) {
        this.docsize = docsize;
    }

    /**
     * 获取文件后缀
     *
     * @return suffix - 文件后缀
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * 设置文件后缀
     *
     * @param suffix 文件后缀
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }

    /**
     * 获取更新时间
     *
     * @return updataTime - 更新时间
     */
    public Date getUpdatatime() {
        return updatatime;
    }

    /**
     * 设置更新时间
     *
     * @param updatatime 更新时间
     */
    public void setUpdatatime(Date updatatime) {
        this.updatatime = updatatime;
    }

    /**
     * 获取文档ID
     *
     * @return docID - 文档ID
     */
    public Integer getDocid() {
        return docid;
    }

    /**
     * 设置文档ID
     *
     * @param docid 文档ID
     */
    public void setDocid(Integer docid) {
        this.docid = docid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", versionid=").append(versionid);
        sb.append(", versioncode=").append(versioncode);
        sb.append(", setuserid=").append(setuserid);
        sb.append(", docurl=").append(docurl);
        sb.append(", docsize=").append(docsize);
        sb.append(", suffix=").append(suffix);
        sb.append(", updatatime=").append(updatatime);
        sb.append(", docid=").append(docid);
        sb.append("]");
        return sb.toString();
    }
}