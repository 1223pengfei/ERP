package com.scd.erp.service.auth;

import com.scd.erp.Vo.Person.User;

public interface LoginService {
    User getUserByusername(String username);

    User login(User realUser);
}
