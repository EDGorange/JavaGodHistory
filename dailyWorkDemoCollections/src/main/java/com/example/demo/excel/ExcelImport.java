package com.example.demo.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.example.demo.dto.Home;
import com.example.demo.excel.entity.ExcelNineSmallPlacesEntity;
import com.example.demo.excel.entity.ExcelNineSmallPlacesResponse;
import com.example.demo.util.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2024-11-18 14:24
 **/

public class ExcelImport {
    public Map<String, Object> importNineSmallData(Home userInfoDto, MultipartFile[] excelFiles) {
        Map<String, Object> map = new HashMap<>();
        //导入数据总数
        int totalCount = 0;
        //失败条数
        int failCount = 0;
        //更新条数
        int updateCount = 0;
        //收集导入失败数据
        List<ExcelNineSmallPlacesResponse> importResponseList = new ArrayList<>();
        if (excelFiles.length != 0) {
            for (MultipartFile excelFile : excelFiles) {
                String userDir = System.getProperties().getProperty("user.dir");
                String uploadFileTemp = "/dir/";
                String uploadFileTemps = userDir + uploadFileTemp;
                File tmpFile = new File(uploadFileTemps);
                if (!tmpFile.exists()) {
                    // 设置写权限，windows下不用此语句
                    tmpFile.mkdirs();
                }
                try {
                    File file = FileUtils.upload(excelFile, uploadFileTemps);
                    ImportParams params = new ImportParams();
                   /* params.setTitleRows(1);
                    params.setHeadRows(1);*/
                    //导入到内存中
                    ExcelImportResult<ExcelNineSmallPlacesEntity> result = ExcelImportUtil.importExcelMore(file, ExcelNineSmallPlacesEntity.class, params);
                    List<ExcelNineSmallPlacesEntity> oldUserExcelEntityList = result.getList();


                    // 过滤空行
                    List<ExcelNineSmallPlacesEntity> userExcelEntityList = oldUserExcelEntityList.stream()
                            .filter(entity -> StringUtils.isNotBlank(entity.getPlaceAddress()))
                            .collect(Collectors.toList());

                    if (userExcelEntityList != null && !userExcelEntityList.isEmpty()) {
                        //导入总条数
                        totalCount = totalCount + userExcelEntityList.size();
                        for (ExcelNineSmallPlacesEntity userExcelEntity : userExcelEntityList) {
                            if (">>>".equalsIgnoreCase(userExcelEntity.getPlaceName())) {
                                break;
                            }


                            if (StringUtils.isBlank(userInfoDto.getAddress())) {
                                ExcelNineSmallPlacesResponse importResponse = convertNineSmallData(userExcelEntity, "组织为空，请先选择导入的组织后重新导入");
                                importResponseList.add(importResponse);
                                failCount++;
                                continue;
                            }
                        }
                    }

                    FileUtils.deleteFile(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //生成下载文件
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("导入失败文件", "导入失败文件"), ExcelNineSmallPlacesResponse.class, importResponseList);
            try {
                String userDir = System.getProperties().getProperty("user.dir");
                String uploadFileTemps = userDir + "/upload/fileTemp";
                //生成所要下载的文件
                String fileName = "ImportResult.xls";
                FileOutputStream outputStream = new FileOutputStream(uploadFileTemps + "/" + fileName);
                workbook.write(outputStream);
                //导入总条数
                map.put("totalNum", totalCount);
                //失败条数
                map.put("failListNum", failCount);
                //更新条数
                map.put("updateNum", updateCount);
                //成功条数
                map.put("addNum", totalCount - failCount - updateCount);
                map.put("fileName", fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return map;
        }
        map.put("message", "上传文件为空");
        return map;

    }


    private ExcelNineSmallPlacesResponse convertNineSmallData(ExcelNineSmallPlacesEntity userExcelEntity, String 更新失败) {
        ExcelNineSmallPlacesResponse importResponse = new ExcelNineSmallPlacesResponse();
        BeanUtils.copyProperties(userExcelEntity, importResponse);
        importResponse.setReason(更新失败);
        return importResponse;
    }

}