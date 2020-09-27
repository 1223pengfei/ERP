package com.scd.erp.Vo.plm.Document;

import javax.persistence.*;

@Table(name = "erp_plm_document_keepdetail")
public class KeepDetail {
    @Id
    @Column(name = "keepDetailID")
    private Integer keepdetailid;

    @Column(name = "docID")
    private Integer docid;

    @Column(name = "keepID")
    private Integer keepid;

    @Column(name = "versonID")
    private Integer versonid;

    /**
     * @return keepDetailID
     */
    public Integer getKeepdetailid() {
        return keepdetailid;
    }

    /**
     * @param keepdetailid
     */
    public void setKeepdetailid(Integer keepdetailid) {
        this.keepdetailid = keepdetailid;
    }

    /**
     * @return docID
     */
    public Integer getDocid() {
        return docid;
    }

    /**
     * @param docid
     */
    public void setDocid(Integer docid) {
        this.docid = docid;
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
     * @return versonID
     */
    public Integer getVersonid() {
        return versonid;
    }

    /**
     * @param versonid
     */
    public void setVersonid(Integer versonid) {
        this.versonid = versonid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", keepdetailid=").append(keepdetailid);
        sb.append(", docid=").append(docid);
        sb.append(", keepid=").append(keepid);
        sb.append(", versonid=").append(versonid);
        sb.append("]");
        return sb.toString();
    }
}