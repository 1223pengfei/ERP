package com.scd.erp.Vo.baseData;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Table(name = "base_package")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasePackage {
    @Id
    @Column(name = "PackageID")
    private Integer packageid;

    @Column(name = "PackageNum")
    private String packagenum;

    @Column(name = "PackageName")
    private String packagename;

    @Column(name = "PackageShortName")
    private String packageshortname;

    private List<BasePackage> packages;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BasePackage{");
        sb.append("packageid=").append(packageid);
        sb.append(", packagenum='").append(packagenum).append('\'');
        sb.append(", packagename='").append(packagename).append('\'');
        sb.append(", packageshortname='").append(packageshortname).append('\'');
        sb.append(", packages=").append(packages);
        sb.append('}');
        return sb.toString();
    }

    public List<BasePackage> getPackages() {
        return packages;
    }

    public void setPackages(List<BasePackage> packages) {
        this.packages = packages;
    }

    /**
     * @return PackageID
     */
    public Integer getPackageid() {
        return packageid;
    }

    /**
     * @param packageid
     */
    public void setPackageid(Integer packageid) {
        this.packageid = packageid;
    }

    /**
     * @return PackageNum
     */
    public String getPackagenum() {
        return packagenum;
    }

    /**
     * @param packagenum
     */
    public void setPackagenum(String packagenum) {
        this.packagenum = packagenum == null ? null : packagenum.trim();
    }

    /**
     * @return PackageName
     */
    public String getPackagename() {
        return packagename;
    }

    /**
     * @param packagename
     */
    public void setPackagename(String packagename) {
        this.packagename = packagename == null ? null : packagename.trim();
    }

    /**
     * @return PackageShortName
     */
    public String getPackageshortname() {
        return packageshortname;
    }

    /**
     * @param packageshortname
     */
    public void setPackageshortname(String packageshortname) {
        this.packageshortname = packageshortname == null ? null : packageshortname.trim();
    }
}