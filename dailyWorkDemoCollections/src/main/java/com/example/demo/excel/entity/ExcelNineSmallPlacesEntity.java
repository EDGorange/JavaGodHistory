package com.example.demo.excel.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2024-11-04 17:10
 **/


@Data
@NoArgsConstructor
public class ExcelNineSmallPlacesEntity {

    @Excel(name = "单位名称",  orderNum = "2")
    @ApiModelProperty("场所名称")
    private String placeName;



    /**
     * 身份证标识/统一信用代码
     */
    @Excel(name = "统一信用代码",  orderNum = "3")
    @ApiModelProperty("统一信用代码")
    private String peopleCard;


    /**
     * 场所地址信息
     */
    @Excel(name = "地址",  orderNum = "4")
    @ApiModelProperty("场所地址信息")
    private String placeAddress;

    /**
     * 场所类型
     */
    @Excel(name = "场所类型",  orderNum = "5")
    @ApiModelProperty("场所类型")
    private String placeType;



    /**
     * 警务区名称
     */
    @Excel(name = "警务区",  orderNum = "6")
    @ApiModelProperty("警务区名称")
    private String abilitrName;




    /**
     * 主要负责人姓名
     */
    @Excel(name = "主要负责人",  orderNum = "7")
    @ApiModelProperty("主要负责人姓名")
    private String contacts;

    /**
     * 联系人电话
     */
    @Excel(name = "联系电话",  orderNum = "8")
    @ApiModelProperty("联系人电话")
    private String contactsPohone;


    /**
     * 法定代表姓名
     */
    @Excel(name = "法人姓名",  orderNum = "9")
    @ApiModelProperty("法定代表姓名")
    private String legalPersonName;


    /**
     * 法定代表电话
     */
    @Excel(name = "法人联系方式",  orderNum = "10")
    @ApiModelProperty("法定代表电话")
    private String legalPersonPhone;



    /**
     * 法定代表身份证
     */
    @Excel(name = "法人身份证号",  orderNum = "11")
    @ApiModelProperty("法定代表身份证")
    private String legalPersonCard;




    /**
     * 员工人数
     */
    @Excel(name = "员工数",  orderNum = "12")
    @ApiModelProperty("员工人数")
    private Long employeeCount;

    /**
     * 所在建筑总层数
     */
    @Excel(name = "所在建筑总层数",  orderNum = "13")
    @ApiModelProperty("所在建筑总层数")
    private Long layersCount;

    /**
     * 所处层数
     */
    @Excel(name = "所处层数",  orderNum = "14")
    @ApiModelProperty("所处层数")
    private Long placeCount;

    /**
     * 建筑面积、餐位、床位或容纳人数
     */
    @Excel(name = "面积",  orderNum = "15")
    @ApiModelProperty("面积")
    private String capacity;


    @ApiModelProperty("组织Id")
    private String orgId;

}
