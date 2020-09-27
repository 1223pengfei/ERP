package com.scd.erp.mapper;

import com.scd.erp.user.review.Content;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface ContentMapper extends Mapper<Content> {
    boolean saveContent(Content content);

    Boolean updateContent(Content contents);

    int deleteReviewContent(Integer rid);

    Content selectContentByRid(Integer rid);
}