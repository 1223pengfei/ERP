package com.scd.erp.Vo.Nail;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "erp_trace_nail_msg")
public class Nail implements Serializable {
    @Id
    @Column(name = "nailID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nailid;

    /**
     * 故障名
     */
    @Column(name = "nailName")
    private String nailname;

    private List<Feature> features;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Nail{");
        sb.append("nailid=").append(nailid);
        sb.append(", nailname='").append(nailname).append('\'');
        sb.append(", features=").append(features);
        sb.append('}');
        return sb.toString();
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    /**
     * @return nailID
     */
    public Integer getNailid() {
        return nailid;
    }

    /**
     * @param nailid
     */
    public void setNailid(Integer nailid) {
        this.nailid = nailid;
    }

    /**
     * 获取故障名
     *
     * @return nailName - 故障名
     */
    public String getNailname() {
        return nailname;
    }

    /**
     * 设置故障名
     *
     * @param nailname 故障名
     */
    public void setNailname(String nailname) {
        this.nailname = nailname == null ? null : nailname.trim();
    }
}