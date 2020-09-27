package com.scd.erp.controller.track;

import com.scd.erp.Vo.Nail.Feature;
import com.scd.erp.Vo.Nail.Nail;
import com.scd.erp.mapper.FeatureMapper;
import com.scd.erp.service.trace.NailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("nail")
public class NailContro {

    @Autowired
    private NailService nailService;
    @Autowired
    private FeatureMapper featureMapper;

    @RequiresPermissions("select:nail")
    @PostMapping("List.do")
    @ResponseBody
    public List<Nail> getList() {
        return this.nailService.getList();
    }

    @PostMapping("aaa")
    @ResponseBody
    public Map aaa() {
        Map map = new LinkedHashMap();
        List<Nail> nlist = this.nailService.getAllNail();
        List<Feature> fList = this.nailService.getFList();
        map.put("nail", nlist);
        map.put("feature", fList);
        return map;
    }

    @RequiresPermissions("add:nail")
    @PostMapping("nailSet.do")
    @ResponseBody
    public Map Set(Nail nail) {
        //  System.out.println(nail);
        this.nailService.nailSet(nail);
        return this.aaa();
    }

    @RequiresPermissions("add:nail")
    @PostMapping("featureSet.do")
    @ResponseBody
    public Map setf(Feature feature) {
        // System.out.println(feature);
        this.nailService.fset(feature);
        return this.aaa();
    }

    @RequiresPermissions("add:nail")
    @PostMapping("linkfn")
    @ResponseBody
    public List<Nail> linkfn(@RequestParam("nid") Integer nid,
                             @RequestParam("fid") String fid) {
        // System.out.println(nid+"?"+fid);
        String[] Fid = fid.split("_");
        for (String id : Fid) {
            this.nailService.linkfn(nid, Integer.parseInt(id));
        }

        return this.getList();
    }

    @RequiresPermissions("add:nail")
    @PostMapping("featurs.do")
    @ResponseBody
    public List<Feature> getF() {
        return this.featureMapper.selectAll();
    }
}
