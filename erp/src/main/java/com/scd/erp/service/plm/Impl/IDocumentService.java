package com.scd.erp.service.plm.Impl;

import com.scd.erp.Vo.commen.Condition;
import com.scd.erp.Vo.plm.Center.documentLink;
import com.scd.erp.Vo.plm.Document.*;
import com.scd.erp.mapper.*;
import com.scd.erp.service.plm.DocumentService;
import com.scd.erp.utils.common.FileUtils;
import com.scd.erp.utils.extraUtil.ChainMap;
import com.scd.erp.utils.extraUtil.ConsoleMsg;
import com.scd.erp.utils.extraUtil.ExampleUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class IDocumentService implements DocumentService {

    @Autowired private DocumentMapper documentMapper;
    @Autowired private VersionMapper versionMapper;
    @Autowired private documentLinkMapper documentLinkMapper;
    @Autowired private MenuMapper menuMapper;
    @Autowired private KeepMapper keepMapper;
    @Autowired private KeepDetailMapper keepDetailMapper;

    @Autowired ExampleUtile exampleUtile;
    @Autowired FileUtils fileUtils;

    private static final String secDomain = "MaterialFile";


    @Override
    public Integer addDocment(MultipartFile uploadFile,
                              Document doc, Version ver) {
        ConsoleMsg.log(doc);
        if (null == doc.getDocname()){
            doc.setDocname(uploadFile.getOriginalFilename());
        }
        if (null == doc.getDocumentid()) {
            System.out.println(doc);
            this.documentMapper.insertSelective(doc);
        }
        ver.setDocid(doc.getDocumentid());
        Example vex = exampleUtile.getExample(Version.class
                , new ChainMap().putObj("versioncode", ver.getVersioncode())
                        .putObj("docid",doc.getDocumentid()));
        int size = this.versionMapper.selectByExample(vex).size();
        if(size == 0) {
            addVersion(uploadFile, ver);
        }else{
            return -1;
        }
        return doc.getDocumentid();
        //materialLinkDocunmet(doc.getDocumentid(), baseid);
    }



    @Override
    public void addNewVersion(Version version, MultipartFile file) {
        addNewVersion(version,file);
    }


    private void addVersion(MultipartFile uploadFile, Version ver) {

        String fname = uploadFile.getOriginalFilename();
        String suffix = fname.split("\\.")[1];
        String filename = UUID.randomUUID().toString() + "." + suffix;
        fileUtils.addFile(uploadFile, filename,secDomain);
        ver.setDocsize((int)uploadFile.getSize() );
        ver.setDocurl("/" + secDomain + "/" + filename);
        ver.setSuffix(suffix);
        this.versionMapper.insertSelective(ver);
    }



    @Override
    public Menu getDocWithMenu(Integer menuid) {
        Example ex = exampleUtile.getExample(Document.class, new ChainMap().putObj("menuid", menuid));
        List<Document> documents = this.documentMapper.selectByExample(ex);
        for (Document doc:documents) {
            Example vex = exampleUtile.getExample(Version.class,
                    new ChainMap().putObj("docid", doc.getDocumentid()));
            vex.orderBy("versionid");
            doc.setVersions(this.versionMapper.selectByExample(vex));
        }
        Menu menu = this.menuMapper.selectByPrimaryKey(menuid);
        menu.setDocuments(documents);
        return menu;
    }

    @Override
    public List<Keep> getKeeps(Integer userid) {
        Example exk = exampleUtile.getExample(Keep.class, new ChainMap().putObj("userid", userid));
        List<Keep> keeps = this.keepMapper.selectByExample(exk);
        for (Keep k:keeps) {
            // ConsoleMsg.log(k);
            Example exkd = exampleUtile.getExample(KeepDetail.class, new ChainMap().putObj("keepid", k.getKeepid()));
            List<KeepDetail> kds = this.keepDetailMapper.selectByExample(exkd);
            // System.out.println(kd);
            if (null != kds && kds.size() > 0) {
                List<Document> documents = new LinkedList<>();
                for (KeepDetail kd:kds) {
                    Document document = this.documentMapper.selectByPrimaryKey(kd.getDocid());
                    Version version = this.versionMapper.selectByPrimaryKey(kd.getVersonid());
                    if (null != version) {
                        document.setVersion(version);
                    }
                    documents.add(document);
                }

                k.setDocuments(documents);
            }
        }
        return keeps;
    }

    @Override
    public List<documentLink> getLink(Integer baseid) {
        Example example = exampleUtile.getExample(documentLink.class, new ChainMap().putObj("materialid", baseid));
        List<documentLink> links = this.documentLinkMapper.selectByExample(example);
        return links;
    }

    @Override
    public void materialLinkDocunmet(documentLink nl) {
        this.documentLinkMapper.insertSelective(nl);
    }

    @Override
    public List<Document> findCondition(List<Condition> newCondis) {
        List<Document> list = new LinkedList<>();

        Example docex = exampleUtile.getExample(Document.class);
        Example.Criteria doccri = docex.createCriteria();

        for (int i = 0; i < newCondis.size() ; i++) {
            Condition condi = newCondis.get(i);
            if (condi.MainType().equals("doc")) {
                if (condi.SubType().contains("time")){
                    String[] times = condi.getValue().toString().split("_");
                    doccri.andBetween(condi.SubType(),times[0],times[1]);
                }else {
                    doccri.andEqualTo(condi.SubType(), condi.realValue());
                }
            }
            newCondis.remove(i);
        }
        if (doccri.isValid()) {
            List<Document> documents = this.documentMapper.selectByExample(docex);
            if (null != documents && documents.size() >0) {
                for (int i = 0; i < documents.size(); i++) {
                    Document doc = documents.get(i);
                    Example ex = exampleUtile.getExample(Version.class);
                    Example.Criteria cri = ex.createCriteria();
                    cri.andEqualTo("docid", doc.getDocumentid());
                    if (newCondis.size() > 0) {
                        for (Condition condi : newCondis) {
                            if (condi.SubType().contains("time")) {
                                String[] times = condi.getValue().toString().split("_");
                                cri.andBetween(condi.SubType(), times[0], times[1]);
                            } else {
                                cri.andEqualTo(condi.SubType(), condi.realValue());
                            }
                        }
                    }
                    List<Version> versions = this.versionMapper.selectByExample(ex);
                    if (null != versions && versions.size() > 0) {
                        doc.setVersions(versions);
                        list.add(doc);
                    }
                }
            }
        } else {
            Example ex = exampleUtile.getExample(Version.class);
            Example.Criteria cri = ex.createCriteria();
            if (newCondis.size()>0){
                for (Condition condi:newCondis) {
                    if (condi.SubType().contains("time")){
                        String[] times = condi.getValue().toString().split("_");
                        cri.andBetween(condi.SubType(),times[0],times[1]);
                    }else {
                        cri.andEqualTo(condi.SubType(), condi.realValue());
                    }
                }
            }
            List<Version> versions = this.versionMapper.selectByExample(ex);
            if (null != versions && versions.size() > 0){
               Map<Integer,List<Version>> mapv = new HashMap<>();
                for (int i = 0; i < versions.size() ; i++) {
                    Version ver = versions.get(i);
                    Integer docid = ver.getDocid();
                    List<Version> vers = mapv.get(docid);
                    if (null == vers){
                        vers = new LinkedList<>();
                    }
                    vers.add(ver);
                    mapv.put(docid,vers);
                }
                Set<Map.Entry<Integer, List<Version>>> set = mapv.entrySet();
                for (Map.Entry<Integer, List<Version>> en:set) {
                    Document document = this.documentMapper.selectByPrimaryKey(en.getKey());
                    document.setVersions(en.getValue());
                    list.add(document);
                }
            }
        }

        return list;
    }

    public void materialLinkDocunmet(Integer docid, Integer baseid) {
        documentLink link = new documentLink();
        link.setDocumentid(docid);
        link.setMaterialid(baseid);
        materialLinkDocunmet(link);
    }
}
