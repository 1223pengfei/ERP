package com.scd.erp.controller.track;

import com.scd.erp.Vo.Product.Product;
import com.scd.erp.Vo.Product.Remark;
import com.scd.erp.Vo.Product.Serial;
import com.scd.erp.Vo.Product.Type;
import com.scd.erp.service.trace.ProductService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("product")
public class productContro {
    @Autowired
    private ProductService productService;

    @RequiresPermissions("select:alarm")
    @PostMapping("List.do")
    @ResponseBody
    public Map getList(@Param("row") Integer row, @Param("page") Integer page) {
        //System.out.println(row+""+page);
        return this.productService.getList(row, page);
    }

    @RequiresPermissions("set:type")
    @PostMapping("typeSet.do")
    @ResponseBody
    public List<Type> sett(Type type) {
        this.productService.sett(type);
        return listt();
    }

    @RequiresPermissions("add:alarm")
    @PostMapping("typeList.do")
    @ResponseBody
    public List<Type> listt() {
        return this.productService.getAllType();
    }

    @PostMapping("Set.do")
    @ResponseBody
    public Map Set(Product product, Remark remark, Serial serial) {

        int pid = this.productService.setProduct(product);

        return this.getList(0, 10);
    }

    @RequiresPermissions("add:alarm")
    @PostMapping("SetOne.do")
    @ResponseBody
    public Integer sOne(Product product, Remark remark, Serial serial) {
        remark.setProductid(product.getProductid());
        int rid = this.productService.SetRemark(remark);
        //System.out.println(rid);
        serial.setRemarkid(rid);
        return this.productService.SetSerial(serial);
    }


}
