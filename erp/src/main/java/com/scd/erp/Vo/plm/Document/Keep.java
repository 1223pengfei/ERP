package com.scd.erp.Vo.plm.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Table(name = "erp_plm_document_keep")
public class Keep {
    @Id
    @Column(name = "keepID")
    private Integer keepid;

    /**
     * 收藏夹名字
     */
    @Column(name = "keepName")
    private String keepname;

    @Column(name = "userID")
    private Integer userid;

    /**
     * 创建时间
     */
    @Column(name = "creatTime")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date creattime;

    private List<Document> documents;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Keep{");
        sb.append("keepid=").append(keepid);
        sb.append(", keepname='").append(keepname).append('\'');
        sb.append(", userid=").append(userid);
        sb.append(", creattime=").append(creattime);
        sb.append(", documents=").append(documents);
        sb.append('}');
        return sb.toString();
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    /**
     * @return keepID
     */
    public Integer getKeepid() {
        return keepid;
    }

    /**
     * @param keepid
     */
    public void setKeepid(Integer keepid) {
        this.keepid = keepid;
    }

    /**
     * 获取收藏夹名字
     *
     * @return keepName - 收藏夹名字
     */
    public String getKeepname() {
        return keepname;
    }

    /**
     * 设置收藏夹名字
     *
     * @param keepname 收藏夹名字
     */
    public void setKeepname(String keepname) {
        this.keepname = keepname == null ? null : keepname.trim();
    }

    /**
     * @return userID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取创建时间
     *
     * @return creatTime - 创建时间
     */
    public Date getCreattime() {
        return creattime;
    }

    /**
     * 设置创建时间
     *
     * @param creattime 创建时间
     */
    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

}