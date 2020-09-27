package com.scd.erp.Vo.Product;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "erp_trace_product_type")
public class Type  implements Serializable {
    @Id
    @Column(name = "typeID")
    private Integer typeid;

    @Column(name = "typeName")
    private String typename;

    /**
     * @return typeID
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * @param typeid
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
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