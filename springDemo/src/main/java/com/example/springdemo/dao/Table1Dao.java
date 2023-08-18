package com.example.springdemo.dao;

import com.example.springdemo.entity.Table1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 测试表(Table1)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-17 15:38:19
 */
@Mapper
public interface Table1Dao {

    /**
     * 通过ID查询单条数据
     *
     * @param  主键
     * @return 实例对象
     */
    Table1 queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param table1 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Table1> queryAllByLimit(Table1 table1, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param table1 查询条件
     * @return 总行数
     */
    long count(Table1 table1);

    /**
     * 新增数据
     *
     * @param table1 实例对象
     * @return 影响行数
     */
    int insert(Table1 table1);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Table1> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Table1> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Table1> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Table1> entities);

    /**
     * 修改数据
     *
     * @param table1 实例对象
     * @return 影响行数
     */
    int update(Table1 table1);

    /**
     * 通过主键删除数据
     *
     * @param  主键
     * @return 影响行数
     */
    int deleteById(String id);

}

