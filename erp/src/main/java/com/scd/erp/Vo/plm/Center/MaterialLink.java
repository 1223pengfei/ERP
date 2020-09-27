package com.scd.erp.Vo.plm.Center;

import javax.persistence.*;

@Table(name = "erp_plm_center_material_link")
public class MaterialLink {
    @Id
    @Column(name = "materialLinkID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer materiallinkid;

    @Column(name = "mainBaseID")
    private Integer mainbaseid;

    @Column(name = "subBaseID")
    private Integer subbaseid;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MaterialLink{");
        sb.append("materiallinkid=").append(materiallinkid);
        sb.append(", mainbaseid=").append(mainbaseid);
        sb.append(", subbaseid=").append(subbaseid);
        sb.append('}');
        return sb.toString();
    }

    /**
     * @return materialLinkID
     */
    public Integer getMateriallinkid() {
        return materiallinkid;
    }

    /**
     * @param materiallinkid
     */
    public void setMateriallinkid(Integer materiallinkid) {
        this.materiallinkid = materiallinkid;
    }

    /**
     * @return mainBaseID
     */
    public Integer getMainbaseid() {
        return mainbaseid;
    }

    /**
     * @param mainbaseid
     */
    public void setMainbaseid(Integer mainbaseid) {
        this.mainbaseid = mainbaseid;
    }

    /**
     * @return subBaseID
     */
    public Integer getSubbaseid() {
        return subbaseid;
    }

    /**
     * @param subbaseid
     */
    public void setSubbaseid(Integer subbaseid) {
        this.subbaseid = subbaseid;
    }

}