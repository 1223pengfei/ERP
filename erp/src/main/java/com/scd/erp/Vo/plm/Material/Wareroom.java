package com.scd.erp.Vo.plm.Material;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_plm_material_wareroom")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wareroom implements Serializable {
    @Id
    @Column(name = "wareroomID")
    private Integer wareroomid;

    @Column(name = "wareroomCode")
    private String wareroomcode;

    @Column(name = "wareroomName")
    private String wareroomname;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("wareroomid", wareroomid)
                .append("wareroomcode", wareroomcode)
                .append("wareroomname", wareroomname)
                .toString();
    }

    /**
     * @return wareroomID
     */
    public Integer getWareroomid() {
        return wareroomid;
    }

    /**
     * @param wareroomid
     */
    public void setWareroomid(Integer wareroomid) {
        this.wareroomid = wareroomid;
    }

    /**
     * @return wareroomCode
     */
    public String getWareroomcode() {
        return wareroomcode;
    }

    /**
     * @param wareroomcode
     */
    public void setWareroomcode(String wareroomcode) {
        this.wareroomcode = wareroomcode == null ? null : wareroomcode.trim();
    }

    /**
     * @return wareroomName
     */
    public String getWareroomname() {
        return wareroomname;
    }

    /**
     * @param wareroomname
     */
    public void setWareroomname(String wareroomname) {
        this.wareroomname = wareroomname == null ? null : wareroomname.trim();
    }
}