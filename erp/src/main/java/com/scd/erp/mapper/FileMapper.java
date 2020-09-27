package com.scd.erp.mapper;

import com.scd.erp.user.review.ContentFile;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface FileMapper extends Mapper<ContentFile> {
    void saveFileMsg(ContentFile contentFile);

    void saveContentIDs(@Param("tid") int tid, @Param("fid")int fid);
}