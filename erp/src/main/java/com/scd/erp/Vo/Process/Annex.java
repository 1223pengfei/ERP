package com.scd.erp.Vo.Process;

import com.scd.erp.Vo.Person.Person;
import com.scd.erp.Vo.plm.Document.Document;
import com.scd.erp.constant.ConstantMaterials;
import com.scd.erp.utils.common.DoubleUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "erp_process_annex")
public class Annex implements Serializable {
    @Id
    @Column(name = "annexID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer annexid;

    private String topic;

    private String detail;

    @Column(name = "creatTime")
    private Date creattime;

    @Column(name = "creatUserID")
    private Integer creatuserid;

    @Column(name = "checkNumber")
    private Integer checknumber;

    @Column(name = "checkedNumber")
    private Integer checkednumber;

    @Column(name = "state")
    private Integer state;

    public String state(){
        Integer state = this.state;
        if (null != state){
            return ConstantMaterials.ANNSTATE[state];
        }
        return null;
    }

    @Column(name = "type")
    private Integer type;

    private Person person;
    private List<Document> documents;
    private List<AnnexRecode> recodes;

    private String schedule;

    public String ScheduleHandle(Integer i){
       if (i==null)i=0;
       this.checkednumber = this.checkednumber==null?1:this.checkednumber+i;
       this.schedule = new StringBuilder().append(
               DoubleUtil.chu2(this.checkednumber,this.checknumber,4)*100).toString()+"%";
       return this.schedule;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<AnnexRecode> getRecodes() {
        return recodes;
    }

    public void setRecodes(List<AnnexRecode> recodes) {
        this.recodes = recodes;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Annex{");
        sb.append("annexid=").append(annexid);
        sb.append(", topic='").append(topic).append('\'');
        sb.append(", detail='").append(detail).append('\'');
        sb.append(", creattime=").append(creattime);
        sb.append(", creatuserid=").append(creatuserid);
        sb.append(", checknumber=").append(checknumber);
        sb.append(", checkednumber=").append(checkednumber);
        sb.append(", state=").append(state);
        sb.append(", person=").append(person);
        sb.append(", documents=").append(documents);
        sb.append('}');
        return sb.toString();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    /**
     * @return annexID
     */
    public Integer getAnnexid() {
        return annexid;
    }

    /**
     * @param annexid
     */
    public void setAnnexid(Integer annexid) {
        this.annexid = annexid;
    }

    /**
     * @return topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic
     */
    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    /**
     * @return detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
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
     * @return creatUserID
     */
    public Integer getCreatuserid() {
        return creatuserid;
    }

    /**
     * @param creatuserid
     */
    public void setCreatuserid(Integer creatuserid) {
        this.creatuserid = creatuserid;
    }

    /**
     * @return checkNumber
     */
    public Integer getChecknumber() {
        return checknumber;
    }

    /**
     * @param checknumber
     */
    public void setChecknumber(Integer checknumber) {
        this.checknumber = checknumber;
    }

    /**
     * @return checkedNumber
     */
    public Integer getCheckednumber() {
        return checkednumber;
    }

    /**
     * @param checkednumber
     */
    public void setCheckednumber(Integer checkednumber) {
        this.checkednumber = checkednumber;
    }

    /**
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }

}