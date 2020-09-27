package com.scd.erp.controller.plm;


import com.scd.erp.Vo.Person.User;
import com.scd.erp.Vo.commen.Condition;
import com.scd.erp.Vo.plm.Center.documentLink;
import com.scd.erp.Vo.plm.Document.*;
import com.scd.erp.constant.ConstantMaterials;
import com.scd.erp.mapper.*;
import com.scd.erp.service.plm.DocumentService;
import com.scd.erp.utils.common.FileUtils;
import com.scd.erp.utils.common.JsonUtil;
import com.scd.erp.utils.common.ReMsgUtil;
import com.scd.erp.utils.common.RedisUtils;
import com.scd.erp.utils.extraUtil.ChainMap;
import com.scd.erp.utils.extraUtil.ConsoleMsg;
import com.scd.erp.utils.extraUtil.ExampleUtile;
import com.scd.erp.utils.extraUtil.LoginUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RequestMapping("plm_file")
@RestController
public class FileController {

    private final ReMsgUtil reMsgUtil;
    private final RedisUtils redisUtils;
    private final ExampleUtile exampleUtile;


    private final DocumentService documentService;

    private final DocumentMapper documentMapper;
    private final documentLinkMapper linkMapper;
    private final VersionMapper versionMapper;
    private final MenuMapper menuMapper;
    private final KeepMapper keepMapper;
    private final KeepDetailMapper keepDetailMapper;
    private final AnnotationMapper annotationMapper;

    private LoginUtile loginUtile = new LoginUtile();

    private final FileUtils utils;

    @Autowired
    public FileController(ReMsgUtil reMsgUtil, RedisUtils redisUtils, ExampleUtile exampleUtile, DocumentService documentService, DocumentMapper documentMapper, documentLinkMapper linkMapper, VersionMapper versionMapper, FileUtils utils, MenuMapper menuMapper, KeepMapper keepMapper, KeepDetailMapper keepDetailMapper, AnnotationMapper annotationMapper) {
        this.reMsgUtil = reMsgUtil;
        this.redisUtils = redisUtils;
        this.exampleUtile = exampleUtile;
        this.documentService = documentService;
        this.documentMapper = documentMapper;
        this.linkMapper = linkMapper;
        this.versionMapper = versionMapper;
        this.utils = utils;
        this.menuMapper = menuMapper;
        this.keepMapper = keepMapper;
        this.keepDetailMapper = keepDetailMapper;
        this.annotationMapper = annotationMapper;
    }

    @GetMapping("get/conditionMap.do")
    public Map getMap(){
       return reMsgUtil.msg(ConstantMaterials.DocmentConditions,"ok",0);
    }

    @PostMapping("find/doc/byconditions.do")
    public Map findByConditions(String conditions){
        ConsoleMsg.log("condition:"+conditions);
        if (null != conditions) {
            try {
                List<Condition> newCondis = JsonUtil.StringToVoList(conditions, Condition.class);
                if(newCondis.size()>0) {
                   List<Document> docs = this.documentService.findCondition(newCondis);
                    return reMsgUtil.msg( docs
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

    //=================================================================================================

    @GetMapping("select/AllAnnotation.do")
    public Map selectAll(){
        List<Annotation> annotations = this.annotationMapper.selectAll();
        return reMsgUtil.msg(annotations,"ok",0);
    }
    @GetMapping("get/byVer.do/{verid}")
    public Map getByVer(@PathVariable Integer verid){
        Example ex = exampleUtile.getExample(Annotation.class, new ChainMap().putObj("versionid", verid));
        List<Annotation> annotations = this.annotationMapper.selectByExample(ex);
        return reMsgUtil.msg(annotations,"ok",0);
    }

    @GetMapping("get/myself.do")
    public Map getMyself(HttpSession session){
        User user = loginUtile.getUser(session);
        Example ex = exampleUtile.getExample(Annotation.class, new ChainMap().putObj("annuserid", user.getUserid()));
        List<Annotation> annotations = this.annotationMapper.selectByExample(ex);
        return reMsgUtil.msg(annotations,"ok",0);
    }

    @PostMapping("delete/annotation.do")
    public Map delAnnotation(Integer anid){
         this.annotationMapper.deleteByPrimaryKey(anid);
        return reMsgUtil.msg("","ok",0);
    }

    @PostMapping("add/annotation.do")
    public Map addAnnotation(HttpSession session,Annotation an){
        User user = loginUtile.getUser(session);
        an.setAnnuserid(user.getUserid());
        an.setCreattime(new Date());
        this.annotationMapper.insertSelective(an);
        return reMsgUtil.msg(an,"ok",0);
    }

    @PostMapping("set/verState.do")
    public Map setVerState(Version ver){
        this.versionMapper.updateByPrimaryKey(ver);
        return  reMsgUtil.msg("","ok",0);
    }
//=============================================================================================
    @PostMapping("cancel/doc/keep.do")
    public Map cancelDocKeep(KeepDetail keepDetail,HttpSession session){
        if (null != keepDetail.getDocid() && null != keepDetail.getKeepid()){

            this.keepDetailMapper.delete(keepDetail);
            return reMsgUtil.msg(getKeeps(session),"ok",2);
        }
       return reMsgUtil.msg(keepDetail,"id is null",1);
    }

    @PostMapping("set/docToKeep.do")
    public Map setDocToKeep(KeepDetail keepDetail,HttpSession session){
        if (null!=keepDetail.getDocid() && null != keepDetail.getKeepid()) {
            List<KeepDetail> details = this.keepDetailMapper.select(keepDetail);
            if (details == null || details.size() == 0) {
                this.keepDetailMapper.insertSelective(keepDetail);
                return reMsgUtil.msg(getKeeps(session), "ok", 0);
            }else{
                return reMsgUtil.msg("","this is repeat",2);
            }
        }
         return reMsgUtil.msg(keepDetail,"have null",1);
    }

    @GetMapping("select/myKeep.do")
    public Map selectKeep(HttpSession session){
        List<Keep> keeps = getKeeps(session);
        return reMsgUtil.msg(keeps,"ok",0);
    }

    private List<Keep> getKeeps(HttpSession session) {
        Integer userid = loginUtile.getUser(session).getUserid();
        return this.documentService.getKeeps(userid);
    }

    @PostMapping("creat/docKeep.do")
    public Map creatDocKeep(HttpSession session,String keepname){
        Integer userid = loginUtile.getUser(session).getUserid();
        if (null != keepname) {
            Keep keep = new Keep();
            keep.setCreattime(new Date());
            keep.setKeepname(keepname);
            keep.setUserid(userid);
            this.keepMapper.insertSelective(keep);
            return reMsgUtil.msg(getKeeps(session),"ok",0);
        }
        return reMsgUtil.msg("","the keep is 少内容",1);
    }

    @GetMapping("select/menu/doc.do/{menuid}")
    public Map getDocWithMenu(@PathVariable Integer menuid){
        if (null != menuid){
            Menu menu = this.documentService.getDocWithMenu(menuid);
            return reMsgUtil.msg(menu,"ok",0);
        }
        return reMsgUtil.msg(menuid,"menuid is null",1);
    }


    @GetMapping("download/docAllversion.do/{docid}")
    public void downDocAllVersion(@PathVariable Integer docid, HttpServletResponse resp){
        ConsoleMsg.log("down all:"+docid);
        if (null != docid) {
            Document doc = this.documentMapper.selectByPrimaryKey(docid);
            Example ex = this.exampleUtile.getExample(Version.class, new ChainMap().putObj("docid", docid));
            List<Version> list = this.versionMapper.selectByExample(ex);
            for (Version v : list
                    ) {
                utils.download(doc.getDocname() + "_" + v.getVersioncode()+"." +v.getSuffix(), "static/" + v.getDocurl(), resp);
            }
        }

    }

    @GetMapping("download/doc.do/{verid}")
    public void downDoc(@PathVariable Integer verid, HttpServletResponse resp){
        ConsoleMsg.log("down ver:"+verid);
        if (null != verid) {
            Version ver = this.versionMapper.selectByPrimaryKey(verid);
            Document doc = this.documentMapper.selectByPrimaryKey(ver.getDocid());

            utils.download(doc.getDocname()+"." +ver.getSuffix(), "static/" + ver.getDocurl(), resp);
        }
    }

    @PostMapping("del/doc.do")
    public Map deleteDoc(Integer docid){
        ConsoleMsg.log("delete :"+docid);
        if (null != docid) {
            Example exl = exampleUtile.getExample(documentLink.class, new ChainMap().putObj("documentid", docid));
            this.linkMapper.deleteByExample(exl);
            Example exv = exampleUtile.getExample(Version.class, new ChainMap().putObj("docid", docid));
            this.versionMapper.deleteByExample(exv);
            this.documentMapper.deleteByPrimaryKey(docid);
            return reMsgUtil.msg("", "ok", 0);
        }
        return  reMsgUtil.msg("","docid is null",1);
    }


    @GetMapping("select/msg.do")
    public Map seletMsg(){
        Map map = new LinkedHashMap();
       // map.put("fileZone",ConstantMaterials.MATER_FILE_ZONE);
       // map.put("filetype",ConstantMaterials.MATER_FILE_TYPE);
        //map.put("filephase",ConstantMaterials.MATER_FILE_PHASE);
        map.put("menu",redisUtils.lGet("menu", 0, -1, Menu.class));
        return reMsgUtil.msg(map,"ok",0);
    }

    @GetMapping("get/menu/{id}")
    public Map getMenu(@PathVariable Integer id){
        return  reMsgUtil.msg(this.menuMapper.selectByPrimaryKey(id),"ok",0);
    }

    @PostMapping("link/menu.do")
    public Map materialLinkDocunmet(Integer baseid,Integer docid){
        this.documentService.materialLinkDocunmet(docid,baseid) ;
        return reMsgUtil.msg("","ok",0) ;
    }

    @PostMapping("get/MaterialAllFile.do")
    public Map getAllFile(Integer baseid){
        //ConsoleMsg.log("baseid:"+baseid);
        Example ex = exampleUtile.getExample(documentLink.class,
                new ChainMap().putObj("materialid", baseid));

        List<documentLink> links = this.linkMapper.selectByExample(ex);
        List<Document> ds = new LinkedList<>();
        if (links.size()>0) {
            for (documentLink link : links) {
                Document d = this.documentMapper.selectByPrimaryKey(link.getDocumentid());
                Example vex = exampleUtile.getExample(Version.class,
                        new ChainMap().putObj("docid", d.getDocumentid()));
                vex.orderBy("versionid");
                d.setVersions(this.versionMapper.selectByExample(vex));
                ds.add(d);
            }

        }
        return reMsgUtil.msg(ds,"ok",0);
    }

    @PostMapping("add/file.do")
    public Map addFile(HttpServletRequest request,MultipartFile upfile,
                       Document doc,Integer baseid,String prefix){
        ConsoleMsg.log("file is empty:"+upfile.isEmpty());
        ConsoleMsg.log("file's name :"+ upfile.getOriginalFilename());
        ConsoleMsg.log("fileSize:"+upfile.getSize());
        ConsoleMsg.log("baseid :" + baseid);
        if (!upfile.isEmpty()){
            if (doc.getMenuid()==null||prefix==null)
                return reMsgUtil.msg("","menuid or prefix is null !!!",2);

            User user = loginUtile.getUser(request);
            doc.setCreatuserid(user.getUserid());
            Date date = new Date();
            doc.setCreatetime(date);

            doc.setDocnum(getdocnum(prefix));

            Version ver = new Version();
            ver.setSetuserid(user.getUserid());
            ver.setUpdatatime(date);
            ver.setVersioncode(1);
            ver.setVerstate(0);
            ver.setVerIte(1);
            Integer docid = this.documentService.addDocment(upfile, doc, ver);
            if (null != baseid) {
                if (baseid != -1) {
                    this.materialLinkDocunmet(baseid, docid);
                    return reMsgUtil.msg(doc, "ok", 0);
                } else {
                    return reMsgUtil.msg("", "vercode is repeat", 2);
                }
            }
            return reMsgUtil.msg(doc, "ok but baseid is null", 0);
        }else {
            return reMsgUtil.msg("","file is null",1);
        }

    }

    @PostMapping("add/newVersion.do")
    public Map addNewVersion(Version version , HttpSession session,MultipartFile file){
        Integer userid = loginUtile.getUser(session).getUserid();
        version.setSetuserid(userid);
        version.setUpdatatime(new Date());
        int i = redisUtils.objToInt(ConstantMaterials.KEYS[5] + version.getDocid());
        version.setVersioncode(i++);
        version.setVerstate(0);
        version.setVerIte(1);
        this.documentService.addNewVersion(version,file);

        return reMsgUtil.msg("","ok",0);
    }



    private String  getdocnum(String msg) {
        StringBuffer sb = new StringBuffer();
        sb.append(msg);
        String key = ConstantMaterials.KEYS[4] +sb.toString();
        int temp = redisUtils.objToInt(key);
        temp++;
        redisUtils.set(key,temp);
        String l = temp+"";
        for (int i = 0; i < 6-l.length() ; i++) {
            sb.append(0);
        }
        sb.append(temp);
        return sb.toString();
    }
}
