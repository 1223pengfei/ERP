package com.scd.erp.service.plm.Impl;

import com.scd.erp.Vo.plm.Center.MaterialLink;
import com.scd.erp.Vo.plm.Material.BaseMaterial;
import com.scd.erp.mapper.MaterialLinkMapper;
import com.scd.erp.service.plm.MaterService;
import com.scd.erp.service.plm.MaterialLinkService;
import com.scd.erp.utils.extraUtil.ChainMap;
import com.scd.erp.utils.extraUtil.ExampleUtile;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.LinkedList;
import java.util.List;

@Service
public class IMaterialLinkService implements MaterialLinkService {

    @Autowired
    private MaterialLinkMapper linkMapper;
    @Autowired
    private ExampleUtile exampleUtile;
    @Autowired
    private MaterService materService;


    @Override
    public boolean link(MaterialLink link) {

        List<MaterialLink> links = this.linkMapper.selectByExample(
                exampleUtile.getExample(MaterialLink.class, new ChainMap()
                        .putObj("mainbaseid", link.getMainbaseid()).putObj("subbaseid", link.getSubbaseid()))
        );
        if (links.size()==0)
            this.linkMapper.insertSelective(link);
        return links.size()==0;


    }

    public  void delink(Integer linkid){
        delink(linkid,"main");
        delink(linkid,"sub");
    }

    @Override
    public void delink(Integer linkid, String type) {
        String key = getType(type);
        Example example = exampleUtile.getExample(MaterialLink.class,new ChainMap().putObj(key,linkid));
        this.linkMapper.deleteByExample(example);
    }

    private String getType(String type) {
        String key = "";
        switch (type){
            case "base":
                key = "materiallinkid";
                break;
            case "main":
                key = "mainbaseid";
                break;
            case "sub":
                key = "subbaseid";
                break;
        }
        return key;
    }

    @Override
    public BaseMaterial getMaterialByType(Integer baseid, String type, Integer depID, Integer offset, Integer page, String way) {
        BaseMaterial base = this.materService.getBaseMaterial(baseid, type, depID, null);
        Example ex = exampleUtile.getExample(MaterialLink.class, new ChainMap().putObj(way+"baseid", baseid));
        int i = this.linkMapper.selectCountByExample(ex);
        base.setCount(i);
        List<MaterialLink> links = this.linkMapper.selectByExampleAndRowBounds(ex, new RowBounds(offset, page));
        List<BaseMaterial> baselist = new LinkedList<>();
        for (MaterialLink link :links) {
            //ConsoleMsg.log(link.getSubbaseid());
            BaseMaterial bm = this.materService.getBaseMaterial(way.equals("main") ? link.getSubbaseid() : link.getMainbaseid(),
                    type, depID, null);
            bm.setLink(link);
            baselist.add(bm);
        }
        base.setBaseMaterials(baselist);
        return base;
    }

    @Override
    public List<MaterialLink> getLink(Integer id, String condi) {
        String key = getType(condi);
        Example example = exampleUtile.getExample(MaterialLink.class,new ChainMap().putObj(key,id));
        List<MaterialLink> links = this.linkMapper.selectByExample(example);
        return links;
    }
}
