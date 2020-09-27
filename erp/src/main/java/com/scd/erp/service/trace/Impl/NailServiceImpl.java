package com.scd.erp.service.trace.Impl;

import com.scd.erp.Vo.Nail.Feature;
import com.scd.erp.Vo.Nail.Nail;
import com.scd.erp.mapper.FeatureMapper;
import com.scd.erp.mapper.NailMapper;
import com.scd.erp.service.trace.NailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NailServiceImpl implements NailService {

    @Autowired
    private NailMapper nailMapper;
    @Autowired
    private FeatureMapper featureMapper;

    @Override
    public List<Nail> getList() {
        return this.nailMapper.getList();
    }

    @Override
    public void nailSet(Nail nail) {
        this.setNail(nail);
    }

    @Override
    public void fset(Feature feature) {
        this.setFeature(feature);
    }

    @Override
    public void linkfn(Integer nid, Integer fid) {
        linkNF(nid, fid);
    }

    @Override
    public List<Feature> getFList() {
        return this.featureMapper.selectAll();
    }

    @Override
    public List<Nail> getAllNail() {
        return this.nailMapper.selectAll();
    }


    private void linkNF(int nid, int fid) {
        this.nailMapper.linkNF(nid, fid);
    }

    private int setFeature(Feature feature) {
        Integer featureid = feature.getFeatureid();
        if (featureid == null) {
            this.featureMapper.insertSelective(feature);
            featureid = feature.getFeatureid();
        } else {
            this.featureMapper.updateByPrimaryKey(feature);
        }
        return featureid;
    }

    private int setNail(Nail nail) {
        Integer nailid = nail.getNailid();
        if (nailid == null) {
            this.nailMapper.insertSelective(nail);
            nailid = nail.getNailid();
        } else {
            this.nailMapper.updateByPrimaryKey(nail);
        }
        return nailid;
    }
}
