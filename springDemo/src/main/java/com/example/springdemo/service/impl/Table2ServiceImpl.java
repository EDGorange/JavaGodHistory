package com.example.springdemo.service.impl;

import com.example.springdemo.dao.Table1Dao;
import com.example.springdemo.entity.Table1;
import com.example.springdemo.service.Table2Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 测试表(Table1)表服务实现类
 *
 * @author makejava
 * @since 2023-08-17 15:38:23
 */
@Service("table2Service")
public class Table2ServiceImpl implements Table2Service {
    @Resource
    private Table1Dao table1Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param  主键
     * @return 实例对象
     */
    @Override
    public Table1 queryById(String id) {
        return this.table1Dao.queryById(id);
    }


    /**
     * 修改数据
     *
     * @param table1 实例对象
     * @return 实例对象
     */
    @Override
    public Table1 update(Table1 table1) {
        this.table1Dao.update(table1);
        return this.queryById(table1.getId());
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public boolean updateMethods(String name) {
            Table1 table1 = queryById("2");
            table1.setName(name);
            this.update(table1);
            int a  = 1 / 0;
            return false;

    }
}
