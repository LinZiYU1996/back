package com.lin.springbootvue_loginexample.demo.mapper;


import com.lin.springbootvue_loginexample.demo.model.User;



public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User queryByNameAndPassword(String name,String password);
}