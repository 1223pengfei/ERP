package com.scd.erp.Vo.plm.Note;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scd.erp.Vo.plm.Material.BaseMaterial;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "erp_plm_note_deletemsg")
public class DelMsg implements Serializable {
    @Id
    @Column(name = "delID")
    private Integer delid;

    @Column(name = "delTime")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date deltime;

    @Column(name = "delUserID")
    private Integer deluserid;

    @Column(name = "delReason")
    private String delreason;

    @Column(name = "delMaterailMSG")
    private String delmaterailmsg;

    @Column(name = "baseID")
    private Integer baseid;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("delid", delid)
                .append("deltime", deltime)
                .append("deluserid", deluserid)
                .append("delreason", delreason)
                .append("delmaterailmsg", delmaterailmsg)
                .append("baseid", baseid)
                .toString();
    }

    /**
     * @return delID
     */
    public Integer getDelid() {
        return delid;
    }

    /**
     * @param delid
     */
    public void setDelid(Integer delid) {
        this.delid = delid;
    }

    /**
     * @return delTime
     */
    public Date getDeltime() {
        return deltime;
    }

    /**
     * @param deltime
     */
    public void setDeltime(Date deltime) {
        this.deltime = deltime;
    }

    /**
     * @return delUserID
     */
    public Integer getDeluserid() {
        return deluserid;
    }

    /**
     * @param deluserid
     */
    public void setDeluserid(Integer deluserid) {
        this.deluserid = deluserid;
    }

    /**
     * @return delReason
     */
    public String getDelreason() {
        return delreason;
    }

    /**
     * @param delreason
     */
    public void setDelreason(String delreason) {
        this.delreason = delreason == null ? null : delreason.trim();
    }

    /**
     * @return delMaterailMSG
     */
    public String getDelmaterailmsg() {
        return delmaterailmsg;
    }

    /**
     * @param delmaterailmsg
     */
    public void setDelmaterailmsg(String delmaterailmsg) {
        this.delmaterailmsg = delmaterailmsg == null ? null : delmaterailmsg.trim();
    }
    public void setDelmaterailmsg(BaseMaterial material) {
        StringBuilder sb = new StringBuilder();
        sb.append("物料名称:").append(material.getNamecn()).append(",");
        sb.append("erp编号:").append(material.getErpnum()).append(",");
        sb.append("主体材料:").append(material.getMainmaterial()).append(",");
        sb.append("客户零件号:").append(material.getCustomerpn()).append(",");
        sb.append("物料版本:").append(material.getVersion()).append(",");
        sb.append("是否标准件:").append(material.getStandard() == null ?"":material.getStandard()?"是":"否");
        this.delmaterailmsg = sb.toString();
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