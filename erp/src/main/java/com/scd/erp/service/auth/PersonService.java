package com.scd.erp.service.auth;

import com.scd.erp.Vo.Person.Contact;
import com.scd.erp.Vo.Person.Person;

import java.util.List;
import java.util.Map;

public interface PersonService {
    Person set(Person person, List<Contact> contacts);

    boolean del(Integer id);

    Map persons(Map map);

    Person getPersonAll(Integer presonid);

    boolean setContact(List<Contact> contact, Integer personid);

    List<Person> selectByname(String name);

    List<Person> getName();

    Map getAuths();

    void setWord(Integer userid, String password);
}
