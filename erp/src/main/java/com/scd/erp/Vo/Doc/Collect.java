package com.scd.erp.Vo.Doc;

import com.scd.erp.Vo.Person.Person;

import javax.persistence.*;
import java.util.List;

@Table(name = "erp_file_collect")
public class Collect {
    @Id
    @Column(name = "collectID")
    private Integer collectid;

    /**
     * 收藏夹名称
     */
    @Column(name = "collectName")
    private String collectname;

    /**
     * 收藏夹编号
     */
    @Column(name = "collectCode")
    private String collectcode;

    /**
     * 上级收藏夹
     */
    @Column(name = "upColID")
    private Integer upcolid;

    /**
     * 拥有者ID
     */
    @Column(name = "personID")
    private Integer personid;

    private List<Docement> docements;

    public List<Docement> getDocements() {
        return docements;
    }

    public void setDocements(List<Docement> docements) {
        this.docements = docements;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Collect{");
        sb.append("collectid=").append(collectid);
        sb.append(", collectname='").append(collectname).append('\'');
        sb.append(", collectcode='").append(collectcode).append('\'');
        sb.append(", upcolid=").append(upcolid);
        sb.append(", personid=").append(personid);
        sb.append(", docements=").append(docements);
        sb.append('}');
        return sb.toString();
    }

    /**
     * @return collectID
     */
    public Integer getCollectid() {
        return collectid;
    }

    /**
     * @param collectid
     */
    public void setCollectid(Integer collectid) {
        this.collectid = collectid;
    }

    /**
     * 获取收藏夹名称
     *
     * @return collectName - 收藏夹名称
     */
    public String getCollectname() {
        return collectname;
    }

    /**
     * 设置收藏夹名称
     *
     * @param collectname 收藏夹名称
     */
    public void setCollectname(String collectname) {
        this.collectname = collectname == null ? null : collectname.trim();
    }

    /**
     * 获取收藏夹编号
     *
     * @return collectCode - 收藏夹编号
     */
    public String getCollectcode() {
        return collectcode;
    }

    /**
     * 设置收藏夹编号
     *
     * @param collectcode 收藏夹编号
     */
    public void setCollectcode(String collectcode) {
        this.collectcode = collectcode == null ? null : collectcode.trim();
    }

    /**
     * 获取上级收藏夹
     *
     * @return upColID - 上级收藏夹
     */
    public Integer getUpcolid() {
        return upcolid;
    }

    /**
     * 设置上级收藏夹
     *
     * @param upcolid 上级收藏夹
     */
    public void setUpcolid(Integer upcolid) {
        this.upcolid = upcolid;
    }

    /**
     * 获取拥有者ID
     *
     * @return personID - 拥有者ID
     */
    public Integer getPersonid() {
        return personid;
    }

    /**
     * 设置拥有者ID
     *
     * @param personid 拥有者ID
     */
    public void setPersonid(Integer personid) {
        this.personid = personid;
    }
}