package com.scd.erp.Vo.plm.Material;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_plm_material_stock")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock implements Serializable {
    @Id
    @Column(name = "stockID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockid;

    /**
     * 制造商
     */
    @Column(name = "Manufacturer")
    private String manufacturer;

    /**
     * 制造商标识
     */
    @Column(name = "ManufacturerIdentification")
    private String manufactureridentification;

    /**
     * 代理商
     */
    @Column(name = "Agent")
    private String agent;

    /**
     * 代理商标识
     */
    @Column(name = "AgentIdentification")
    private String agentidentification;

    /**
     * 订购码
     */
    @Column(name = "ManufacturerPartNumber")
    private String manufacturerpartnumber;

    /**
     * 物料订购周期
     */
    @Column(name = "OrderingPeriod")
    private Integer orderingperiod;

    /**
     * 最小订购数量
     */
    @Column(name = "MinPurchaseNumber")
    private Integer minpurchasenumber;

    /**
     * 是否包含原材料
     */
    @Column(name = "PriceIncludeMaterial")
    private Boolean priceincludematerial;

    /**
     * 现行价格
     */
    @Column(name = "CurrentPrice")
    private Integer currentprice;

    /**
     * 年降
     */
    @Column(name = "LTA")
    private Integer lta;

    /**
     * 阶梯价格（/划分）
     */
    @Column(name = "VolumePrice")
    private String volumeprice;

    @Column(name = "baseID")
    private Integer baseid;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Stock{");
        sb.append("stockid=").append(stockid);
        sb.append(", manufacturer='").append(manufacturer).append('\'');
        sb.append(", manufactureridentification='").append(manufactureridentification).append('\'');
        sb.append(", agent='").append(agent).append('\'');
        sb.append(", agentidentification='").append(agentidentification).append('\'');
        sb.append(", manufacturerpartnumber='").append(manufacturerpartnumber).append('\'');
        sb.append(", orderingperiod=").append(orderingperiod);
        sb.append(", minpurchasenumber=").append(minpurchasenumber);
        sb.append(", priceincludematerial=").append(priceincludematerial);
        sb.append(", currentprice=").append(currentprice);
        sb.append(", lta=").append(lta);
        sb.append(", volumeprice='").append(volumeprice).append('\'');
        sb.append(", baseid=").append(baseid);
        sb.append('}');
        return sb.toString();
    }

    /**
     * @return stockID
     */
    public Integer getStockid() {
        return stockid;
    }

    /**
     * @param stockid
     */
    public void setStockid(Integer stockid) {
        this.stockid = stockid;
    }

    /**
     * 获取制造商
     *
     * @return Manufacturer - 制造商
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 设置制造商
     *
     * @param manufacturer 制造商
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    /**
     * 获取制造商标识
     *
     * @return ManufacturerIdentification - 制造商标识
     */
    public String getManufactureridentification() {
        return manufactureridentification;
    }

    /**
     * 设置制造商标识
     *
     * @param manufactureridentification 制造商标识
     */
    public void setManufactureridentification(String manufactureridentification) {
        this.manufactureridentification = manufactureridentification == null ? null : manufactureridentification.trim();
    }

    /**
     * 获取代理商
     *
     * @return Agent - 代理商
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置代理商
     *
     * @param agent 代理商
     */
    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    /**
     * 获取代理商标识
     *
     * @return AgentIdentification - 代理商标识
     */
    public String getAgentidentification() {
        return agentidentification;
    }

    /**
     * 设置代理商标识
     *
     * @param agentidentification 代理商标识
     */
    public void setAgentidentification(String agentidentification) {
        this.agentidentification = agentidentification == null ? null : agentidentification.trim();
    }

    /**
     * 获取订购码
     *
     * @return ManufacturerPartNumber - 订购码
     */
    public String getManufacturerpartnumber() {
        return manufacturerpartnumber;
    }

    /**
     * 设置订购码
     *
     * @param manufacturerpartnumber 订购码
     */
    public void setManufacturerpartnumber(String manufacturerpartnumber) {
        this.manufacturerpartnumber = manufacturerpartnumber == null ? null : manufacturerpartnumber.trim();
    }

    /**
     * 获取物料订购周期
     *
     * @return OrderingPeriod - 物料订购周期
     */
    public Integer getOrderingperiod() {
        return orderingperiod;
    }

    /**
     * 设置物料订购周期
     *
     * @param orderingperiod 物料订购周期
     */
    public void setOrderingperiod(Integer orderingperiod) {
        this.orderingperiod = orderingperiod;
    }

    /**
     * 获取最小订购数量
     *
     * @return MinPurchaseNumber - 最小订购数量
     */
    public Integer getMinpurchasenumber() {
        return minpurchasenumber;
    }

    /**
     * 设置最小订购数量
     *
     * @param minpurchasenumber 最小订购数量
     */
    public void setMinpurchasenumber(Integer minpurchasenumber) {
        this.minpurchasenumber = minpurchasenumber;
    }

    /**
     * 获取是否包含原材料
     *
     * @return PriceIncludeMaterial - 是否包含原材料
     */
    public Boolean getPriceincludematerial() {
        return priceincludematerial;
    }

    /**
     * 设置是否包含原材料
     *
     * @param priceincludematerial 是否包含原材料
     */
    public void setPriceincludematerial(Boolean priceincludematerial) {
        this.priceincludematerial = priceincludematerial;
    }

    /**
     * 获取现行价格
     *
     * @return CurrentPrice - 现行价格
     */
    public Integer getCurrentprice() {
        return currentprice;
    }

    /**
     * 设置现行价格
     *
     * @param currentprice 现行价格
     */
    public void setCurrentprice(Integer currentprice) {
        this.currentprice = currentprice;
    }

    /**
     * 获取年降
     *
     * @return LTA - 年降
     */
    public Integer getLta() {
        return lta;
    }

    /**
     * 设置年降
     *
     * @param lta 年降
     */
    public void setLta(Integer lta) {
        this.lta = lta;
    }

    /**
     * 获取阶梯价格（/划分）
     *
     * @return VolumePrice - 阶梯价格（/划分）
     */
    public String getVolumeprice() {
        return volumeprice;
    }

    /**
     * 设置阶梯价格（/划分）
     *
     * @param volumeprice 阶梯价格（/划分）
     */
    public void setVolumeprice(String volumeprice) {
        this.volumeprice = volumeprice == null ? null : volumeprice.trim();
    }

    /**
     * @return baseID
     */
    public Integer getBaseid() {
        return baseid;
    }

    /**
     * @param baseid
     */
    public void setBaseid(Integer baseid) {
        this.baseid = baseid;
    }
}