package com.zhaokxkx13.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/4.
 */
public interface ExcelExportService {
    Workbook generateOutputStream(String title, String sheetName, List data, Class clazz);
}
