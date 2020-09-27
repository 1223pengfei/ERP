package com.scd.erp.service.doc.Impl;

import com.scd.erp.Vo.Department.Department;
import com.scd.erp.Vo.Doc.Belong;
import com.scd.erp.Vo.Doc.Docement;
import com.scd.erp.Vo.Doc.Operate;
import com.scd.erp.Vo.Doc.Package;
import com.scd.erp.Vo.Person.Person;
import com.scd.erp.Vo.Person.User;
import com.scd.erp.mapper.*;
import com.scd.erp.service.doc.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocServiceImpl implements DocService {
    private final DocementMapper docementMapper;
    private final OperateMapper operateMapper;
    private final BelongMapper belongMapper;
    private final LinkMapper linkMapper;

    private final PersonMapper personMapper;
    private final PackageMapper packageMapper;

    @Autowired
    public DocServiceImpl(DocementMapper docementMapper, OperateMapper operateMapper, BelongMapper belongMapper, LinkMapper linkMapper, PersonMapper personMapper, PackageMapper packageMapper) {
        this.docementMapper = docementMapper;
        this.operateMapper = operateMapper;
        this.belongMapper = belongMapper;
        this.linkMapper = linkMapper;
        this.personMapper = personMapper;
        this.packageMapper = packageMapper;
    }

    public Map getPackages(User user, Integer packageid) {
        Map map = new LinkedHashMap();
        String msg;
        Integer presonid = user.getPresonid();
        Person personAll = this.personMapper.getPersonAll(presonid);

        if (null != personAll && null != personAll.getJob()) {
            Department department = personAll.getJob().getDepartment();
            //System.out.println(department);
            Integer updepid = department.getUpdepid();
            Integer departmentid = department.getDepartmentid();
            if (updepid != 0){
                departmentid = department.getDepart().getDepartmentid();
            }
            //System.out.println(departmentid);
            List<Package> packages = this.packageMapper.allFile(presonid, departmentid, packageid);
            msg = packages.size() > 0? "ok":"vacant";
            map.put("data",packages);
        } else {
            msg = "no job";
        }
        map.put("msg",msg);
        return map;
    }


    public void addFile(Docement doc, Person person, Integer paid) {

        doc.setFilecreattime(new Date());
        this.docementMapper.insertSelective(doc);

        Integer fileid = doc.getFileid();
        addOperat(fileid, person.getPersonid());
        //System.out.println(person);
        Belong be = new Belong();
        be.setFileid(fileid);
        be.setDepartmentid(person.getJob().getDepid());
        be.setNowbelonguserid(person.getPersonid());
        be.setUpdatauserid(person.getPersonid());
        be.setLasttime(new Date());
        be.setIsbelonguser(true);
        this.setBelong(be);

        this.linkMapper.package_doc(paid, fileid);
    }

    @Override
    public void setBelong(Belong be) {
        if (be.getBelongid() != null) {
            be.setLasttime(new Date());
            this.belongMapper.updateByPrimaryKey(be);
        } else {
            this.belongMapper.insertSelective(be);
        }
    }

    private void addOperat(Integer fileid, Integer pid) {
        Operate op = new Operate();
        op.setFileid(fileid);
        op.setOperatype("1");
        op.setUserid(pid);
        op.setOperatime(new Date());
        this.operateMapper.insertSelective(op);
    }


}
