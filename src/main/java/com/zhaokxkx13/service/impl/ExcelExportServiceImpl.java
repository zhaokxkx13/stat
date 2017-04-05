package com.zhaokxkx13.service.impl;

import com.zhaokxkx13.service.ExcelExportService;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/4.
 */
@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    @Override
    public Workbook generateOutputStream(String title, String sheetName, List data, Class clazz) {
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName), clazz, data);
            return workbook;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
