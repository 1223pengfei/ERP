package com.scd.erp.controller.plm;

import com.scd.erp.Vo.Person.User;
import com.scd.erp.Vo.ToExcel.MaterailToExcel;
import com.scd.erp.Vo.commen.Condition;
import com.scd.erp.Vo.plm.Material.BaseMaterial;
import com.scd.erp.Vo.plm.Material.Bom;
import com.scd.erp.Vo.plm.Material.Stock;
import com.scd.erp.Vo.plm.Note.DelMsg;
import com.scd.erp.Vo.plm.Note.Note;
import com.scd.erp.Vo.plm.Note.Renew;
import com.scd.erp.constant.ConstantMaterials;
import com.scd.erp.mapper.*;
import com.scd.erp.service.plm.MaterService;
import com.scd.erp.utils.common.JsonUtil;
import com.scd.erp.utils.common.ReMsgUtil;
import com.scd.erp.utils.extraUtil.ChainMap;
import com.scd.erp.utils.extraUtil.ConsoleMsg;
import com.scd.erp.utils.extraUtil.ExampleUtile;
import com.scd.erp.utils.extraUtil.LoginUtile;
import com.scd.erp.utils.plm.numberUtil;
import com.scd.erp.utils.trace.easyPoiUtil;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("plm")
public class MaterialController {

    private static final Logger logger = LoggerFactory.getLogger(MaterialController.class);


    private LoginUtile loginUtile = new LoginUtile();
    private final MaterService materService;
    private final ReMsgUtil reMsgUtil;
    private final ExampleUtile exampleUtile;
    private final numberUtil numberUtil;


    private final GroupMapper groupMapper;
    private final BasePackageMapper basePackageMapper;
    private final WareroomMapper wareroomMapper;
    private final BaseMaterialMapper materialMapper;
    private final TypeMapper typeMapper;

    @Autowired
    public MaterialController(MaterService materService, ReMsgUtil reMsgUtil, ExampleUtile exampleUtile, numberUtil numberUtil, GroupMapper groupMapper, BasePackageMapper basePackageMapper, WareroomMapper wareroomMapper, BaseMaterialMapper materialMapper, TypeMapper typeMapper) {
        this.materService = materService;
        this.reMsgUtil = reMsgUtil;
        this.exampleUtile = exampleUtile;
        this.numberUtil = numberUtil;
        this.groupMapper = groupMapper;
        this.basePackageMapper = basePackageMapper;
        this.wareroomMapper = wareroomMapper;
        this.materialMapper = materialMapper;
        this.typeMapper = typeMapper;
    }

    @PostMapping("down/MaterailToExcle.do")
    public void down(HttpServletResponse rep,Integer baseid){
        if (null != baseid){
            BaseMaterial material = this.materService.toExcle(baseid);
            List<Bom> boms = material.getBoms();
            List<MaterailToExcel> excels = new LinkedList<>();
            int i = 0;
            String namecn = material.getNamecn();
            for (Bom bom:boms) {
                BaseMaterial mat = bom.getBaseMaterial();
                MaterailToExcel exc = new MaterailToExcel();
               exc.setNum(i++);
               exc.setAmount(bom.getAmount());
               exc.setCustomerpn(mat.getCustomerpn());
               exc.setNamecn(mat.getNamecn());
               exc.setRemark(mat.getRemark());
               exc.setTag(bom.getTag());
               exc.setUnit(bom.getUnit());
               StringBuffer cs = null,ms = null;
                for (int x = 0 ; x <mat.getStocks().size();x++ ) {
                    Stock st= mat.getStocks().get(x);
                    cs.append(st.getManufacturer()).append("_");
                    ms.append(st.getManufacturerpartnumber()).append("_");
                }
               exc.setCompanys(Arrays.asList(cs != null ? cs.toString().split("_") : new String[0]));
                exc.setManufacturerpartnumber(Arrays.asList(ms != null ? ms.toString().split("_") : new String[0]));
                exc.setBaba(namecn);
               excels.add(exc);
            }
            String title = namecn +"总成零件清单("+material.getVersion()+")";
            String fileName = namecn + "零件清单表";
            easyPoiUtil.exportExcel(excels, title,"shit1",MaterailToExcel.class,
                    fileName,true,rep);
        }

    }

    /**
     * @param baseid 主ID
     * @param session session
     * @param type  类型
     * @return  map
     */
    @PostMapping("copy/baseMaterail.do")
    public Map copyBase(Integer baseid,HttpSession session,Integer type){
        //ConsoleMsg.debug(baseid);
        if (null!=baseid){
            Integer userid = loginUtile.getUser(session).getUserid();
            BaseMaterial base = this.materService.copyBase(baseid,userid,type);
           return reMsgUtil.msg(base,"ok",0);
        }
        return reMsgUtil.msg(baseid,"id is null",1);
    }


    @PostMapping("get/renew.do")
    public Map getRenew(Integer renewid){
        Map map = this.materService.getRenew(renewid);
        return reMsgUtil.msg(map,"ok",0);
    }

    @PostMapping("get/note.do")
    public Map selectNote(Integer materailid){
       Note note = this.materService.getNote(materailid);
        return reMsgUtil.msg(note,"ok",0);
    }

    @PostMapping("set/{type}.do")
    public Map setAnything(@PathVariable String type,
                           @RequestParam Map<String, Object> params,
                           HttpSession session){
        User user = loginUtile.getUser(session);
        this.materService.setAnything(params, newRenew(user, type));
        return reMsgUtil.msg("","ok",0);
    }

    @PostMapping("del/material.do")
    public Map deleteMaterial(Integer materailid, DelMsg delMsg,HttpSession session){
        logger.info(materailid+"");
        logger.info(delMsg.toString());
        delMsg.setDeluserid(loginUtile.getUser(session).getUserid());
        delMsg.setDeltime(new Date());
        this.materService.deleteMaterial(materailid,delMsg);
        return reMsgUtil.msg("","ok",0);
    }

    @PostMapping("get/{type}.do")
    public Map selectByAnything(Integer materailid,HttpSession session,
                                @PathVariable String type,String condition){
       Integer depID = loginUtile.getDepID(session);
        BaseMaterial material = this.materService.getBaseMaterial(materailid,type,depID,condition);
        if (null != material) {
            logger.debug(material.toString());
            return reMsgUtil.msg(material, "ok", 0);
        }else{
            return reMsgUtil.msg(null, "material is null", 1);
        }
    }

    @PostMapping("find/byCondition.do")
    public Map findCondtion(String conditions){
        logger.info("condition:"+conditions);
        if (null != conditions && !conditions.equals("[{}]")) {
            try {
                List<Condition> newCondis = JsonUtil.StringToVoList(conditions, Condition.class);
                if(newCondis.size()>0) {
                    return reMsgUtil.msg(this.materService.findCondition(newCondis)
                            , "ok", 0);
                }else{
                    return reMsgUtil.msg("","conditions legnth is 0!!!!!",1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
           return reMsgUtil.msg("","conditions is null!!!!!",1);
            }

    @PostMapping("select/allBase.do")
    public Map selectAllBase(Integer offset,Integer page){
        if (offset != null && page != null) {
            offset = (offset - 1) * page;
            RowBounds rowBounds = new RowBounds(offset,page);
            Example example = exampleUtile.getExample(BaseMaterial.class);
            example.orderBy("baseid").desc();
            return reMsgUtil.msg(new ChainMap<>()
                            .putObj("count",this.materialMapper.selectCountByExample(example))
                            .putObj("base",this.materialMapper.selectByExampleAndRowBounds(example, rowBounds)),
                    "ok",0);

        }
        return  null;
    }

    @GetMapping("select/baseMsg.do")
    public Map selectBaseMsg(){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("P",ConstantMaterials.MATERIAL_P);
        map.put("M",ConstantMaterials.MATERIAL_M);
        map.put("clazz",ConstantMaterials.MATERIAL_CLAZZ);
        map.put("subClazz",ConstantMaterials.MATERIAL_SUBCATEGORY);
        map.put("group",this.groupMapper.selectAll());
        map.put("type",this.typeMapper.selectAll());
        map.put("selectTree",ConstantMaterials.MaterailConditions);
        return reMsgUtil.msg(map,"ok",0);
    }

    /**
     * @return map
     */
    @GetMapping("select/logisticMsg.do")
    public Map selectLogisticMsg(){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("packages",this.basePackageMapper.selectPackageSelf());
        map.put("warerooms",this.wareroomMapper.selectAll());
        map.put("unit",ConstantMaterials.UNIT);
        return reMsgUtil.msg(map,"ok",0);
    }


    @PostMapping("add/baseMaterial.do")
    public Map addMaterial(HttpSession session, BaseMaterial material,String prefix){

        ConsoleMsg.log(material);
        ConsoleMsg.log(prefix);
        User user = loginUtile.getUser(session);
        Note note = new Note();
        note.setCreattime(new Date());
        note.setCreatuserid(user.getUserid());
        note.setState("0");
            if (null == material.getErpnum()) {
                material.setErpnum(numberUtil.setERPnum(prefix));
            }
            material.setVersion(numberUtil.setVersion(material.getErpnum()));
            this.materService.addBaseMaterial(material, note);
            return reMsgUtil.msg(material, "ok", 0);

    }


    @PostMapping("adds/{type}.do")
    public Map addOther(HttpSession session,
                        @PathVariable String type,
                        @RequestParam Map<String, Object> params,
                        Integer materialID){

        if(this.materService.hasMsg(materialID, type)) {
            return reMsgUtil.msg("", "the type is had", 2);
        }

        User user = loginUtile.getUser(session);
        if (!params.isEmpty()) {
            this.materService.add(params, newRenew(user, type), materialID);
            return reMsgUtil.msg(
                    this.materService.getBaseMaterial(materialID,type,null, null),
                    "ok", 0);
        }
        return reMsgUtil.msg("","error",1);
    }




    private Renew newRenew(User user,String type) {
        Renew renew = new Renew();
        renew.setRenewtype(type);
        renew.setRenewuserid(user.getUserid());
        renew.setRenewtime(new Date());
        return renew;
    }



}
