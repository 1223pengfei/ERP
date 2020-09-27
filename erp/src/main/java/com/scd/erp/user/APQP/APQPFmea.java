package com.scd.erp.user.APQP;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Table(name = "erp_apqp_fmea")
public class APQPFmea {
    @Id
    private Integer id;

    private String organization;

    private String examine;

    private String standardization;

    private String approval;

    private String opinion;

    private String reviewer;

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    private List<APQPFile> apqpFiles;

    public List<APQPFile> getApqpFiles() {
        return apqpFiles;
    }

    public void setApqpFiles(List<APQPFile> apqpFiles) {
        this.apqpFiles = apqpFiles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    public String getStandardization() {
        return standardization;
    }

    public void setStandardization(String standardization) {
        this.standardization = standardization;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }
}
