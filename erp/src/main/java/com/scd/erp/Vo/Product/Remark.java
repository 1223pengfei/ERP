package com.scd.erp.Vo.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "erp_trace_product_remark")
public class Remark implements Serializable {
    @Id
    @Column(name = "remarkID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer remarkid;

    @Column(name = "productID")
    private Integer productid;

    @Column(name = "creatTime")
    private Date creattime;

    @Column(name = "lotNumber")
    private String lotnumber;

    @Column(name = "proTotal")
    private Integer prototal;

    private List<Serial> serials;
    private Product product;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Remark{");
        sb.append("remarkid=").append(remarkid);
        sb.append(", productid=").append(productid);
        sb.append(", creattime=").append(creattime);
        sb.append(", lotnumber='").append(lotnumber).append('\'');
        sb.append(", prototal=").append(prototal);
        sb.append(", serials=").append(serials);
        sb.append(", product=").append(product);
        sb.append('}');
        return sb.toString();
    }

    public List<Serial> getSerials() {
        return serials;
    }

    public void setSerials(List<Serial> serials) {
        this.serials = serials;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return remarkID
     */
    public Integer getRemarkid() {
        return remarkid;
    }

    /**
     * @param remarkid
     */
    public void setRemarkid(Integer remarkid) {
        this.remarkid = remarkid;
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
     * @return creatTime
     */
    public Date getCreattime() {
        return creattime;
    }

    /**
     * @param creattime
     */
    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    /**
     * @return lotNumber
     */
    public String getLotnumber() {
        return lotnumber;
    }

    /**
     * @param lotnumber
     */
    public void setLotnumber(String lotnumber) {
        this.lotnumber = lotnumber == null ? null : lotnumber.trim();
    }

    /**
     * @return proTotal
     */
    public Integer getPrototal() {
        return prototal;
    }

    /**
     * @param prototal
     */
    public void setPrototal(Integer prototal) {
        this.prototal = prototal;
    }
}