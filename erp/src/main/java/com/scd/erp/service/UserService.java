package com.scd.erp.service;


import com.scd.erp.Vo.Person.User;

import java.util.List;

public interface UserService {

    User findUser(User realUser);

    Boolean findByUsername(String username);

    int insertUserMsg(User user);

    List<User> findAll();

    Boolean deleteUserbyID(Integer userID);
}
