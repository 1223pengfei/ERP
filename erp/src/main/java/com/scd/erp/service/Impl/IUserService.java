package com.scd.erp.service.Impl;

import com.scd.erp.service.UserService;
import com.scd.erp.Vo.Person.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scd.erp.mapper.UserMapper;

import java.util.List;

@Service
public class IUserService implements UserService {

    @Autowired
    private UserMapper um;

    public User findUser(User realUser) {
        // TODO Auto-generated method stub
        return (User) this.um.selectOne(realUser);
    }

    @Override
    public Boolean findByUsername(String username) {
        // TODO Auto-generated method stub
        //return um.findByUsername(username);
        return false;
    }


    public int insertUserMsg(User user) {
        // TODO Auto-generated method stub
        return um.insertSelective(user);
    }

    @Override
    public List<User> findAll() {
        return um.selectAll();
    }

    @Override
    public Boolean deleteUserbyID(Integer userID) {
        //return um.deleteByPrimaryKey(userID);

        return false;
    }

}
