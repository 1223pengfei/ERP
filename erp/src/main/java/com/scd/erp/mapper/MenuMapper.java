package com.scd.erp.mapper;

import com.scd.erp.Vo.plm.Document.Menu;
import com.scd.erp.utils.IBaseDao.IBaseDao;

import java.util.List;

public interface MenuMapper extends IBaseDao<Menu> {
    List getAll();
}