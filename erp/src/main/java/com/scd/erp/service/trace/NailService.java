package com.scd.erp.service.trace;

import com.scd.erp.Vo.Nail.Feature;
import com.scd.erp.Vo.Nail.Nail;

import java.util.List;

public interface NailService {
    List<Nail> getList();

    void nailSet(Nail nail);

    void fset(Feature feature);

    void linkfn(Integer nid, Integer fid);

    List<Feature> getFList();

    List<Nail> getAllNail();
}
