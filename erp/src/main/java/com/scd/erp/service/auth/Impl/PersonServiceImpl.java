package com.scd.erp.service.auth.Impl;

import com.scd.erp.Vo.Person.Contact;
import com.scd.erp.Vo.Person.Person;
import com.scd.erp.Vo.Person.User;
import com.scd.erp.mapper.*;
import com.scd.erp.service.auth.PersonService;
import com.scd.erp.utils.common.CipherUtil;
import com.scd.erp.utils.common.itCodeUtils;
import com.scd.erp.utils.extraUtil.ChainMap;
import com.scd.erp.utils.extraUtil.ExampleUtile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);
    private final static String SINCODEST_COM = "@sincodest.com";

    private final PersonMapper personMapper;
    private final UserMapper userMapper;
    private final ContactMapper contactMapper;
    private final RuleMapper ruleMapper;
    private final PermissionMapper permissionMapper;

    private ExampleUtile exampleUtile;

    @Autowired
    public PersonServiceImpl(PersonMapper personMapper, UserMapper userMapper, ContactMapper contactMapper, RuleMapper ruleMapper, PermissionMapper permissionMapper) {
        this.personMapper = personMapper;
        this.userMapper = userMapper;
        this.contactMapper = contactMapper;
        this.ruleMapper = ruleMapper;
        this.permissionMapper = permissionMapper;
        this.exampleUtile =new ExampleUtile();
    }

    @Override
    public Person set(Person person, List<Contact> contacts) {
        Integer personid = person.getPersonid();
        User user = new User();
        if (personid != null ) {
            if(personid != 1)this.personMapper.updateByPrimaryKeySelective(person);
        } else {
            user = insertPerson(person);
            personid = person.getPersonid();
            if (contacts != null ) {
                for (Contact c : contacts) {
                    if (!c.getContactnumber().equals("")&&!c.getContacttype().equals(""))
                    c.setPersonid(personid);
                }
                setContact(contacts, personid);
            }
        }

        person.setContacts(contacts);
        person.setUser(user);
        return person;
    }

    @Override
    public Map persons(Map maps) {
        //System.out.println(maps.size());
        List<Person> list = this.personMapper.List(maps);
        int i = this.personMapper.getCount();
        return new ChainMap().putObj("count", i).putObj("data", list);
    }

    @Override
    public Person getPersonAll(Integer presonid) {
        return this.personMapper.getPersonAll(presonid);
    }

    @Override
    public boolean del(Integer id) {
        if(id != 1) {
            Example userExa = new Example(User.class);
            userExa.createCriteria().andEqualTo("presonid", id);
            this.userMapper.deleteByExample(userExa);

            Example contExa = new Example(Contact.class);
            contExa.createCriteria().andEqualTo("personid", id);
            this.contactMapper.deleteByExample(contExa);

            this.personMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }


    public boolean setContact(List<Contact> c,Integer personid) {

        Example ex = exampleUtile.getExample(Contact.class, new ChainMap().putObj("personid", personid));
        this.contactMapper.deleteByExample(ex);
        for (Contact cs:c) {
            cs.setPersonid(personid);
            this.contactMapper.insertSelective(cs);
        }

        return true;
    }

    @Override
    public List<Person> selectByname(String name) {
        return getPersons("person", name);
    }

    @Override
    public List<Person> getName() {
        return this.personMapper.getNames();
    }

    @Override
    public Map getAuths() {
        return new ChainMap()
                .putObj("rule",this.ruleMapper.selectAll())
                .putObj("premission",this.permissionMapper.getpermission());
    }

    @Override
    public void setWord(Integer userid, String password) {
         User user = new User();
         user.setUserid(userid);
         user.setPassword(password);
         this.userMapper.updateByPrimaryKeySelective(user);
    }

    private List<Person> getPersons(String key, String value) {
        Example ex = new Example(Person.class);
        ex.createCriteria().andLike(key, "%" + value + "%");
        return this.personMapper.selectByExample(ex);
    }

    private User insertPerson(Person person) {
        String personname = person.getPersonname();
        //System.out.println(personname);
        String idcard = person.getIdcard();
        String code = null;

        try {
            code = new itCodeUtils().SCDCode(personname);
            String mail = code + SINCODEST_COM;
            Example ex = exampleUtile.getExample(Person.class, new ChainMap().putObj("fixedmail", mail));
            List<Person> list = this.personMapper.selectByExample(ex);
            if (list.size() > 0){
                mail = code +"1"+ SINCODEST_COM;
            }
            person.setFixedmail(mail);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        this.personMapper.insertSelective(person);


        Integer personid = person.getPersonid();
        User user = new User();
        user.setUsername(code);
        String pwd = idcard.substring(idcard.length() - 6, idcard.length());
        user.setPassword(CipherUtil.generatePassword(pwd));
        user.setPresonid(personid);
        this.userMapper.insertSelective(user);
        user.setPassword(pwd);
        return user;
    }
}
