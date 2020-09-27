package com.scd.erp.Vo.plm.Center;

import javax.persistence.*;

@Table(name = "erp_plm_center_material_document_link")
public class documentLink {
    @Id
    @Column(name = "material_document_ID")
    private Integer materialDocumentId;

    @Column(name = "materialID")
    private Integer materialid;

    @Column(name = "documentID")
    private Integer documentid;

    /**
     * @return material_document_ID
     */
    public Integer getMaterialDocumentId() {
        return materialDocumentId;
    }

    /**
     * @param materialDocumentId
     */
    public void setMaterialDocumentId(Integer materialDocumentId) {
        this.materialDocumentId = materialDocumentId;
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
        sb.append(", materialDocumentId=").append(materialDocumentId);
        sb.append(", materialid=").append(materialid);
        sb.append(", documentid=").append(documentid);
        sb.append("]");
        return sb.toString();
    }
}