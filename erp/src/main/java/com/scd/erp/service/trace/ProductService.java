package com.scd.erp.service.trace;

import com.scd.erp.Vo.Product.Product;
import com.scd.erp.Vo.Product.Remark;
import com.scd.erp.Vo.Product.Serial;
import com.scd.erp.Vo.Product.Type;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Map getList(Integer row, Integer page);

    Integer setProduct(Product product);

    Integer SetRemark(Remark remark);

    Integer SetSerial(Serial serial);

    void sett(Type type);

    List<Type> getAllType();
}
