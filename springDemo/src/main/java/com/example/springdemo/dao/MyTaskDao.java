package com.example.springdemo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-08-18 10:03
 **/
@Repository
public class MyTaskDao {
    @Resource(name = "jdbcTemplateOracle")
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getTaskDatas() {
        String sql  = "SELECT ADDRESS_ID ,FAIL_CODE ,TASK_ORDER_NO ,WORKORDER_NO ,HANDLE_USER_ID ,ATTR_VALUE18 ,ATTR_VALUE23 ,TEAM_ID ,SYSTEM_ID ,FAIL_CODE ,CREATE_DATE ,WORKORDER_NO ,TASK_STATUS ,FE_STATUS ,TASK_CODE ,HANDLE_USER_ID , AREA_ID ,TASK_ORDER_NO ,wt.* FROM WF_TASK wt WHERE REQUEST_NO IN  ('LCMOD100823000263') ORDER BY wt.CREATE_DATE ";
        return jdbcTemplate.queryForMap(sql);
    }
}
