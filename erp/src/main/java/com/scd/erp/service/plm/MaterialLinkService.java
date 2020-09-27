package com.scd.erp.service.plm;

import com.scd.erp.Vo.plm.Center.MaterialLink;
import com.scd.erp.Vo.plm.Material.BaseMaterial;

import java.util.List;

public interface MaterialLinkService {
    boolean link(MaterialLink link);

    void delink(Integer linkid);
    void delink(Integer linkid, String type);

    BaseMaterial getMaterialByType(Integer baseid, String type, Integer depID, Integer offset, Integer page, String way);

    List<MaterialLink> getLink(Integer copyBaseid, String condi);
}
