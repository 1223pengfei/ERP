package com.scd.erp.service.trace;

import com.scd.erp.Vo.Materail.Materail;

import java.util.List;

public interface MaterailService {

    List<Materail> getList();

    void setMater(Materail materail);

    Materail getOne(Materail materail);

    boolean del(Materail materail);


    List<Materail> carousel();
}
