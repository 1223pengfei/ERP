package com.scd.erp.Vo.auth;

import javax.persistence.*;
import java.util.List;

@Table(name = "erp_auth_permission")
public class Permission {
    @Id
    @Column(name = "permissionID")
    private Integer permissionid;

    /**
     * 权限名称
     */
    @Column(name = "permissionName")
    private String permissionname;

    /**
     * 控制地址
     */
    @Column(name = "permissionUrl")
    private String permissionurl;

    /**
     * 权限编号
     */
    @Column(name = "permissionCode")
    private String permissioncode;

    /**
     * 上级权限
     */
    @Column(name = "upPermissionID")
    private Integer uppermissionid;

    private List<Permission> permissions;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Permission{");
        sb.append("permissionid=").append(permissionid);
        sb.append(", permissionname='").append(permissionname).append('\'');
        sb.append(", permissionurl='").append(permissionurl).append('\'');
        sb.append(", permissioncode='").append(permissioncode).append('\'');
        sb.append(", uppermissionid=").append(uppermissionid);
        sb.append(", permissions=").append(permissions);
        sb.append('}');
        return sb.toString();
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    /**
     * @return permissionID
     */
    public Integer getPermissionid() {
        return permissionid;
    }

    /**
     * @param permissionid
     */
    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }

    /**
     * 获取权限名称
     *
     * @return permissionName - 权限名称
     */
    public String getPermissionname() {
        return permissionname;
    }

    /**
     * 设置权限名称
     *
     * @param permissionname 权限名称
     */
    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname == null ? null : permissionname.trim();
    }

    /**
     * 获取控制地址
     *
     * @return permissionUrl - 控制地址
     */
    public String getPermissionurl() {
        return permissionurl;
    }

    /**
     * 设置控制地址
     *
     * @param permissionurl 控制地址
     */
    public void setPermissionurl(String permissionurl) {
        this.permissionurl = permissionurl == null ? null : permissionurl.trim();
    }

    /**
     * 获取权限编号
     *
     * @return permissionCode - 权限编号
     */
    public String getPermissioncode() {
        return permissioncode;
    }

    /**
     * 设置权限编号
     *
     * @param permissioncode 权限编号
     */
    public void setPermissioncode(String permissioncode) {
        this.permissioncode = permissioncode == null ? null : permissioncode.trim();
    }

    /**
     * 获取上级权限
     *
     * @return upPermissionID - 上级权限
     */
    public Integer getUppermissionid() {
        return uppermissionid;
    }

    /**
     * 设置上级权限
     *
     * @param uppermissionid 上级权限
     */
    public void setUppermissionid(Integer uppermissionid) {
        this.uppermissionid = uppermissionid;
    }
}