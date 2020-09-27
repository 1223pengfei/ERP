package com.scd.erp.service.doc;

import com.scd.erp.Vo.Doc.Belong;
import com.scd.erp.Vo.Doc.Docement;
import com.scd.erp.Vo.Person.Person;
import com.scd.erp.Vo.Person.User;

import java.util.Map;

public interface DocService {
    void addFile(Docement doc, Person pid, Integer paid);

    void setBelong(Belong be);

    Map getPackages(User user, Integer packageid);
}
