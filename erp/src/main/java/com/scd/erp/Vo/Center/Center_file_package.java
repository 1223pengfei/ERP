package com.scd.erp.Vo.Center;

import javax.persistence.*;

@Table(name = "erp_center_file_package")
public class Center_file_package {
    @Id
    @Column(name = "file_package_ID")
    private Integer filePackageId;

    @Column(name = "fileID")
    private Integer fileid;

    @Column(name = "packageID")
    private Integer packageid;

    /**
     * @return file_package_ID
     */
    public Integer getFilePackageId() {
        return filePackageId;
    }

    /**
     * @param filePackageId
     */
    public void setFilePackageId(Integer filePackageId) {
        this.filePackageId = filePackageId;
    }

    /**
     * @return fileID
     */
    public Integer getFileid() {
        return fileid;
    }

    /**
     * @param fileid
     */
    public void setFileid(Integer fileid) {
        this.fileid = fileid;
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
}