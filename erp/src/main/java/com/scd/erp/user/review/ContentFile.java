package com.scd.erp.user.review;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "erp_project_content_file")
public class ContentFile implements Serializable {
    @Id
    private Integer rfid;

    /**
     * 资料名称
     */
    @Column(name = "FileName")
    private String filename;

    /**
     * 评审文件地址
     */
    private String url;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    /**
     * @return rfid
     */
    public Integer getRfid() {
        return rfid;
    }

    /**
     * @param rfid
     */
    public void setRfid(Integer rfid) {
        this.rfid = rfid;
    }

    /**
     * 获取评审文件地址
     *
     * @return url - 评审文件地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置评审文件地址
     *
     * @param url 评审文件地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}