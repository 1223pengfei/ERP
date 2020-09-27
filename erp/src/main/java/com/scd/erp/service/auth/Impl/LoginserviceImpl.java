package com.scd.erp.service.auth.Impl;

import com.scd.erp.Vo.Person.User;
import com.scd.erp.mapper.UserMapper;
import com.scd.erp.service.auth.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class LoginserviceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUserByusername(String username) {
        Example ex = new Example(User.class);
        ex.createCriteria().andEqualTo("username", username);
        return this.userMapper.selectOneByExample(ex);
    }

    @Override
    public User login(User user) {
        Example ex = new Example(User.class);
        ex.createCriteria().andEqualTo("username", user.getUsername()).andEqualTo("password", user.getPassword());
        return this.userMapper.selectOneByExample(ex);
    }


}
