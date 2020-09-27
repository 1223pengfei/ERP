package com.scd.erp.controller.plm;

import com.scd.erp.Vo.Person.Person;
import com.scd.erp.Vo.Person.User;
import com.scd.erp.Vo.plm.Center.MaterialLink;
import com.scd.erp.Vo.plm.Material.BaseMaterial;
import com.scd.erp.Vo.plm.Material.Bom;
import com.scd.erp.mapper.BomMapper;
import com.scd.erp.mapper.MaterialLinkMapper;
import com.scd.erp.mapper.UserMapper;
import com.scd.erp.service.auth.PersonService;
import com.scd.erp.service.plm.MaterService;
import com.scd.erp.service.plm.MaterialLinkService;
import com.scd.erp.utils.common.ReMsgUtil;
import com.scd.erp.utils.extraUtil.ChainMap;
import com.scd.erp.utils.extraUtil.ConsoleMsg;
import com.scd.erp.utils.extraUtil.ExampleUtile;
import com.scd.erp.utils.extraUtil.LoginUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("plm_link")
public class LinkController {

    private final MaterialLinkService linkService;
    private final MaterService materService;
    private final PersonService personService;

    private final ReMsgUtil reMsgUtil;
    private final ExampleUtile exampleUtile;
    private final MaterialLinkMapper linkMapper;
    private final UserMapper userMapper;

    private LoginUtile loginUtile = new LoginUtile();

    @Autowired
    public LinkController(MaterialLinkService linkService, MaterService materService, PersonService personService, ReMsgUtil reMsgUtil, ExampleUtile exampleUtile, MaterialLinkMapper linkMapper, UserMapper userMapper, BomMapper bomMapper) {
        this.linkService = linkService;
        this.materService = materService;
        this.personService = personService;
        this.reMsgUtil = reMsgUtil;
        this.exampleUtile = exampleUtile;
        this.linkMapper = linkMapper;
        this.userMapper = userMapper;
        this.bomMapper = bomMapper;
    }

    @PostMapping("set/bom.do")
    public Map setBom(Bom bom){
        this.bomMapper.updateByPrimaryKey(bom);
        return reMsgUtil.msg("","ok",0);
    }

    @PostMapping("select/bomByBase.do")
    public Map selectBom(Integer baseid){
        Example lex = exampleUtile.getExample(MaterialLink.class, new ChainMap().putObj("mainbaseid", baseid));
        List<MaterialLink> links = this.linkMapper.selectByExample(lex);
        List<Bom> boms = new LinkedList<>();
        for (MaterialLink link:links) {
            Integer linkid = link.getMateriallinkid();
            Example ex = exampleUtile.getExample(Bom.class, new ChainMap().putObj("baseid", linkid));
            Bom bom = this.bomMapper.selectOneByExample(ex);
            Integer subbaseid = link.getSubbaseid();
            BaseMaterial all = this.materService.getBaseMaterial(subbaseid, "all", null, null);
            User user = this.userMapper.selectByPrimaryKey(bom.getCreatuserid());
            Person personAll = this.personService.getPersonAll(user.getPresonid());
            bom.setBaseMaterial(all);
            user.setPerson(personAll);
            bom.setCreatUser(user);
            boms.add(bom);
        }

        return reMsgUtil.msg(boms,"ok",0);
    }

    @PostMapping("select/material.do/{type}/{way}")
    public Map materialBylink(Integer baseid,
                              @PathVariable String type,
                              @PathVariable String way,
                              HttpSession session,
                              Integer offset,Integer page){
       // ConsoleMsg.log(baseid+">"+type+">"+offset+">"+page+">"+way);
        if (way.equals("main")||way.equals("sub")) {
            Integer depID = loginUtile.getDepID(session);
            if (offset != null && page != null) {
                offset = (offset - 1) * page;
            }
            BaseMaterial base = this.linkService.getMaterialByType(baseid, type, depID, offset, page, way);
            return reMsgUtil.msg(base, "ok", 0);
        }else {
            return reMsgUtil.msg("", "error whit way", 2);
        }
    }

    private final BomMapper bomMapper;

    @PostMapping("base_Link.do")
    public Map materialLink(MaterialLink link,Bom bom,HttpSession session){
        if (this.linkService.link(link)){
            bom.setBaseid(link.getMateriallinkid());
            bom.setCreattime(new Date());
            bom.setCreatuserid(loginUtile.getUser(session).getUserid());
            this.bomMapper.insertSelective(bom);
            ConsoleMsg.log(link);
            return reMsgUtil.msg(link,"ok",0);
        }else {
            return reMsgUtil.msg(link,"is repeat",2);
        }

    }

    @PostMapping("base_deLink.do/{type}")
    public Map materialDelink(Integer linkid , @PathVariable String type){
        this.linkService.delink(linkid,type);
        Example baseid = exampleUtile.getExample(Bom.class, new ChainMap().putObj("baseid", linkid));
        this.bomMapper.deleteByExample(baseid);
        return reMsgUtil.msg("","ok",0);
    }
}
