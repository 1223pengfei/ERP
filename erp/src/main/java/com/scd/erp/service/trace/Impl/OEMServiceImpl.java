package com.scd.erp.service.trace.Impl;

import com.scd.erp.Vo.Alarm.OEM;
import com.scd.erp.mapper.OEMMapper;
import com.scd.erp.service.trace.OEMServic;
import com.scd.erp.utils.extraUtil.ChainMap;
import com.scd.erp.utils.extraUtil.ExampleUtile;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class OEMServiceImpl implements OEMServic {
    private final OEMMapper oemMapper;

    private ExampleUtile eu ;

    @Autowired
    public OEMServiceImpl(OEMMapper oemMapper) {
        this.oemMapper = oemMapper;
        this.eu = new ExampleUtile();
    }

    @Override
    public Map getList(Integer row, Integer page) {
        Map map = new LinkedHashMap();
        List<OEM> oems;
        if (row != null) {
            page = (page - 1) * row;
            Example ex = eu.getExample(OEM.class);
            ex.setOrderByClause("convert(oemname using gbk)");
            RowBounds rb = new RowBounds(page,row);
            oems = this.oemMapper.selectByExampleAndRowBounds(ex, rb);
            int i = this.oemMapper.selectCount(null);
            map.put("count", i);
        } else {
            oems = this.oemMapper.selectAll();
        }
        map.put("data", oems);
        return map;
    }

    @Override
    public void set(OEM oem) {
        Integer oemid = oem.getOemid();
        Example ex = eu.getExample(OEM.class, new ChainMap().putObj("oemname", oem.getOemname()));
        OEM o = this.oemMapper.selectOneByExample(ex);
        if (oemid != null || o != null ) {
            oem.setOemid(o.getOemid());
            this.oemMapper.updateByPrimaryKey(oem);
        } else {
            this.oemMapper.insertSelective(oem);

        }

    }
}
