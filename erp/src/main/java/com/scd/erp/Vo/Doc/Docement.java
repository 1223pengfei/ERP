package com.scd.erp.Vo.Doc;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "erp_file_msg")
public class Docement {
    @Id
    @Column(name = "FileID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileid;

    /**
     * 资料名称
     */
    @Column(name = "FileName")
    private String filename;

    /**
     * 资料地址
     */
    @Column(name = "FlieUrl")
    private String flieurl;

    /**
     * 资料类型
     */
    @Column(name = "FlieType")
    private String flietype;

    /**
     * 资料说明
     */
    @Column(name = "FileRemark")
    private String fileremark;

    /**
     * 建立时间
     */
    @Column(name = "FileCreatTime")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date filecreattime;

    /**
     * 删除时间
     */
    @Column(name = "FileDelTime")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date filedeltime;

    @Column(name = "DataSize")
    private Long size;

    @Column(name = "DataSuffix")
    private String suffix;

    private Integer packageid;
    private Belong belong;
    private Collect collect;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Docement{");
        sb.append("fileid=").append(fileid);
        sb.append(", filename='").append(filename).append('\'');
        sb.append(", flieurl='").append(flieurl).append('\'');
        sb.append(", flietype='").append(flietype).append('\'');
        sb.append(", fileremark='").append(fileremark).append('\'');
        sb.append(", filecreattime=").append(filecreattime);
        sb.append(", filedeltime=").append(filedeltime);
        sb.append(", size=").append(size);
        sb.append(", suffix='").append(suffix).append('\'');
        sb.append(", packageid=").append(packageid);
        sb.append(", belong=").append(belong);
        sb.append(", collect=").append(collect);
        sb.append('}');
        return sb.toString();
    }

    public Belong getBelong() {
        return belong;
    }

    public void setBelong(Belong belong) {
        this.belong = belong;
    }

    public Collect getCollect() {
        return collect;
    }

    public void setCollect(Collect collect) {
        this.collect = collect;
    }

    public Integer getPackageid() {
        return packageid;
    }

    public void setPackageid(Integer packageid) {
        this.packageid = packageid;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * @return FileID
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
     * 获取资料名称
     *
     * @return FileName - 资料名称
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 设置资料名称
     *
     * @param filename 资料名称
     */
    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    /**
     * 获取资料地址
     *
     * @return FlieUrl - 资料地址
     */
    public String getFlieurl() {
        return flieurl;
    }

    /**
     * 设置资料地址
     *
     * @param flieurl 资料地址
     */
    public void setFlieurl(String flieurl) {
        this.flieurl = flieurl == null ? null : flieurl.trim();
    }

    /**
     * 获取资料类型
     *
     * @return FlieType - 资料类型
     */
    public String getFlietype() {
        return flietype;
    }

    /**
     * 设置资料类型
     *
     * @param flietype 资料类型
     */
    public void setFlietype(String flietype) {
        this.flietype = flietype == null ? null : flietype.trim();
    }

    /**
     * 获取资料说明
     *
     * @return FileRemark - 资料说明
     */
    public String getFileremark() {
        return fileremark;
    }

    /**
     * 设置资料说明
     *
     * @param fileremark 资料说明
     */
    public void setFileremark(String fileremark) {
        this.fileremark = fileremark == null ? null : fileremark.trim();
    }

    /**
     * 获取建立时间
     *
     * @return FileCreatTime - 建立时间
     */
    public Date getFilecreattime() {
        return filecreattime;
    }

    /**
     * 设置建立时间
     *
     * @param filecreattime 建立时间
     */
    public void setFilecreattime(Date filecreattime) {
        this.filecreattime = filecreattime;
    }

    /**
     * 获取删除时间
     *
     * @return FileDelTime - 删除时间
     */
    public Date getFiledeltime() {
        return filedeltime;
    }

    /**
     * 设置删除时间
     *
     * @param filedeltime 删除时间
     */
    public void setFiledeltime(Date filedeltime) {
        this.filedeltime = filedeltime;
    }
}