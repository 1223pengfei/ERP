package com.scd.erp.Vo.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "erp_trace_product_msg")
public class Product implements Serializable {
    @Id
    @Column(name = "productID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productid;

    @Column(name = "proName")
    private String proname;

    @Column(name = "proCode")
    private String procode;

    @Column(name = "typeID")
    private Integer typeid;

    private Integer inventory;

    private Integer offered;

    private Integer production;

    private Integer total;

    private List<Remark> remarks;
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("productid=").append(productid);
        sb.append(", proname='").append(proname).append('\'');
        sb.append(", procode='").append(procode).append('\'');
        sb.append(", typeid=").append(typeid);
        sb.append(", inventory=").append(inventory);
        sb.append(", offered=").append(offered);
        sb.append(", production=").append(production);
        sb.append(", total=").append(total);
        sb.append(", remarks=").append(remarks);
        sb.append('}');
        return sb.toString();
    }

    public List<Remark> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<Remark> remarks) {
        this.remarks = remarks;
    }

    /**
     * @return productID
     */
    public Integer getProductid() {
        return productid;
    }

    /**
     * @param productid
     */
    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    /**
     * @return proName
     */
    public String getProname() {
        return proname;
    }

    /**
     * @param proname
     */
    public void setProname(String proname) {
        this.proname = proname == null ? null : proname.trim();
    }

    /**
     * @return proCode
     */
    public String getProcode() {
        return procode;
    }

    /**
     * @param procode
     */
    public void setProcode(String procode) {
        this.procode = procode == null ? null : procode.trim();
    }

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
     * @return inventory
     */
    public Integer getInventory() {
        return inventory;
    }

    /**
     * @param inventory
     */
    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    /**
     * @return offered
     */
    public Integer getOffered() {
        return offered;
    }

    /**
     * @param offered
     */
    public void setOffered(Integer offered) {
        this.offered = offered;
    }

    /**
     * @return production
     */
    public Integer getProduction() {
        return production;
    }

    /**
     * @param production
     */
    public void setProduction(Integer production) {
        this.production = production;
    }

    /**
     * @return total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }
}