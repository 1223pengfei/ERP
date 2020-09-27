package com.scd.erp.mapper;

import com.scd.erp.Vo.plm.Material.Stock;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockMapper extends IBaseDao<Stock> {
}