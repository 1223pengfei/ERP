package com.scd.erp.mapper;

import com.scd.erp.Vo.plm.Note.Note;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoteMapper extends IBaseDao<Note> {
}