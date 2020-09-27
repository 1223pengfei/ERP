package com.scd.erp.Vo.Doc;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Table(name = "erp_file_package")
public class Package {
    @Id
    @Column(name = "packageID")
    private Integer packageid;

    @Column(name = "packageName")
    private String packagename;

    @Column(name = "packageTypeID")
    private Integer packagetypeid;

    @Column(name = "packageCreatTime")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date packagecreattime;

    private Integer belongdepartmentid;

    public Integer getBelongdepartmentid() {
        return belongdepartmentid;
    }

    public void setBelongdepartmentid(Integer belongdepartmentid) {
        this.belongdepartmentid = belongdepartmentid;
    }

    private List<Docement> docements;
    private PackageType type;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Package{");
        sb.append("packageid=").append(packageid);
        sb.append(", packagename='").append(packagename).append('\'');
        sb.append(", packagetypeid=").append(packagetypeid);
        sb.append(", packagecreattime=").append(packagecreattime);
        sb.append(", docements=").append(docements);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    public List<Docement> getDocements() {
        return docements;
    }

    public void setDocements(List<Docement> docements) {
        this.docements = docements;
    }

    public PackageType getType() {
        return type;
    }

    public void setType(PackageType type) {
        this.type = type;
    }

    /**
     * @return packageID
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
     * @return packageName
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
     * @return packageTypeID
     */
    public Integer getPackagetypeid() {
        return packagetypeid;
    }

    /**
     * @param packagetypeid
     */
    public void setPackagetypeid(Integer packagetypeid) {
        this.packagetypeid = packagetypeid;
    }

    /**
     * @return packageCreatTime
     */
    public Date getPackagecreattime() {
        return packagecreattime;
    }

    /**
     * @param packagecreattime
     */
    public void setPackagecreattime(Date packagecreattime) {
        this.packagecreattime = packagecreattime;
    }
}