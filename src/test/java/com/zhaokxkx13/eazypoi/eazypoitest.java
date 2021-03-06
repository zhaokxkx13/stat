package com.zhaokxkx13.eazypoi;

import com.zhaokxkx13.dao.entity.Customer;
import com.zhaokxkx13.dao.entity.Employee;
import com.zhaokxkx13.dao.entity.Income;
import com.zhaokxkx13.dao.inf.CustomerMapper;
import com.zhaokxkx13.dao.inf.EmployeeMapper;
import com.zhaokxkx13.service.IncomeService;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.zhaokxkx13.StatApplication.class)
public class eazypoitest {

    @Autowired
    IncomeService incomeService;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Test
    public void test1() throws IOException {
        List<Income> incomeList = incomeService.getCurYearIncome(2017);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("123123", "test"), Income.class, incomeList);
        File saveFile = new File("d:/test");
        if (!saveFile.exists()) {
            saveFile.mkdir();
        }
        FileOutputStream outputStream = new FileOutputStream("d:/test/1.xls");
        workbook.write(outputStream);
        outputStream.close();
    }

    @Test
    public void test2() throws IOException {
        List<Income> incomeList = incomeService.getCurYearIncome(2017);
        List<Employee> employeeList = employeeMapper.selectAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("123123", "test"), Employee.class, employeeList);
        File saveFile = new File("d:/test");
        if (!saveFile.exists()) {
            saveFile.mkdir();
        }
        FileOutputStream outputStream = new FileOutputStream("d:/test/2.xls");
        workbook.write(outputStream);
        outputStream.close();
    }

    @Test
    public void test3() throws IOException {
        List<Customer> customerList = customerMapper.selectAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("123123", "test"), Customer.class, customerList);
        File saveFile = new File("d:/test");
        if (!saveFile.exists()) {
            saveFile.mkdir();
        }
        FileOutputStream outputStream = new FileOutputStream("d:/test/2.xls");
        workbook.write(outputStream);
        outputStream.close();
    }
}
