package com.scd.erp.user.review;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "erp_project_review_content")
public class Content implements Serializable {
    @Id
    private Integer tid;

    /**
     * 评审内容
     */
    private String content;
    /**
     * 评审记录
     */
    private String notes;

    /**
     * 评审结论
     */
    private String conclusion;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 会签
     */
    private String sign;

    private Integer rid;

    private List<ContentFile> contentFile;

    public List<ContentFile> getContentFile() {
        return contentFile;
    }

    public void setContentFile(List<ContentFile> contentFile) {
        this.contentFile = contentFile;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * 评审一对多关联
     */
    private Record record;

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    /**
     * @return tid
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * @param tid
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    /**
     * 获取评审内容
     *
     * @return content - 评审内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评审内容
     *
     * @param content 评审内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取评审记录
     *
     * @return record - 评审记录
     */
    public String getNotes() {
        return notes;
    }

    /**
     * 设置评审记录
     *
     * @param notes 评审记录
     */
    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    /**
     * 获取评审结论
     *
     * @return conclusion - 评审结论
     */
    public String getConclusion() {
        return conclusion;
    }

    /**
     * 设置评审结论
     *
     * @param conclusion 评审结论
     */
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion == null ? null : conclusion.trim();
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 获取会签
     *
     * @return sign - 会签
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设置会签
     *
     * @param sign 会签
     */
    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }
}