package com.lin.springbootvue_loginexample.demo.model;


import lombok.Getter;
import lombok.Setter;

/**
 * 封装响应信息，用以返回给前端
 */


@Setter
@Getter
public class ResponseBean {

    /*状态码*/
    private Integer status;

    /*具体信息*/
    private String msg;

    /*需要传递的对象*/
    private Object object;


    private ResponseBean(){

    }

    /*私有构造函数*/
    private ResponseBean(Integer status,String msg,Object object) {
        this.msg = msg;
        this.status = status;
        this.object = object;
    }

    /*返回一个空的对象*/
    public static ResponseBean build(){
        return new ResponseBean();
    }

    /* 200状态码(信息以及对象不为空) */
    public static ResponseBean ok(String msg,Object o){
        return new ResponseBean(200,msg,o);
    }

    /*200状态码(信息不为空)*/
    public static ResponseBean ok(String msg){
        return  new ResponseBean(200,msg,null);
    }


    /*500状态码*/
    public static ResponseBean error(String msg,Object object){
        return new ResponseBean(500,msg,object);
    }

    /*500状态码*/
    public static ResponseBean error(String msg){
        return new ResponseBean(500,msg,null);
    }









}
