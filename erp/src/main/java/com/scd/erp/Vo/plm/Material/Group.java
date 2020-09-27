package com.scd.erp.Vo.plm.Material;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_plm_material_group")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Group implements Serializable {
    @Id
    @Column(name = "groupID")
    private Integer groupid;

    /**
     * 分组编号
     */
    @Column(name = "groupNum")
    private String groupnum;

    /**
     * 分组名称
     */
    @Column(name = "groupName")
    private String groupname;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Group{");
        sb.append("groupid=").append(groupid);
        sb.append(", groupnum='").append(groupnum).append('\'');
        sb.append(", groupname='").append(groupname).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * @return groupID
     */
    public Integer getGroupid() {
        return groupid;
    }

    /**
     * @param groupid
     */
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    /**
     * 获取分组编号
     *
     * @return groupNum - 分组编号
     */
    public String getGroupnum() {
        return groupnum;
    }

    /**
     * 设置分组编号
     *
     * @param groupnum 分组编号
     */
    public void setGroupnum(String groupnum) {
        this.groupnum = groupnum == null ? null : groupnum.trim();
    }

    /**
     * 获取分组名称
     *
     * @return groupName - 分组名称
     */
    public String getGroupname() {
        return groupname;
    }

    /**
     * 设置分组名称
     *
     * @param groupname 分组名称
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }
}