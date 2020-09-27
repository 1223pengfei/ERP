package com.scd.erp.Vo.Nail;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_trace_nail_feature")
public class Feature implements Serializable {
    @Id
    @Column(name = "featureID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer featureid;

    /**
     * 故障点
     */
    private String feature;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Feature{");
        sb.append("featureid=").append(featureid);
        sb.append(", feature='").append(feature).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Integer getFeatureid() {
        return featureid;
    }

    public void setFeatureid(Integer featureid) {
        this.featureid = featureid;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}