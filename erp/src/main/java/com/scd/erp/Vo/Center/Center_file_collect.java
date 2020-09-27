package com.scd.erp.Vo.Center;

import javax.persistence.*;

@Table(name = "erp_center_file_collect")
public class Center_file_collect {
    @Id
    @Column(name = "privateID")
    private Integer privateid;

    @Column(name = "collectID")
    private Integer collectid;

    @Column(name = "fileID")
    private Integer fileid;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Center_file_collect{");
        sb.append("privateid=").append(privateid);
        sb.append(", collectid=").append(collectid);
        sb.append(", fileid=").append(fileid);
        sb.append('}');
        return sb.toString();
    }

    /**
     * @return privateID
     */
    public Integer getPrivateid() {
        return privateid;
    }

    /**
     * @param privateid
     */
    public void setPrivateid(Integer privateid) {
        this.privateid = privateid;
    }

    /**
     * @return collectID
     */
    public Integer getCollectid() {
        return collectid;
    }

    /**
     * @param collectid
     */
    public void setCollectid(Integer collectid) {
        this.collectid = collectid;
    }

    /**
     * @return fileID
     */
    public Integer getFileid() {
        return fileid;
    }

    /**
     * @param fileid
     */
    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }
}