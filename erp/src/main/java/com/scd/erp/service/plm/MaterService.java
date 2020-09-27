package com.scd.erp.service.plm;

import com.scd.erp.Vo.commen.Condition;
import com.scd.erp.Vo.plm.Material.BaseMaterial;
import com.scd.erp.Vo.plm.Note.DelMsg;
import com.scd.erp.Vo.plm.Note.Note;
import com.scd.erp.Vo.plm.Note.Renew;

import java.util.List;
import java.util.Map;

public interface MaterService {

    void addBaseMaterial(BaseMaterial material, Note note);

//    void addLayout(Layout layout, Renew renew, Integer materialID);
//
//    void addCrafts(Crafts crafts, Renew renew, Integer materialID);

    void add(Map map, Renew renew, Integer materialID);

    BaseMaterial getBaseMaterial(Integer materailid, String type, Integer depID, String condition);

    void deleteMaterial(Integer materailid, DelMsg delMsg);

    void setAnything(Map<String,Object> params, Renew renew);

    Note getNote(Integer materailid);

    Map getRenew(Integer renewid);

    boolean hasMsg(Integer materialID, String type);

    List<BaseMaterial> findCondition(List<Condition> conditions);

    BaseMaterial copyBase(Integer baseid, Integer userid,Integer type);

    BaseMaterial toExcle(Integer baseid);
}
