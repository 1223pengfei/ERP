package com.scd.erp.controller.track;

import com.scd.erp.Vo.Alarm.OEM;
import com.scd.erp.service.trace.OEMServic;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("oem")
public class oemContro {

    private final OEMServic oemServic;

    @Autowired
    public oemContro(OEMServic oemServic) {
        this.oemServic = oemServic;
    }


    @RequiresPermissions("add:alarm")
    @PostMapping("List.do")
    @ResponseBody
    public Map getList(@Param("row") Integer row, @Param("page") Integer page) {

        return oemServic.getList(row, page);
    }


    @RequiresPermissions("add:oem")
    @PostMapping("Set.do")
    @ResponseBody
    public Map set(OEM oem) {

        this.oemServic.set(oem);

        return this.getList(null, null);
    }
}
