package com.example.springdemo.entity;

import java.io.Serializable;

/**
 * 测试表(Table1)实体类
 *
 * @author makejava
 * @since 2023-08-17 15:38:21
 */
public class Table1 implements Serializable {
    private static final long serialVersionUID = 801396457994771481L;
    
    private String id;
    
    private String name;
    
    private String age;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}

