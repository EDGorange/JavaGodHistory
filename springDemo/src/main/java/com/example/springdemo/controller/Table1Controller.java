package com.example.springdemo.controller;

import com.example.springdemo.entity.ResultVo;
import com.example.springdemo.entity.Table1;
import com.example.springdemo.service.Table1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 测试表(Table1)表控制层
 *
 * @author makejava
 * @since 2023-08-17 15:37:44
 */
@Slf4j
@RestController
@RequestMapping("table1")
public class Table1Controller {
    /**
     * 服务对象
     */
    @Resource
    private Table1Service table1Service;

    /**
     * 分页查询
     *
     * @param table1 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Table1>> queryByPage(Table1 table1, PageRequest pageRequest) {
        return ResponseEntity.ok(this.table1Service.queryByPage(table1, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Table1> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.table1Service.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param table1 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Table1> add(Table1 table1) {
        return ResponseEntity.ok(this.table1Service.insert(table1));
    }

    /**
     * 编辑数据
     *
     * @param table1 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Table1> edit(Table1 table1) {
        return ResponseEntity.ok(this.table1Service.update(table1));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.table1Service.deleteById(id));
    }

    @RequestMapping(value = "transactionTesting/v1", method = RequestMethod.GET)
    public ResponseEntity<Boolean> transactionTesting(String id) {
        return ResponseEntity.ok(this.table1Service.transactionTesting(id));
    }

    @RequestMapping(value = "getTasksByRequestNo/v1", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getTasksByRequestNo(String id) {
        return ResponseEntity.ok(this.table1Service.getTasksByRequestNo(id));
    }

    @RequestMapping(value = "getTasksByRequestNo/v2", method = RequestMethod.GET)
    public ResultVo getTasksByRequestNo2(String id) {
        return new ResultVo(this.table1Service.getTasksByRequestNo(id));
    }

}

