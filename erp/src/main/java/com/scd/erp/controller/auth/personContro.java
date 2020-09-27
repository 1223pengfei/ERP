package com.scd.erp.controller.auth;

import com.scd.erp.Vo.Department.Department;
import com.scd.erp.Vo.Department.Job;
import com.scd.erp.Vo.Person.Contact;
import com.scd.erp.Vo.Person.Person;
import com.scd.erp.Vo.ToExcel.ModelExcel;
import com.scd.erp.Vo.ToExcel.PersonToExcel;
import com.scd.erp.service.auth.PersonService;
import com.scd.erp.utils.common.FileUtils;
import com.scd.erp.utils.common.JsonUtil;
import com.scd.erp.utils.common.idcardUtils;
import com.scd.erp.utils.extraUtil.ChainMap;
import com.scd.erp.utils.extraUtil.ConsoleMsg;
import com.scd.erp.utils.trace.easyPoiUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("person")
public class personContro {

    private final PersonService personService;
    private FileUtils fu;

    @Autowired
    public personContro(PersonService personService) {
        this.personService = personService;
        this.fu = new FileUtils();

    }

    @RequiresPermissions("add:person")
    @PostMapping("add.do")
    @ResponseBody
    public Person add(Person person, @RequestParam("contactz") String cs) {
        ConsoleMsg.debug(person);
        ConsoleMsg.debug(cs);

        try {
            List<Contact> contacts = JsonUtil.StringToVoList(cs, Contact.class);
            return this.personService.set(person, contacts);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @PostMapping("getName.do")
    @ResponseBody
    public List<Person> getName() {
        return this.personService.getName();
    }


    @RequiresPermissions("select:person")
    @PostMapping("persons.do")
    @ResponseBody
    public Map persons(@RequestParam("map") String smap) {
        //System.out.println(smap);
        Map map = JsonUtil.JsonToMap(smap);
        int size = map.size();
        if (size > 0) {
            return this.personService.persons(map);
        } else {
            return null;
        }
    }

    @RequiresPermissions("del:person")
    @PostMapping("del.do")
    @ResponseBody
    public boolean del(@RequestParam("personid") Integer id) {
        return this.personService.del(id);
    }


    @PostMapping("setContact.do")
    @ResponseBody
    public boolean setContact(@RequestParam("contactz") String contact,Integer personid) {
        ConsoleMsg.log(contact);
        ConsoleMsg.log(personid);
        try {
            List<Contact> contacts = JsonUtil.StringToVoList(contact, Contact.class);
            return this.personService.setContact(contacts,personid);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequiresPermissions("down:person")
    @RequestMapping("toExcel.do")
    public void toExcel(@RequestParam("map") String smap, HttpServletResponse response) {
        //System.out.println(smap);

        if (null == smap || smap.equals("") || smap.equals("null")) {
            smap = "{\"n\":0}";
        }
        List<Person> ps = (List<Person>) persons(smap).get("data");
        mapTodown(response, ps);
    }

    private void mapTodown(HttpServletResponse response, List<Person> ps) {
        String[] type = {"其他", "手机", "座机", "微信", "钉钉", "QQ", "个人邮箱"};
        List<PersonToExcel> ptes = new LinkedList<>();
        for (Person p : ps) {
            String idcard = p.getIdcard();
            List<Contact> cs = p.getContacts();
            StringBuilder sb = new StringBuilder();
            if (null != cs) {
                for (Contact c : cs) {

                    if (null!=c) {
                        //System.out.println(c);
                        String typec = c.getContacttype();
                        sb.append(type[Integer.parseInt(null==typec||typec.equals("")||typec.equals("null") ? "0" : typec)]);
                        sb.append(":");
                        sb.append(c.getContactnumber());
                        sb.append("\r\n");
                    }
                }
            }
            PersonToExcel pte;
            pte = new PersonToExcel(
                    p.getPersonname(),
                    idcardUtils.getGenderByIdCard(idcard),
                    idcardUtils.getAgeByIdCard(idcard) + "",
                    idcardUtils.getProvinceByIdCard(idcard),
                    idcard,
                    p.getFixedmail(),
                    sb.toString()
            );
            Job job = p.getJob();
            if (job != null){
                //System.out.println(job);
                Department department = job.getDepartment();
                department = department.getDepart()!= null?department.getDepart():department;
                //System.out.println(department);
                pte.setDepart(department.getDepname());
                pte.setJob(job.getJobname());
            }
            ptes.add(pte);
        }
        easyPoiUtil.exportExcel(ptes, "人员表", "shit1",
                PersonToExcel.class, "人员表.xls", response);
    }

    @PostMapping("upPerson.do")
    @ResponseBody
    public Map loadupPerson(MultipartFile uploadFile){
        List<Person> list = easyPoiUtil.importExcel(uploadFile, 0, 1, Person.class);
        //System.out.println(list);
        Map map = new LinkedHashMap();
        if (list != null && list.size()>0) {
            for (Person p : list) {
                String idcard = p.getIdcard();
                if (p.getPersonname()!=null || idcard !=null ) {
                    if (idcard.length()==18/*idcardUtils.validateCard(p.getIdcard())*/) {
                        this.personService.set(p, null);
                    }else {
                        map.put(p.getPersonname(),"身份证信息不合法："+ idcard);
                    }
                }
            }
        }
        Map ps = this.personService.persons(new ChainMap().putObj("row", 10).putObj("page", 1));
        ps.put("error",map);

        return ps;
    }

    @PostMapping("downModel.do")
    public void down(HttpServletResponse response){
        ModelExcel me = new ModelExcel();
        ArrayList<ModelExcel> list = new ArrayList<>();
        list.add(me);
        easyPoiUtil.exportExcel(list, "shit1",ModelExcel.class, "人员上传模板.xls", response);
        //fu.download("人员上传模板.xls", "template/model.xls",response);
    }

}
