package com.scd.erp.Vo.Doc;

import javax.persistence.*;

@Table(name = "erp_file_packagetype")
public class PackageType {
    @Id
    @Column(name = "packTypeID")
    private Integer packtypeid;

    @Column(name = "typeName")
    private String typename;

    /**
     * @return packTypeID
     */
    public Integer getPacktypeid() {
        return packtypeid;
    }

    /**
     * @param packtypeid
     */
    public void setPacktypeid(Integer packtypeid) {
        this.packtypeid = packtypeid;
    }

    /**
     * @return typeName
     */
    public String getTypename() {
        return typename;
    }

    /**
     * @param typename
     */
    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }
}