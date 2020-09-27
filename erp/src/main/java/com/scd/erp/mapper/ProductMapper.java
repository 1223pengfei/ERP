package com.scd.erp.mapper;

import com.scd.erp.Vo.Product.Product;
import com.scd.erp.utils.IBaseDao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper extends IBaseDao<Product> {

    List<Product> getAll(@Param("row") Integer row, @Param("page") Integer page);
}