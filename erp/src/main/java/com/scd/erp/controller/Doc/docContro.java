package com.scd.erp.controller.Doc;

import com.scd.erp.Vo.Doc.Collect;
import com.scd.erp.Vo.Doc.Docement;
import com.scd.erp.Vo.Doc.Package;
import com.scd.erp.Vo.Doc.PackageType;
import com.scd.erp.Vo.Person.Person;
import com.scd.erp.Vo.Person.User;
import com.scd.erp.mapper.*;
import com.scd.erp.service.doc.DocService;
import com.scd.erp.utils.common.FileUtils;
import com.scd.erp.utils.common.ReMsgUtil;
import com.scd.erp.utils.extraUtil.ChainMap;
import com.scd.erp.utils.extraUtil.ExampleUtile;
import com.scd.erp.utils.extraUtil.LoginUtile;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RequestMapping("doc")
@RestController
public class docContro {
    private final DocService docService;


    private final PackageTypeMapper packageTypeMapper;
    private final PackageMapper packageMapper;
    private final PersonMapper personMapper;
    private final CollectMapper collectMapper;
    private ExampleUtile eu;
    private LoginUtile lu;
    private ReMsgUtil mu;
    @Autowired private FileUtils fileUtils;




    @Autowired
    public docContro(DocService docService, PackageTypeMapper packageTypeMapper, PackageMapper packageMapper, DocementMapper docementMapper, PersonMapper personMapper, CollectMapper collectMapper) {
        this.docService = docService;
        this.packageTypeMapper = packageTypeMapper;
        this.packageMapper = packageMapper;
        this.personMapper = personMapper;
        this.collectMapper = collectMapper;
        this.eu = new ExampleUtile();
        this.lu = new LoginUtile();
        this.mu = new ReMsgUtil();
    }

    @RequiresPermissions("select:file")
    @PostMapping("allFile.do")
    public Map allFile(HttpServletRequest request,
                       @RequestParam("packageid") Integer packageid) {
        //System.out.println(packageid);
        User user = lu.getUser(request);

        return this.docService.getPackages(user, packageid);
    }




    @PostMapping("newCollect.do")
    public List<Collect> newCollect(Collect collect, HttpServletRequest request) {
        Integer presonid = lu.getpersonid(request);
        collect.setPersonid(presonid);
        this.collectMapper.insertSelective(collect);
      return   this.collectMapper.withPerson(presonid);
    }



    @PostMapping("collects.do")
    public List<Collect> collects(HttpServletRequest request) {
        Integer presonid = lu.getpersonid(request);
        Example ex = new Example(Collect.class);
        ex.createCriteria().andEqualTo("personid", presonid);
        return this.collectMapper.selectByExample(ex);
    }

    @PostMapping("collectsWithPerson.do")
    public List<Collect> withPerson(HttpServletRequest request) {
        return this.collectMapper.withPerson(lu.getpersonid(request));
    }

    /**
     * @param type
     */
    @RequiresPermissions("add:packtype")
    @PostMapping("packTypeSet.do")
    public Map setpackagetype(PackageType type) {
        // System.out.println(type.getTypename());
        Example ex = eu.getExample(PackageType.class);
        ex.createCriteria().andEqualTo("typename",type.getTypename());
        List<PackageType> pts = this.packageTypeMapper.selectByExample(ex);
        if (pts.size() == 0) {
            this.packageTypeMapper.insertSelective(type);
            List<PackageType> list = this.packageTypeMapper.selectAll();
            return mu.msg(list,"OK",0);
        }
        return mu.msg(null,"the name is repeated!",2);
    }

    @PostMapping("packTypes.do")
    public List<PackageType> ptlist() {
        return this.packageTypeMapper.selectAll();
    }

    @PostMapping("packages.do")
    public List<Package> packages(HttpServletRequest req) {
        Integer depID = lu.getDepID(req);
        Example ex = eu.getExample(Package.class);
        ex.createCriteria().andEqualTo("belongdepartmentid",depID);
        return this.packageMapper.selectByExample(ex);
    }

    @RequiresPermissions("add:package")
    @PostMapping("newPackage.do")
    public Map addPackage(Package pack,HttpServletRequest request) {
        Example ex = eu.getExample(Package.class, new ChainMap().putObj("packagename", pack.getPackagename()));
        List<Package> list = this.packageMapper.selectByExample(ex);
        if (list.size() == 0) {
            Integer depID = lu.getDepID(request);
            pack.setBelongdepartmentid(depID);
            pack.setPackagecreattime(new Date());
            //System.out.println(pack);
            this.packageMapper.insertSelective(pack);
            return mu.msg(null,"ok",0);
        }
        return mu.msg(null,"the name is repeated!",2);
    }



    @RequiresPermissions("updata:doc")
    @RequestMapping("uploads")
    public List<Integer> uploads(HttpServletRequest request,
                                 @RequestParam("packageid") Integer paid) {
        //System.out.println(paid);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("uploadFile");
        if (files.size() > 0) {
            List<Integer> IDs = new LinkedList<>();
            for (MultipartFile file : files) {
                IDs.add(this.upload(file, request, paid));
            }
            return IDs;
        } else {
            return null;
        }
    }


    private Integer upload(MultipartFile uploadFile,
                           HttpServletRequest request,
                           Integer paid) {
        Person person = this.personMapper.getPersonAll(lu.getpersonid(request));

        String fname = uploadFile.getOriginalFilename();
        String suffix = fname.split("\\.")[1];
        String filename = UUID.randomUUID().toString() + "." + suffix;
        final String secDomain = "DocFile";

        try {
            File dir = fileUtils.handlePath(secDomain, filename);
            //System.out.println(dir.getPath());
            uploadFile.transferTo(dir);


            long length = dir.length();
            String filePath = "/" + secDomain + "/" + filename;
            Docement doc = new Docement();

            doc.setPackageid(paid);
            doc.setFilename(fname);
            doc.setFlieurl(filePath);
            doc.setSuffix(suffix);
            doc.setSize(length);
            this.docService.addFile(doc, person, paid);
            //System.out.println(materialid+">>>>>");
            return doc.getFileid();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @RequiresPermissions("down:doc")
    @RequestMapping("download.do")
    public void download(HttpServletResponse response,
                         @RequestParam("ids") String ids) {
    }
}
