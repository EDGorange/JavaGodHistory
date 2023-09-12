package com.example.springdemo.service.impl;

import com.example.springdemo.dao.MyTaskDao;
import com.example.springdemo.dao.Table1Dao;
import com.example.springdemo.entity.Table1;
import com.example.springdemo.exception.BaseException;
import com.example.springdemo.inter.em.AppCode;
import com.example.springdemo.service.Table1Service;
import com.example.springdemo.service.Table2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 测试表(Table1)表服务实现类
 *
 * @author makejava
 * @since 2023-08-17 15:38:23
 */
@Service("table1Service")
public class Table1ServiceImpl implements Table1Service {
    @Resource
    private Table1Dao table1Dao;

    @Resource
    private MyTaskDao myTaskDao;

    @Autowired
    private Table2Service table2Service;

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
     * 分页查询
     *
     * @param table1 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Table1> queryByPage(Table1 table1, PageRequest pageRequest) {
        long total = this.table1Dao.count(table1);
        return new PageImpl<>(this.table1Dao.queryAllByLimit(table1, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param table1 实例对象
     * @return 实例对象
     */
    @Override
    public Table1 insert(Table1 table1) {
        this.table1Dao.insert(table1);
        return table1;
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

    /**
     * 通过主键删除数据
     *
     * @param  主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.table1Dao.deleteById(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean transactionTesting(String id) {

        List<Table1> table1s = new ArrayList<>();
        Table1 table1 = new Table1();
        table1.setAge("2");
        table1.setId("2");
        table1.setName("2");
        table1s.add(table1);
        this.table1Dao.insertBatch(table1s);

        table2Service.updateMethods("3");

        return false;
    }

    @Override
    public Map<String, Object> getTasksByRequestNo(String id) throws BaseException {
        if (null == myTaskDao.getTaskDatas()) {
            throw new BaseException(AppCode.PRICE_ERROR, "价格问题");
        }
        return myTaskDao.getTaskDatas();
    }

}
