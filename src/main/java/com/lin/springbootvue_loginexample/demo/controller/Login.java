package com.lin.springbootvue_loginexample.demo.controller;


import com.lin.springbootvue_loginexample.demo.model.ResponseBean;
import com.lin.springbootvue_loginexample.demo.model.User;
import com.lin.springbootvue_loginexample.demo.service.UserService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class Login {

    @Autowired
    UserService userService;

    @RequestMapping(value = "he")
    @ResponseBody
    public String hello(){
        return "hello";
    }



    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean Login(@RequestParam String name,@RequestParam String password){

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        System.out.println(user);
        User login_user = userService.queryUser(user);

        if (login_user == null) {
            return  ResponseBean.error("不存在");
        }


        return ResponseBean.ok("登录成功");

    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean register(@RequestParam String name,@RequestParam String password){

        User user = new User();
        user.setPassword(password);
        user.setName(name);

        System.out.println(userService.registerUser(user));

        return  ResponseBean.ok("OK");

    }

}
