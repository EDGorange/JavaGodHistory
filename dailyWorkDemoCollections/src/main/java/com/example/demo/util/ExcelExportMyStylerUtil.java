package com.example.demo.util;

import cn.afterturn.easypoi.excel.export.styler.AbstractExcelExportStyler;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @ClassName: ExcelExportMyStylerImpl
 * @Description: 自定义报表导出样式，可以修改表头颜色，高度等
 * @Author: sunt
 * @Date: 2019/8/29 21:39
 * @Version 1.0
 **/
public class ExcelExportMyStylerUtil extends AbstractExcelExportStyler implements IExcelExportStyler {
    public ExcelExportMyStylerUtil(Workbook workbook) {
        super.createStyles(workbook);
    }

    @Override
    public CellStyle getTitleStyle(short color) {
        CellStyle titleStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);// 加粗
        titleStyle.setFont(font);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);// 居中
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        titleStyle.setFillForegroundColor(IndexedColors.AQUA.index);// 设置颜色
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setWrapText(true);
        return titleStyle;
    }

    @SuppressWarnings("deprecation")
    @Override
    public CellStyle stringSeptailStyle(Workbook workbook, boolean isWarp) {
        CellStyle style = workbook.createCellStyle();
        style.setDataFormat(STRING_FORMAT);
        if (isWarp) {
            style.setWrapText(true);
        }
        return style;
    }

    @Override
    public CellStyle getHeaderStyle(short color) {
        CellStyle titleStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);// 加粗
        font.setColor(IndexedColors.RED.index);
        font.setFontHeightInPoints((short) 11);
        titleStyle.setFont(font);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);// 居中
        titleStyle.setFillForegroundColor(IndexedColors.WHITE.index);// 设置颜色
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setWrapText(true);
        return titleStyle;
    }

    @SuppressWarnings("deprecation")
    @Override
    public CellStyle stringNoneStyle(Workbook workbook, boolean isWarp) {
        CellStyle style = workbook.createCellStyle();
        style.setDataFormat(STRING_FORMAT);
        if (isWarp) {
            style.setWrapText(true);
        }
        return style;
    }
}
