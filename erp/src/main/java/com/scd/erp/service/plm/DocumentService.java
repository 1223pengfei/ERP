package com.scd.erp.service.plm;

import com.scd.erp.Vo.commen.Condition;
import com.scd.erp.Vo.plm.Center.documentLink;
import com.scd.erp.Vo.plm.Document.Document;
import com.scd.erp.Vo.plm.Document.Keep;
import com.scd.erp.Vo.plm.Document.Menu;
import com.scd.erp.Vo.plm.Document.Version;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

    Integer addDocment(MultipartFile files, Document doc, Version ver);

    void addNewVersion(Version version, MultipartFile file);

    void materialLinkDocunmet(Integer docid, Integer baseid);

    Menu getDocWithMenu(Integer menuid);

    List<Keep> getKeeps(Integer userid);

    List<documentLink> getLink(Integer baseid);

    void materialLinkDocunmet(documentLink nl);

    List<Document> findCondition(List<Condition> newCondis);
}
