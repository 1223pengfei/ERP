package com.scd.erp.mapper;

import com.scd.erp.Vo.plm.Document.Document;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DocumentMapper extends IBaseDao<Document> {
    List<String> getDocNum();
}