package com.lin.springbootvue_loginexample.demo.service;


import com.lin.springbootvue_loginexample.demo.mapper.UserMapper;
import com.lin.springbootvue_loginexample.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("UserService")
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User queryUser(User user){
            return userMapper.queryByNameAndPassword(user.getName(),user.getPassword());
    }


    public int registerUser(User user){
        return userMapper.insert(user);
    }



}
