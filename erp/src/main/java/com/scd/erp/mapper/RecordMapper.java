package com.scd.erp.mapper;

import com.scd.erp.user.review.Record;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface RecordMapper extends Mapper<Record> {

    boolean addRecordMsg(Record record);

    Boolean saveID(@Param("rid") int rid, @Param("personID")Integer personID);

    Boolean saveRecordItemsID(@Param("rid")int rid, @Param("iid")int iid);

    List<Record> selectRecordMg(@Param("personID")Integer personID, @Param("rid")Integer rid,@Param("pid")Integer pid);

    boolean updateRecordMsg(Record record);

    void delRecordItemsID(@Param("rid")int rid, @Param("iid")int iid);

    Integer selectRid(String pname);

    void updateID(@Param("rid")Integer rid, @Param("personID")Integer personID);

    int deletePersonReviewRecord(Integer rid);

    int deleteProjectRecordItem(Integer rid);

    void deleteID(@Param("recordid")Integer recordid, @Param("personID")int personID);
}