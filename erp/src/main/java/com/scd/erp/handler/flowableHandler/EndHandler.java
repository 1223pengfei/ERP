package com.scd.erp.handler.flowableHandler;

import com.scd.erp.Vo.Process.Annex;
import com.scd.erp.Vo.plm.Document.Document;
import com.scd.erp.Vo.plm.Document.Version;
import com.scd.erp.mapper.AnnexMapper;
import com.scd.erp.mapper.VersionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EndHandler {

    @Autowired private AnnexMapper annexMapper;
    @Autowired private VersionMapper versionMapper;


    private void handleAnnex(Annex annex, int state) {
        annex.setState(state);
        this.annexMapper.updateByPrimaryKeySelective(annex);


    }

    private void handleVer(Annex annex) {
        List<Document> documents = annex.getDocuments();
        for (Document d:documents) {
            Version version = d.getVersion();
            version.setVerIte(version.getVerIte()+1);
            this.versionMapper.updateByPrimaryKey(version);
        }
    }

    void handlethis(Annex annex) {
        handleAnnex(annex,2);
    }

    void handlethis(Annex annex , int state) {
        handleAnnex(annex,state);
        handleVer(annex);
    }
}
