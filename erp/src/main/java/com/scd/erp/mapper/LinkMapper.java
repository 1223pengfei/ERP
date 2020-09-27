package com.scd.erp.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LinkMapper {
    @Insert("insert into erp_center_person_job (jobID,pesonID) values(#{jobid},#{personid})")
    void job_person(@Param("jobid") Integer jobid, @Param("personid") Integer personid);

    @Insert("insert into erp_center_file_package (fileID,packageID) values(#{fileid},#{paid})")
    void package_doc(@Param("paid") Integer paid, @Param("fileid") Integer fileid);


    @Insert("insert into erp_center_job_rule (jobID,ruleID) values(#{jobid},#{ruleid})")
    void job_rule(@Param("jobid") Integer jobid, @Param("ruleid") int ruleid);

    @Insert("insert into erp.erp_center_file_collect (collectID,fileID) values(#{collectid},#{docid})")
    void file_collect(@Param("docid") Integer docid, @Param("collectid") Integer collectid);


    @Delete("delete from erp.erp_center_file_collect where collectID = #{collectid} and fileID = #{docid}")
    void dis_file_collect(@Param("docid") Integer docid, @Param("collectid") Integer collectid);

    @Insert("insert into erp_center_rule_persission (rulID,persID) values(#{ruleid},#{persid})")
    void rule_premission(@Param("persid") Integer perid, @Param("ruleid") Integer ruleid);

    @Delete("delete from erp_center_job_rule where jobID = #{jobid} and ruleID = #{ruleid}")
    void dis_job_rule(@Param("jobid") Integer jobid, @Param("ruleid") String ruleid);

    @Delete("delete from erp_center_rule_persissione where persID = #{persid} and ruleID = #{ruleid}")
    void dis_rule_premission(@Param("persid") Integer perid, @Param("ruleid") Integer ruleid);
}
