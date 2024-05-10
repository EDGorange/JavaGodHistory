package com.example.demo.test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2024-03-21 17:14
 **/

public class TestMethods {
    public static void main(String[] args) {
        //test();
        //System.out.println(getMapperClassName("driver_risk_control_new"));
        generateSql();
    }


    public static Class getMapperClassName(String tableName) {
        //1: tableCode格式都为下划线格式，转为对应的驼峰名称+Mapper即为mapperClass名称。
        if (tableName == null || tableName.isEmpty()) {
            throw new IllegalArgumentException("Table name cannot be null or empty");
        }

        int a = 10;
        System.out.println(Optional.ofNullable(a).orElse(2));

        // 转换为小写
        String lowercaseTableName = tableName.toLowerCase();

        // 使用正则表达式将下划线分隔的单词转换为驼峰命名
        String camelCaseName = Arrays.stream(lowercaseTableName.split("_"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(""));

        // 添加Mapper后缀
        String mapperClassName = camelCaseName + "Mapper";
        try {
            return Class.forName(mapperClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("获取mapperClass出错：" + e);
        }


    }

    public static void test() {
        String cycleCode = "last30Days";
        String[] parts = cycleCode.split("\\d+");

        if (parts.length > 1) {
            String result = parts[1];
            System.out.println("数字后面的字符串为: " + result);
            int beginLastNum = 0;
            LocalDateTime beginLocalTime = null;
            LocalDateTime now = LocalDateTime.now();
            try {
                beginLastNum = Integer.parseInt("-30".split("-")[1]) * Integer.parseInt("3");

                switch (result) {
                    case "Days":
                        beginLocalTime = now.minusDays(beginLastNum);
                        break;
                    case "Months":
                        beginLocalTime = now.minusMonths(beginLastNum);
                        break;
                    case "Weeks":
                        beginLocalTime = now.minusWeeks(beginLastNum);
                        break;
                    case "Years":
                        beginLocalTime = now.minusYears(beginLastNum);
                        break;
                    default:
                        System.out.println("未找到匹配的内容");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("无法将字符串转换为数字: " + e.getMessage());
            }
            System.out.println(beginLastNum);
            System.out.println(beginLocalTime);


        }

    }

    public static void generateSql() {
        String[] strings = new String[]{
                    "李云峰;003891;旬邑县公安局太村派出所;综合指挥室;13259100322;民警;男;所长;48",
                "文龙;F18176;旬邑县公安局太村派出所;综合指挥室;13892983252;辅警;男;综合指挥室辅警;42",
                "马金虎;F18056;旬邑县公安局太村派出所;综合指挥室;18840383175;辅警;男;综合指挥室辅警;35",
                "陈东;040564;旬邑县公安局太村派出所;社区警务队;15229706461;民警;男;社区警务队负责人;40",
                "尹翌;F18144;旬邑县公安局太村派出所;社区警务队;15289406612;辅警;男;社区警务队辅警;41",
                "张涛;F18305;旬邑县公安局太村派出所;社区警务队;18220033928;辅警;男;社区警务队辅警;23",
                "张佳乐;F18309;旬邑县公安局太村派出所;社区警务队;17342982015;辅警;男;社区警务队辅警;24",
                "何鑫;F18097;旬邑县公安局太村派出所;社区警务队;15291403802;辅警;男;社区警务队辅警;30",
                "杨明;F18057;旬邑县公安局太村派出所;社区警务队;18291078625;辅警;男;社区警务队辅警;38",
                "门涛涛;F18280;旬邑县公安局太村派出所;社区警务队;18509102218;辅警;男;社区警务队辅警;32",
                "王鹏飞;F18108;旬邑县公安局太村派出所;社区警务队;13892993992;民警;男;社区警务队民警;40",
                "蒲东东;F18226;旬邑县公安局太村派出所;社区警务队;13892993992;辅警;男;社区警务队辅警;40",
                "周涛;040603;旬邑县公安局太村派出所;案件办理队;15289408769;民警;男;案件办理队负责人;39",
                "李贵平;061069;旬邑县公安局太村派出所;案件办理队;17730667159;民警;男;案件办理队负责人;47",
                "马旭东;061661;旬邑县公安局太村派出所;案件办理队;13259100310;民警;男;案件办理队民警;33",
                "许超;003819;旬邑县公安局太村派出所;案件办理队;15877618681;民警;男;案件办理队民警;37",
                "刘诚;F18245;旬邑县公安局太村派出所;案件办理队;18740504519;辅警;男;案件办理队辅警;34",
                "马明;F18107;旬邑县公安局太村派出所;案件办理队;15289407301;辅警;男;案件办理队辅警;37",
                "卢东科;F18300;旬邑县公安局太村派出所;案件办理队;15191033837;辅警;男;案件办理队辅警;27",
                "郭凯;F18073;旬邑县公安局太村派出所;案件办理队;15591034727;辅警;男;案件办理队辅警;33"
        };
//-- 太村派出所orgId:7f84300b6456443fa3729aae3f023d02  orgcode:123
//
//-- 警员信息插入：
//INSERT INTO cap.cap_user
//(  police_number, user_id, password, password_invaldate, invaldate, user_name, authmode, status, unlocktime, menutype, lastlogin, errcount, startdate, enddate, validtime, maccode, ipaddress, email, createuser, createtime, sex, first_landing, updatetime, extra_attributes, user_from)
//VALUES(  '009891', '009891', 'BA5237CF9CE91F77CA3E3F4510196EC4', '2039-04-25', '2030-07-23', '李云峰', NULL, 1, NULL, NULL, '2023-02-01 14:09:12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-02-24 10:51:05', '1', NULL, '2022-08-23 16:09:42', NULL, 0);
//
//
//
//
//-- 警员信息：
//
//INSERT INTO cap.org_employee
//( empcode, operatorid, userid, empname, realname, gender, birthdate, `position`, empstatus, cardtype, cardno, indate, outdate, otel, oaddress, ozipcode, oemail, faxno, mobileno, qq, htel, haddress, hzipcode, pemail, party, `degree`, sortno, major, specialty, workexp, regdate, createtime, lastmodytime, orgidlist, orgid, remark, tenant_id, app_id, weibo, business_address, identity_type, headimg)
//VALUES( '17801130126', 303998, '500103', '信息', '', '0', '2019-02-25', 35, '', '', '421023198607220414', '2019-02-25', '2019-02-25', '', '', '', '', '', '17801130126', '', '', '', '', '', '', '', NULL, NULL, '', '', '2019-02-25', '2018-08-01 16:18:54', '2024-04-08 09:03:58', '', NULL, '', '', '', '', '', '', NULL);
//
//
//
//-- 警员和用户关联信息：
//INSERT INTO cap.org_emporg
//(status, orgid, empid, ismain, tenant_id, app_id)
//VALUES('0', '500103660000', 1078, NULL, NULL, '0' );

        for (int i = 0; i < strings.length; i++) {
            String[] split = strings[i].split(";");

            //-- 警员信息插入：
//INSERT INTO cap.cap_user
//(  police_number, user_id, password, password_invaldate, invaldate, user_name, authmode, status, unlocktime, menutype, lastlogin, errcount, startdate, enddate, validtime, maccode, ipaddress, email, createuser, createtime, sex, first_landing, updatetime, extra_attributes, user_from)
//VALUES(  '009891', '009891', 'BA5237CF9CE91F77CA3E3F4510196EC4', '2039-04-25', '2030-07-23', '李云峰', NULL, 1, NULL, NULL, '2023-02-01 14:09:12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-02-24 10:51:05', '1', NULL, '2022-08-23 16:09:42', NULL, 0);
//把split切割后组装成上面描述得插入语句
            StringBuilder capInsertSql = new StringBuilder();
            capInsertSql.append("INSERT INTO cap.cap_user\n" +
                    "(  police_number, user_id, password, password_invaldate, invaldate, user_name, authmode, status, unlocktime, menutype, lastlogin, errcount, startdate, enddate, validtime, maccode, ipaddress, email, createuser, createtime, sex, first_landing, updatetime, extra_attributes, user_from)\n" +
                    "VALUES(  '");
            capInsertSql.append(split[1]).append("', '").append(split[1]).
                    append("', 'BA5237CF9CE91F77CA3E3F4510196EC4', '2039-04-25', '2030-07-23', '")
                    .append(split[0]).append("', NULL, 1, NULL, NULL, '2023-02-01 14:09:12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-02-24 10:51:05', '1', NULL, '")
                    .append("'2022-08-23 16:09:42'").append("', NULL, 0);");

            System.out.println(capInsertSql);

            //-- 警员信息：
            //
            //INSERT INTO cap.org_employee
            //( empcode, operatorid, userid, empname, realname, gender, birthdate, `position`, empstatus, cardtype, cardno, indate, outdate, otel, oaddress, ozipcode, oemail, faxno, mobileno, qq, htel, haddress, hzipcode, pemail, party, `degree`, sortno, major, specialty, workexp, regdate, createtime, lastmodytime, orgidlist, orgid, remark, tenant_id, app_id, weibo, business_address, identity_type, headimg)
            //VALUES( '17801130126', 303998, '500103', '信息', '', '0', '2019-02-25', 35, '', '', '421023198607220414', '2019-02-25', '2019-02-25', '', '', '', '', '', '17801130126', '', '', '', '', '', '', '', NULL, NULL, '', '', '2019-02-25', '2018-08-01 16:18:54', '2024-04-08 09:03:58', '', NULL, '', '', '', '', '', '', NULL);
            StringBuilder orgEmployeeInsertSql = new StringBuilder();
            orgEmployeeInsertSql.append("INSERT INTO cap.org_employee\n" +
                    "( empcode, operatorid, userid, empname, realname, gender, birthdate, `position`, empstatus, cardtype, cardno, indate, outdate, otel, oaddress, ozipcode, oemail, faxno, mobileno, qq, htel, haddress, hzipcode, pemail, party, `degree`, sortno, major , specialty, workexp, regdate, createtime, lastmodytime, orgidlist, orgid, remark, tenant_id, app_id, weibo, business_address, identity_type, headimg)\n" +
                    "VALUES( '").append(split[4])
                    .append("', ")
                    .append(" '")
                    .append(304023 + i+1)
                    .append("', ")
                    .append(" '")
                    .append(split[1])
                    .append("', '").append(split[0]).append("', '', '").append(0).append("', '").append("2019-02-25").append("',").append(split[8]).append(", '', '', '7f84300b6456443fa3729aae3f023d02', ")
                            .append("'2019-02-25', '2019-02-25', '', '', '', '', '',")
                                    .append("'").append(split[4]).append("', ")
                    .append("'', '', '', '', '', '', '', NULL, NULL, '', '', '2019-02-25', '2018-08-01 16:18:54', '2024-04-08 09:03:58', '', NULL, '', '', '', '', '', '', NULL").append(");");


            System.out.println(orgEmployeeInsertSql);

            //-- 警员和用户关联信息：
            //INSERT INTO cap.org_emporg
            //        //(status, orgid, empid, ismain, tenant_id, app_id)
            //        //VALUES('0', '500103660000', 1078, NULL, NULL, '0' );
            StringBuilder orgEmployeeOrgInsertSql = new StringBuilder();
            orgEmployeeOrgInsertSql.append("INSERT INTO cap.org_emporg\n" +
                    "(status, orgid, empid, ismain, tenant_id, app_id)\n" +
                    "VALUES('0', '7f84300b6456443fa3729aae3f023d02', ").append(1106 + i +1).append(", NULL, NULL, '0' );");

            System.out.println(orgEmployeeOrgInsertSql);
            //换两行
            System.out.println();
            System.out.println();

        }



            }
    }

