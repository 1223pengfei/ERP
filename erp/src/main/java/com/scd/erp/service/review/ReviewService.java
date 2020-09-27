package com.scd.erp.service.review;

import com.scd.erp.user.review.Content;
import com.scd.erp.user.review.Record;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    Map addReviewMsg(Content contents, String ids, Record record, String personIDs,String rfids);

    List<Record> selectRecordMg(Integer personID, Integer rid,Integer pid);

    Boolean deleteRecordMg(Integer rid,String rfids);

    Content addContentMsg(Content contents);
}
