package com.scd.erp.Vo.Process;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_process_annex_link")
public class AnnexLink implements Serializable {
    @Id
    @Column(name = "annexlinkID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer annexlinkid;

    @Column(name = "annexID")
    private Integer annexid;

    @Column(name = "documentID")
    private Integer documentid;

    public AnnexLink() {
    }

    public AnnexLink(Integer annexid, Integer documentid) {
        this.annexid = annexid;
        this.documentid = documentid;
    }

    /**
     * @return annexlinkID
     */
    public Integer getAnnexlinkid() {
        return annexlinkid;
    }

    /**
     * @param annexlinkid
     */
    public void setAnnexlinkid(Integer annexlinkid) {
        this.annexlinkid = annexlinkid;
    }

    /**
     * @return annexID
     */
    public Integer getAnnexid() {
        return annexid;
    }

    /**
     * @param annexid
     */
    public void setAnnexid(Integer annexid) {
        this.annexid = annexid;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", annexlinkid=").append(annexlinkid);
        sb.append(", annexid=").append(annexid);
        sb.append(", documentid=").append(documentid);
        sb.append("]");
        return sb.toString();
    }
}