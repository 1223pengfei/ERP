package com.scd.erp.utils.extraUtil;

import com.scd.erp.Vo.Department.Department;
import com.scd.erp.Vo.Person.Person;
import com.scd.erp.Vo.Person.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtile {

    private static Integer PERSONID = 1;
    private static Integer DEPID = 1;
    private static Integer USERID = 1;



    public  Integer getpersonid(HttpServletRequest request) {
       return getpersonid(request.getSession());
    }
    public  Integer getpersonid(HttpSession session) {
        Object obj = session.getAttribute("user");
        Integer presonid = PERSONID;
        if (null != obj) {
            User user = (User) obj;
            presonid = user.getPresonid();
        }
        return presonid;
    }

    public  User getUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setPresonid(PERSONID);
            user.setUserid(USERID);
        }
        return user;
    }

    public  User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return getUser(session);
    }

    public  Integer getDepID(HttpServletRequest request) {
       return getDepID(request.getSession());
    }

    public Integer getDepID(HttpSession session) {
        Person person = (Person)session.getAttribute("person");
        if(null != person) {
            Department department = person.getJob().getDepartment();
            DEPID = department.getUpdepid()==0?department.getDepartmentid():department.getUpdepid();
        }
        return DEPID;
    }
}
