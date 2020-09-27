package com.scd.erp.service.trace.Impl;

import com.scd.erp.Vo.Product.Product;
import com.scd.erp.Vo.Product.Remark;
import com.scd.erp.Vo.Product.Serial;
import com.scd.erp.Vo.Product.Type;
import com.scd.erp.mapper.ProductMapper;
import com.scd.erp.mapper.RemarkMapper;
import com.scd.erp.mapper.SerialMapper;
import com.scd.erp.mapper.TypeMapper;
import com.scd.erp.service.trace.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class productServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final RemarkMapper remarkMapper;
    private final SerialMapper serialMapper;
    private final TypeMapper typeMapper;

    @Autowired
    public productServiceImpl(ProductMapper productMapper, RemarkMapper remarkMapper, SerialMapper serialMapper, TypeMapper typeMapper) {
        this.productMapper = productMapper;
        this.remarkMapper = remarkMapper;
        this.serialMapper = serialMapper;
        this.typeMapper = typeMapper;
    }

    @Override
    public Map getList(Integer row, Integer page) {
        Map map = new LinkedHashMap();
        List<Product> all;
        if (row != null) {
            page = (page - 1) * row;
            all = this.productMapper.getAll(row, page);
            int i = this.productMapper.selectCount(null);
            map.put("count", i);
        } else {
            all = this.productMapper.selectAll();
        }

        map.put("data", all);
        return map;
    }

    @Override
    public Integer setProduct(Product product) {
        Integer productid = product.getProductid();
        if (productid == null) {
            this.productMapper.insertSelective(product);
            productid = product.getProductid();
        } else {
            this.productMapper.updateByPrimaryKey(product);
        }
        return productid;
    }

    @Override
    public Integer SetRemark(Remark remark) {
        Integer remarkid = remark.getRemarkid();
        if (remarkid == null) {
            this.remarkMapper.insertSelective(remark);
            remarkid = remark.getRemarkid();
        } else {
            this.remarkMapper.updateByPrimaryKey(remark);
        }
        return remarkid;
    }

    @Override
    public Integer SetSerial(Serial serial) {
        //System.out.println(serial);
        Integer serialid = serial.getSerialid();
        if (serialid == null) {
            this.serialMapper.insertSelective(serial);
            serialid = serial.getSerialid();
        } else {
            this.serialMapper.updateByPrimaryKey(serial);
        }
        return serialid;
    }

    @Override
    public void sett(Type type) {
        Integer typeid = type.getTypeid();
        if (typeid != null) {
            this.typeMapper.updateByPrimaryKey(type);
        } else {
            this.typeMapper.insertSelective(type);
        }
    }

    @Override
    public List<Type> getAllType() {
        return this.typeMapper.selectAll();
    }
}
