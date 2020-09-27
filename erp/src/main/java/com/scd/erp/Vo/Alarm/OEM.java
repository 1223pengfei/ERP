package com.scd.erp.Vo.Alarm;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "erp_trace_oem_msg")
public class OEM implements Serializable {
    @Id
    @Column(name = "oemID")
    private Integer oemid;

    @Column(name = "oemName")
    private String oemname;

    @Column(name = "linkerName")
    private String linkername;

    @Column(name = "linkerTell")
    private String linkertell;

    @Column(name = "linkerEmail")
    private String linkeremail;

    @Column(name = "oemAddr")
    private String oemaddr;

    @Column(name = "oemUB")
    private String oemub;

    /**
     * @return oemID
     */
    public Integer getOemid() {
        return oemid;
    }

    /**
     * @param oemid
     */
    public void setOemid(Integer oemid) {
        this.oemid = oemid;
    }

    /**
     * @return oemName
     */
    public String getOemname() {
        return oemname;
    }

    /**
     * @param oemname
     */
    public void setOemname(String oemname) {
        this.oemname = oemname == null ? null : oemname.trim();
    }

    /**
     * @return linkerName
     */
    public String getLinkername() {
        return linkername;
    }

    /**
     * @param linkername
     */
    public void setLinkername(String linkername) {
        this.linkername = linkername == null ? null : linkername.trim();
    }

    /**
     * @return linkerTell
     */
    public String getLinkertell() {
        return linkertell;
    }

    /**
     * @param linkertell
     */
    public void setLinkertell(String linkertell) {
        this.linkertell = linkertell == null ? null : linkertell.trim();
    }

    /**
     * @return linkerEmail
     */
    public String getLinkeremail() {
        return linkeremail;
    }

    /**
     * @param linkeremail
     */
    public void setLinkeremail(String linkeremail) {
        this.linkeremail = linkeremail == null ? null : linkeremail.trim();
    }

    /**
     * @return oemAddr
     */
    public String getOemaddr() {
        return oemaddr;
    }

    /**
     * @param oemaddr
     */
    public void setOemaddr(String oemaddr) {
        this.oemaddr = oemaddr == null ? null : oemaddr.trim();
    }

    /**
     * @return oemUB
     */
    public String getOemub() {
        return oemub;
    }

    /**
     * @param oemub
     */
    public void setOemub(String oemub) {
        this.oemub = oemub == null ? null : oemub.trim();
    }
}