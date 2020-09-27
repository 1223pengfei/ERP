package com.scd.erp.user.review;

import com.scd.erp.Vo.Person.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "erp_project_review_record")
public class Record implements Serializable {
    @Id
    private Integer rid;

    /**
     * 文件编号
     */
    @Column(name = "DocumentNo")
    private String documentno;

    /**
     * 版次
     */
    @Column(name = "Edition")
    private String edition;

    /**
     * 产品名称
     */
    private String pname;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * 规格型号
     */
    @Column(name = "Model")
    private String model;

    /**
     * 车辆型号
     */
    @Column(name = "CarModel")
    private String carmodel;

    /**
     * 顾客名称
     */
    @Column(name = " CustName")
    private String custname;

    /**
     * 主持人
     */
    private String host;

    /**
     * 开始时间
     */
    @Column(name = "StartTime")
    private String starttime;

    /**
     * 结束时间
     */
    @Column(name = "EndTime")
    private String endtime;

    private  Integer pid;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 包含项目
     */
    private List<Items> item;

    private Content contents;

    private List<Person> person;

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    public Content getContents() {
        return contents;
    }

    public void setContents(Content contents) {
        this.contents = contents;
    }

    public List<Items> getItem() {
        return item;
    }

    public void setItem(List<Items> item) {
        this.item = item;
    }

    /**
     * @return rid
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * @param rid
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * 获取文件编号
     *
     * @return DocumentNo - 文件编号
     */
    public String getDocumentno() {
        return documentno;
    }

    /**
     * 设置文件编号
     *
     * @param documentno 文件编号
     */
    public void setDocumentno(String documentno) {
        this.documentno = documentno == null ? null : documentno.trim();
    }

    /**
     * 获取版次
     *
     * @return Edition - 版次
     */
    public String getEdition() {
        return edition;
    }

    /**
     * 设置版次
     *
     * @param edition 版次
     */
    public void setEdition(String edition) {
        this.edition = edition == null ? null : edition.trim();
    }

    /**
     * 获取规格型号
     *
     * @return Model - 规格型号
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置规格型号
     *
     * @param model 规格型号
     */
    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    /**
     * 获取车辆型号
     *
     * @return CarModel - 车辆型号
     */
    public String getCarmodel() {
        return carmodel;
    }

    /**
     * 设置车辆型号
     *
     * @param carmodel 车辆型号
     */
    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel == null ? null : carmodel.trim();
    }

    /**
     * 获取顾客名称
     *
     * @return 

CustName - 顾客名称
     */
    public String getcustname() {
        return custname;
    }

    /**
     * 设置顾客名称
     *
     * @param 

custname 顾客名称
     */
    public void setcustname(String custname) {
        this.custname = custname == null ? null : custname.trim();
    }

    /**
     * 获取主持人
     *
     * @return host - 主持人
     */
    public String getHost() {
        return host;
    }

    /**
     * 设置主持人
     *
     * @param host 主持人
     */
    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    /**
     * 获取开始时间
     *
     * @return StartTime - 开始时间
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * 设置开始时间
     *
     * @param starttime 开始时间
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    /**
     * 获取结束时间
     *
     * @return EndTime - 结束时间
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * 设置结束时间
     *
     * @param endtime 结束时间
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }
}