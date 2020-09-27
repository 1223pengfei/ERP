package com.scd.erp.service.plm.Impl;

import com.scd.erp.Vo.commen.Condition;
import com.scd.erp.Vo.plm.Center.MaterialLink;
import com.scd.erp.Vo.plm.Center.documentLink;
import com.scd.erp.Vo.plm.Material.*;
import com.scd.erp.Vo.plm.Note.DelMsg;
import com.scd.erp.Vo.plm.Note.Note;
import com.scd.erp.Vo.plm.Note.Renew;
import com.scd.erp.mapper.*;
import com.scd.erp.service.plm.DocumentService;
import com.scd.erp.service.plm.MaterService;
import com.scd.erp.service.plm.MaterialLinkService;
import com.scd.erp.utils.common.JsonUtil;
import com.scd.erp.utils.extraUtil.ChainMap;
import com.scd.erp.utils.extraUtil.ExampleUtile;
import com.scd.erp.utils.plm.numberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.*;

@Service
public class IMaterialService implements MaterService {

    private static final Logger logger = LoggerFactory.getLogger(IMaterialService.class);

    private final ExampleUtile exampleUtile;

    private final BaseMaterialMapper materialMapper;
    private final NoteMapper noteMapper;
    private final LayoutMapper layoutMapper;
    private final RenewMapper renewMapper;
    private final CraftsMapper   craftsMapper;
    private final CycleMapper    cycleMapper;
    private final StockMapper    stockMapper;
    private final LogisticMapper logisticMapper;
    private final DelMsgMapper delMsgMapper;

    private final MaterialLinkService linkService;

    private final numberUtil numberUtil;

    @Autowired
    public IMaterialService(CycleMapper cycleMapper, ExampleUtile exampleUtile, BaseMaterialMapper materialMapper, NoteMapper noteMapper, LayoutMapper layoutMapper, RenewMapper renewMapper, CraftsMapper craftsMapper, StockMapper stockMapper, LogisticMapper logisticMapper, numberUtil numberUtil, BomMapper bomMapper, DelMsgMapper delMsgMapper, MaterialLinkService linkService, DocumentService documentService) {
        this.cycleMapper = cycleMapper;
        this.exampleUtile = exampleUtile;
        this.materialMapper = materialMapper;
        this.noteMapper = noteMapper;
        this.layoutMapper = layoutMapper;
        this.renewMapper = renewMapper;
        this.craftsMapper = craftsMapper;
        this.stockMapper = stockMapper;
        this.logisticMapper = logisticMapper;
        this.numberUtil = numberUtil;
        this.bomMapper = bomMapper;
        this.delMsgMapper = delMsgMapper;
        this.linkService = linkService;
        this.documentService = documentService;
    }


    @Override
    public void addBaseMaterial(BaseMaterial material, Note note) {
        this.materialMapper.insertSelective(material);
        note.setBaseid(material.getBaseid());
        this.noteMapper.insertSelective(note);
    }

    @Override
    public void add(Map map, Renew renew, Integer materialID) {
        Integer ID = -1;
        switch (renew.getRenewtype()){
            case "layout":
                Layout layout = JsonUtil.parse(JsonUtil.toJSON(map),Layout.class);
                layout.setBaseid(materialID);
                this.layoutMapper.insertSelective(layout);
                ID = layout.getLayoutid();
                break;
            case "crafts":
                Crafts crafts = JsonUtil.parse(JsonUtil.toJSON(map), Crafts.class);
                crafts.setBaseid(materialID);
                this.craftsMapper.insertSelective(crafts);
                ID = crafts.getCraftsid();
                break;
            case "cycle":
                Cycle cycle = JsonUtil.parse(JsonUtil.toJSON(map), Cycle.class);
                cycle.setBaseid(materialID);
                this.cycleMapper.insertSelective(cycle);
                ID = cycle.getCycleid();
                break;
            case "stock":
                Stock stock = JsonUtil.parse(JsonUtil.toJSON(map), Stock.class);
                stock.setBaseid(materialID);
                this.stockMapper.insertSelective(stock);
                ID = stock.getStockid();
                break;
            case "logistic":
                Logistic logistic = JsonUtil.parse(JsonUtil.toJSON(map), Logistic.class);
                logistic.setBaseid(materialID);
                this.logisticMapper.insertSelective(logistic);
                ID = logistic.getLogisticid();
                break;
            default:
                break;
        }
        if (ID != -1) {
            addRenew(renew, materialID, ID);
        }
    }

    private void addRenew(Renew renew, Integer materialID, Integer ID) {
        Example example = this.exampleUtile.getExample(Note.class, new ChainMap().putObj("baseid", materialID));
        Note note = this.noteMapper.selectByExample(example).get(0);
        subRenew(note.getNoteid(),renew.getRenewtype(),renew.getRenewuserid(),ID);
    }

    @Override
    public BaseMaterial getBaseMaterial(Integer materailid, String type, Integer depID, String condition) {
        //ConsoleMsg.log(materailid);
        BaseMaterial material = this.materialMapper.getMaterail(materailid);
        //System.out.println(material+">"+type);
        if (material != null ) {
            ChainMap map = new ChainMap().putObj("baseid", materailid);
            if (null != condition){
                try {
                    List<Condition> conditions = JsonUtil.StringToVoList(condition, Condition.class);
                    for (Condition c:conditions) {
                       map.putObj(c.SubType(),"%"+c.getValue()+"%");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            switch (type) {
                case "layout":
                    putLayout(material, getLayout(map));
                    break;
                case "crafts":
                    putCraft(material, getCrafts(map));
                    break;
                case "cycle":
                    putCycles(material, getCycle(map));
                    break;
                case "stock":
                    material.setStocks(getStock(map));
                    break;
                case "logistic":
                    material.setLogistic(getLogistic( materailid));
                    break;
                case "all":
                    putCraft(material, getCrafts(map));
                    putLayout(material, getLayout(map));
                    putCycles(material, getCycle(map));
                    material.setStocks(getStock(map));
                    material.setLogistic(getLogistic( materailid));
                    break;
                default:
                    break;
            }
        }
        return material;
    }

    @Override
    public void deleteMaterial(Integer materailid, DelMsg delMsg) {
        BaseMaterial material = this.materialMapper.selectByPrimaryKey(materailid);
        delMsg.setDelmaterailmsg(material);
        delMsg.setBaseid(materailid);
        this.delMsgMapper.insertSelective(delMsg);

        ChainMap map = new ChainMap<>().putObj("baseid", materailid);
        this.craftsMapper.deleteByExample(exampleUtile.getExample(Crafts.class,map));
        this.cycleMapper.deleteByExample(exampleUtile.getExample(Cycle.class,map));
        this.layoutMapper.deleteByExample(exampleUtile.getExample(Layout.class,map));
        this.logisticMapper.deleteByExample(exampleUtile.getExample(Logistic.class,map));
        this.stockMapper.deleteByExample(exampleUtile.getExample(Stock.class,map));

        this.linkService.delink(materailid);

        this.materialMapper.deleteByPrimaryKey(materailid);


    }

    @Override
    public void setAnything(Map<String, Object> map, Renew renew) {
        Integer ID = -1,baseid = -1;
        switch (renew.getRenewtype()){
            case "layout":
                Layout layout = JsonUtil.parse(JsonUtil.toJSON(map),Layout.class);
                this.layoutMapper.updateByPrimaryKeySelective(layout);
                ID = layout.getLayoutid();
                baseid = layout.getBaseid();
                break;
            case "crafts":
                Crafts crafts = JsonUtil.parse(JsonUtil.toJSON(map), Crafts.class);
                this.craftsMapper.updateByPrimaryKeySelective(crafts);
                ID = crafts.getCraftsid();
                baseid = crafts.getBaseid();
                break;
            case "cycle":
                Cycle cycle = JsonUtil.parse(JsonUtil.toJSON(map), Cycle.class);
                this.cycleMapper.updateByPrimaryKeySelective(cycle);
                ID = cycle.getCycleid();
                baseid = cycle.getBaseid();
                break;
            case "stock":
                Stock stock = JsonUtil.parse(JsonUtil.toJSON(map), Stock.class);
                this.stockMapper.updateByPrimaryKeySelective(stock);
                ID = stock.getStockid();
                baseid = stock.getBaseid();
                break;
            case "logistic":
                Logistic logistic = JsonUtil.parse(JsonUtil.toJSON(map), Logistic.class);
                this.logisticMapper.updateByPrimaryKeySelective(logistic);
                ID = logistic.getLogisticid();
                baseid = logistic.getBaseid();
                break;
            case "material":
                BaseMaterial material = JsonUtil.parse(JsonUtil.toJSON(map), BaseMaterial.class);
                this.materialMapper.updateByPrimaryKeySelective(material);
                baseid = material.getBaseid();
                ID = material.getBaseid();
                break;
            default:
                break;
        }
        logger.debug(renew.toString());
        logger.debug(ID+".."+baseid);
        if (baseid > 0 && ID > 0){
            addRenew(renew,baseid,ID);
        }
    }

    @Override
    public Note getNote(Integer materailid) {
        ChainMap map = new ChainMap().putObj("baseid", materailid);
        Example noteEX = exampleUtile.getExample(Note.class, map);
        Note note = this.noteMapper.selectByExample(noteEX).get(0);
        Example renewEX = exampleUtile.getExample(Renew.class, new ChainMap().putObj("noteid", note.getNoteid()));
        List<Renew> renews = this.renewMapper.selectByExample(renewEX);
        note.setRenews(renews);
        return note;
    }

    @Override
    public Map getRenew(Integer renewid) {
        Renew renew = this.renewMapper.selectByPrimaryKey(renewid);
        Integer ID = renew.getAttrituteid();
        Map<String,Object> map = new LinkedHashMap();
        map.put("renew",renew);
        String type = renew.getRenewtype();
        switch (type){
            case "layout":
                map.put(type,this.layoutMapper.selectByPrimaryKey(ID));
                break;
            case "crafts":
                map.put(type,this.craftsMapper.selectByPrimaryKey(ID));
                break;
            case "cycle":
                map.put(type,this.cycleMapper.selectByPrimaryKey(ID));
                break;
            case "stock":
                map.put(type,this.stockMapper.selectByPrimaryKey(ID));
                break;
            case "logistic":
                map.put(type,this.logisticMapper.selectByPrimaryKey(ID));
                break;
            case "material":
                map.put(type,this.materialMapper.selectByPrimaryKey(ID));
                break;
            default:
                break;
        }
        return map;
    }

    @Override
    public boolean hasMsg(Integer materialID, String type) {
        ChainMap map = new ChainMap<>().putObj("baseid", materialID);
        switch (type) {
            case "layout":
               return getLayout(map).size()>0;
            case "crafts":
                return  getCrafts(map).size()>0;
            case "cycle":
                return getCycle(map).size()>0;
            case "logistic":
                return getLogistic(materialID) !=null;
            case "stock":
                return false;
            default:
                return true;
        }
    }

    @Override
    public List<BaseMaterial> findCondition(List<Condition> conditions) {
        Map mapm = new LinkedHashMap();
        Map mapcr = new LinkedHashMap();
        Map mapla = new LinkedHashMap();
        Map mapcy = new LinkedHashMap();
        Map maplo = new LinkedHashMap();
        Map mapst = new LinkedHashMap();
        Map mapn = new LinkedHashMap();
        Map mapr = new LinkedHashMap();
        for (Condition c : conditions) {
            logger.info(c.MainType()+":"+c.SubType()+">>"+c.realValue());
            switch (c.MainType()) {
                case "material":

                    mapm.put(c.SubType(),  c.realValue() );
                    break;
                case "layout":

                    mapla.put(c.SubType(), c.realValue() );
                    break;
                case "crafts":

                    mapcr.put(c.SubType(), c.realValue() );
                    break;
                case "cycle":

                    mapcy.put(c.SubType(), c.realValue() );
                    break;
                case "stock":

                    mapst.put(c.SubType(), c.realValue() );
                    break;
                case "logistic":

                    maplo.put(c.SubType(), c.realValue() );
                    break;
                case "note":
                    mapn.put(c.SubType(), c.realValue());
                    break;
                case "renew":
                    mapr.put(c.SubType(),  c.realValue());
                    break;
                default:
                    break;
            }
        }

           Map<Integer,Integer> mapIDs = new HashMap<>();
            if(mapcr.size() > 0 ){
                List<Crafts> list = getCrafts(mapcr);
                if (list.size()>0) {
                    for (Crafts aList : list) {
                        handleMap(mapIDs, aList.getBaseid());
                    }
                }
            }
            if(mapla.size() > 0 ){
                List<Layout> list = getLayout(mapla);
                if (list.size()>0) {
                    for (Layout aList : list) handleMap(mapIDs, aList.getBaseid());
                }
            }
            if(mapcy.size() > 0 ){
                List<Cycle> list = getCycle(mapcy);
                if (list.size()>0) {
                    for (Cycle aList : list) handleMap(mapIDs, aList.getBaseid());
                }
            }
            //System.out.println(maplo.size());
            if(maplo.size() > 0 ){
                List<Logistic> list = getLogistic(maplo);
                if (list.size()>0) {
                    for (Logistic aList : list) handleMap(mapIDs, aList.getBaseid());
                }
            }
            if(mapst.size() > 0 ){
                List<Stock> list = getStock(mapst);
                if (list.size()>0) {
                    for (Stock aList : list) handleMap(mapIDs, aList.getBaseid());
                }
            }
            List<Integer> noteids = new LinkedList<>();
            if(mapr .size() > 0 ){
                String[] time = null;
                if (mapr.containsKey("renewtime")){
                    time = mapr.get("renewtime").toString().split("_");
                    mapr.remove("renewtime");
                }
                Example ex = this.exampleUtile.getExample(Renew.class,mapr);
                if (null != time) {
                    ex.and().andBetween("renewtime", time[0], time[1]);
                }
                List<Renew> list = this.renewMapper.selectByExample(ex);
                for (Renew aList : list) {
                    noteids.add(aList.getNoteid());
                }
            }

            if(mapn .size() > 0 ){
                Example ex = this.exampleUtile.getExample(Note.class);
                Example.Criteria criteria = ex.createCriteria();
                if (mapn.containsKey("creattime")){
                    String[] time = mapn.get("creattime").toString().split("_");
                    criteria.andBetween("creattime",time[0],time[1]);
                }
                if (mapn.containsKey("creatuserid")){
                    criteria.andEqualTo("creatuserid",mapn.get("creatuserid"));
                }
                if (noteids.size()>0){
                    Example.Criteria c2 = ex.createCriteria();
                    for (Integer id:noteids) {
                        c2.orEqualTo("noteid",id);
                    }
                }
                List<Note> list = this.noteMapper.selectByExample(ex);
                for (Note aList : list) {
                    handleMap(mapIDs, aList.getBaseid());
                }

            }
            Integer temp = -1;
            List<Integer> ids = new ArrayList<>();
            for(Map.Entry<Integer, Integer> entry: mapIDs.entrySet())
            {
                Integer v = entry.getValue();
                if (v >= temp){
                    temp = v;
                    if (ids.size()>0 && mapIDs.get(ids.get(0)) < v){
                        ids.clear();
                    }
                    ids.add(entry.getKey());
                }
            }
            List<BaseMaterial> list = new ArrayList<>();
            //System.out.println(ids.size());
        if (ids.size()>0) {
            for (Integer baseid : ids) {
                BaseMaterial base = this.getBaseMaterial(baseid, "all", null, null);
                //System.out.println(mapm.size());
                if (mapm.size() == 0) {
                    list.add(base);
                } else {
                    Example exm = this.exampleUtile.getExample(BaseMaterial.class, mapm);
                    exm.and().andEqualTo("baseid", baseid);
                    if (this.materialMapper.selectByExample(exm).size() > 0)
                        list.add(base);
                }

            }
        }else {
            Example exm = this.exampleUtile.getExample(BaseMaterial.class, mapm);
            List<BaseMaterial> listm = this.materialMapper.selectByExample(exm);
            for (BaseMaterial m:listm) {
                     list.add( this.getBaseMaterial(m.getBaseid(), "all", null, null));
            }
        }
            return list;
        }

    @Override
    public BaseMaterial copyBase(Integer baseid, Integer userid,Integer type) {
        //ConsoleMsg.log(baseid+">>"+userid);
        BaseMaterial base = getBaseMaterial(baseid, "all", null, null);
        //ConsoleMsg.log(base);
        base.setBaseid(null);
        base.setErpnum(numberUtil.setERPnum(base.getErpnum().substring(0,2)));
        this.materialMapper.insertSelective(base);

        Note note = new Note();
        Integer copyBaseid = base.getBaseid();
        note.setBaseid(copyBaseid);
        note.setState(0+"");
        note.setCreatuserid(userid);
        note.setCreattime(new Date());
        this.noteMapper.insertSelective(note);

        Logistic logistic = base.getLogistic();
        if (null != logistic) {
            logistic.setBaseid(copyBaseid);
            logistic.setLogisticid(null);
            this.logisticMapper.insertSelective(logistic);
            subRenew(note.getNoteid(), "logistic", userid, logistic.getLogisticid());
        }
        Layout layout = base.getLayout();
        if (null != layout) {
            layout.setBaseid(copyBaseid);
            layout.setLayoutid(null);
            this.layoutMapper.insertSelective(layout);
            subRenew(note.getNoteid(), "layout", userid, layout.getLayoutid());
        }
        Crafts crafts = base.getCrafts();
        if (null != crafts) {
            crafts.setCraftsid(null);
            crafts.setBaseid(copyBaseid);
            this.craftsMapper.insertSelective(crafts);
            subRenew(note.getNoteid(), "crafts", userid, crafts.getCraftsid());
        }
        Cycle cycle = base.getCycle();
        if (null!=cycle) {
            cycle.setBaseid(copyBaseid);
            cycle.setCycleid(null);
            this.cycleMapper.insertSelective(cycle);
            subRenew(note.getNoteid(), "cycle", userid, cycle.getCycleid());
        }
        List<Stock> stocks = base.getStocks();
        if (null != stocks && stocks.size()>0) {
            for (Stock s : stocks) {
                s.setBaseid(copyBaseid);
                s.setStockid(null);
                this.stockMapper.insertSelective(s);
                subRenew(note.getNoteid(), "stock", userid, s.getStockid());
            }
        }

        base.setCycle(cycle);
        base.setCrafts(crafts);
        base.setStocks(stocks);
        base.setLogistic(logistic);
        base.setLayout(layout);

        if (type == 2)
        {
            copyDoc(baseid, copyBaseid);
        }
        if (type == 3)
        {
            copyMaterlink(baseid, copyBaseid);
            copyDoc(baseid, copyBaseid);
        }

        return base;
    }

    @Override
    public BaseMaterial toExcle(Integer baseid) {
        BaseMaterial material = this.materialMapper.selectByPrimaryKey(baseid);
        List<MaterialLink> links = this.linkService.getLink(baseid, "main");
        List<Bom> list = new LinkedList<>();
        for (MaterialLink link:links) {
            BaseMaterial all = this.getBaseMaterial(link.getSubbaseid(), "stock", null, null);
            Example ex = exampleUtile.getExample(Bom.class, new ChainMap().putObj("baseid", link.getMateriallinkid()));
            Bom bom = this.bomMapper.selectOneByExample(ex);
            bom.setBaseMaterial(all);
            list.add(bom);
        }
        material.setBoms(list);
        return material;
    }
//=====================================================================================================
    private void copyDoc(Integer baseid, Integer copyBaseid) {
        List<documentLink> links = this.documentService.getLink(baseid);
        for (documentLink dl:links) {
           documentLink nl = new documentLink();
           nl.setMaterialid(copyBaseid);
           nl.setDocumentid(dl.getDocumentid());
           this.documentService.materialLinkDocunmet(nl);

        }
    }

    private final DocumentService documentService;
    private final BomMapper bomMapper;

    private void copyMaterlink(Integer baseid, Integer copyBaseid) {
        List<MaterialLink> links = this.linkService.getLink(baseid,"main");
        for (MaterialLink l:links) {
            MaterialLink link = new MaterialLink();
            link.setMainbaseid(copyBaseid);
            link.setSubbaseid(l.getSubbaseid());
            this.linkService.link(link);
            Example example = exampleUtile.getExample(Bom.class, new ChainMap().putObj("baseid",l.getMateriallinkid()));
            Bom bom = this.bomMapper.selectOneByExample(example);
            if (null != bom) {
                bom.setBaseid(link.getMateriallinkid());
                this.bomMapper.insertSelective(bom);
            }
        }
    }

    private void subRenew(Integer noteid, String type, Integer userid, Integer atrid){
        Renew renew = new Renew();
        renew.setNoteid(noteid);
        renew.setRenewtime(new Date());
        renew.setRenewtype(type);
        renew.setRenewuserid(userid);
        renew.setAttrituteid(atrid);
        this.renewMapper.insertSelective(renew);
    }


    private void handleMap(Map<Integer,Integer> mapIDs, Integer baseid) {
        int l = 1;
        if(mapIDs.containsKey(baseid)){
            l += mapIDs.get(baseid);
            System.out.println(l);
        }
        mapIDs.put(baseid,l);
    }

    private <T> Example getExample(Map mapcr, BaseMaterial m,Class<T> entityClass) {
        Example excr = exampleUtile.getExample(entityClass, mapcr);
        excr.and().andEqualTo("baseid", m.getBaseid());
        return excr;
    }

    private Logistic getLogistic(Integer id) {
        return this.logisticMapper.getLogistic(id);
    }
    private List<Logistic> getLogistic(Map map) {
        final Example exlo = exampleUtile.getExample(Logistic.class, map);
        return this.logisticMapper.selectByExample(exlo);
    }

    private  List<Stock> getStock(Map map) {
        Example stockEX = exampleUtile.getExample(Stock.class, map);
        return this.stockMapper.selectByExample(stockEX);

    }

    private List<Cycle> getCycle(Map map) {
        Example cycleEX = exampleUtile.getExample(Cycle.class, map);
        return this.cycleMapper.selectByExample(cycleEX);
    }

    private void putCycles(BaseMaterial material, List<Cycle> cycles) {
        if (cycles.size() >0)
        material.setCycle(cycles.get(0));
    }

    private List<Crafts> getCrafts(Map map) {
        Example craftsEX = exampleUtile.getExample(Crafts.class, map);
        return this.craftsMapper.selectByExample(craftsEX);

    }

    private void putCraft(BaseMaterial material, List<Crafts> crafts) {
        if (crafts.size()>0)
        material.setCrafts(crafts.get(0));
    }

    private List<Layout> getLayout( Map map) {
        Example example = exampleUtile.getExample(Layout.class, map);
        return this.layoutMapper.selectByExample(example);

    }

    private void putLayout(BaseMaterial material, List<Layout> layouts) {
        if (layouts.size()>0) {
            material.setLayout(layouts.get(0));
        }
    }



}