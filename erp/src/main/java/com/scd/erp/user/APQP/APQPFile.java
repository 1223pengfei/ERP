package com.scd.erp.user.APQP;

import javax.persistence.Table;

@Table(name = "erp_apqp_file")
public class APQPFile {

    private Integer fid;

    private String apqp_file_url;

    private String apqp_file_name;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getApqp_file_url() {
        return apqp_file_url;
    }

    public void setApqp_file_url(String apqp_file_url) {
        this.apqp_file_url = apqp_file_url;
    }

    public String getApqp_file_name() {
        return apqp_file_name;
    }

    public void setApqp_file_name(String apqp_file_name) {
        this.apqp_file_name = apqp_file_name;
    }
}
