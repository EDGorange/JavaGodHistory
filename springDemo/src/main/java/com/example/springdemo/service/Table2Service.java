package com.example.springdemo.service;

import com.example.springdemo.entity.Table1;

/**
 * 测试表(Table1)表服务接口
 *
 * @author makejava
 * @since 2023-08-17 15:38:22
 */
public interface Table2Service {

    /**
     * 通过ID查询单条数据
     *
     * @param  主键
     * @return 实例对象
     */
    Table1 queryById(String id);


    Table1 update(Table1 table1);



    boolean updateMethods(String id);
}
