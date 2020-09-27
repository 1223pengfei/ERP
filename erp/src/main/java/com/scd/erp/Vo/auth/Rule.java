package com.scd.erp.Vo.auth;

import javax.persistence.*;
import java.util.List;

@Table(name = "erp_auth_rule")
public class Rule {
    @Id
    @Column(name = "ruleID")
    private Integer ruleid;

    /**
     * 角色名称
     */
    @Column(name = "ruleName")
    private String rulename;

    /**
     * 上级角色ID
     */
    @Column(name = "upRuleID")
    private Integer upruleid;

    /**
     * 角色说明
     */
    @Column(name = "ruleRemark")
    private String ruleremark;

    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    /**
     * @return ruleID
     */
    public Integer getRuleid() {
        return ruleid;
    }

    /**
     * @param ruleid
     */
    public void setRuleid(Integer ruleid) {
        this.ruleid = ruleid;
    }

    /**
     * 获取角色名称
     *
     * @return ruleName - 角色名称
     */
    public String getRulename() {
        return rulename;
    }

    /**
     * 设置角色名称
     *
     * @param rulename 角色名称
     */
    public void setRulename(String rulename) {
        this.rulename = rulename == null ? null : rulename.trim();
    }

    /**
     * 获取上级角色ID
     *
     * @return upRuleID - 上级角色ID
     */
    public Integer getUpruleid() {
        return upruleid;
    }

    /**
     * 设置上级角色ID
     *
     * @param upruleid 上级角色ID
     */
    public void setUpruleid(Integer upruleid) {
        this.upruleid = upruleid;
    }

    /**
     * 获取角色说明
     *
     * @return ruleRemark - 角色说明
     */
    public String getRuleremark() {
        return ruleremark;
    }

    /**
     * 设置角色说明
     *
     * @param ruleremark 角色说明
     */
    public void setRuleremark(String ruleremark) {
        this.ruleremark = ruleremark == null ? null : ruleremark.trim();
    }
}