package com.example.springdemo.service;

import com.example.springdemo.entity.Table1;
import com.example.springdemo.exception.BaseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * 测试表(Table1)表服务接口
 *
 * @author makejava
 * @since 2023-08-17 15:38:22
 */
public interface Table1Service {

    /**
     * 通过ID查询单条数据
     *
     * @param  主键
     * @return 实例对象
     */
    Table1 queryById(String id);

    /**
     * 分页查询
     *
     * @param table1 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Table1> queryByPage(Table1 table1, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param table1 实例对象
     * @return 实例对象
     */
    Table1 insert(Table1 table1);

    /**
     * 修改数据
     *
     * @param table1 实例对象
     * @return 实例对象
     */
    Table1 update(Table1 table1);

    /**
     * 通过主键删除数据
     *
     * @param  主键
     * @return 是否成功
     */
    boolean deleteById(String id );

    boolean transactionTesting(String id);

    Map getTasksByRequestNo(String id) throws BaseException;
}
