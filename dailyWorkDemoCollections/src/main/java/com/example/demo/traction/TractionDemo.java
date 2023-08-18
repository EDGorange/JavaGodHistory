package com.example.demo.traction;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-08-17 14:01
 **/

public class TractionDemo {
    @Transactional
    public void test1() {
        Map<String, String> updateData = selectTableAByState();
        Map<String, String> otherData = new HashMap<>();
        insertTableA(otherData);
        updateTableA(updateData);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateTableA(Map<String, String> updateData) {
        //更新tableA
        Map<String, String> stringStringMap = selectTableAByState();
    }

    public void insertTableA(Map<String, String> otherData) {

    }

    public Map<String, String> selectTableAByState() {
        //查询TableA中state为X得数据
        Map<String, String> objectObjectHashMap = new HashMap<>();
        return objectObjectHashMap;
    }

    public  void main(String[] args) {
        test1();
    }
}
