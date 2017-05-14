package com.zhaokxkx13.controller;

import com.zhaokxkx13.dao.entity.*;
import com.zhaokxkx13.service.DownloadService;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/29.
 */
@Controller
public class DownloadController {

    @Autowired
    DownloadService downloadService;

    @RequestMapping("/download/employees")
    public void downloadEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=employee.xls");
        List<Employee> employeeList = downloadService.getAllEmployee();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Employee.class, employeeList);
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/download/products")
    public void downloadProducts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=product.xls");
        response.setContentType("application/msexcel;charset=UTF-8");
        List<Product> productList = downloadService.getAllProduct();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Product.class, productList);
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/download/customers")
    public void downloadCustomers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=customer.xls");
        List<Customer> customerList = downloadService.getAllCustomer();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Customer.class, customerList);
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/download/orders")
    public void downloadOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=orders.xls");
        List<Order> orderList = downloadService.getAllOrder();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Order.class, orderList);
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/download/departments")
    public void downloadDepartments(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=department.xls");
        List<Department> departmentList = downloadService.getAllDepartment();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Department.class, departmentList);
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/download/balance")
    public void downloadBalance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=balance.xls");
        List<Balance> balanceList = downloadService.getAllBalance();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Balance.class, balanceList);
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/download/cashflow")
    public void downloadCashFlow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=cashflow.xls");
        List<CashFlow> cashFlowList = downloadService.getAllCashFlow();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), CashFlow.class, cashFlowList);
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/download/profit")
    public void downloadProfit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=profit.xls");
        List<Profit> profitList = downloadService.getAllProfit();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Profit.class, profitList);
        workbook.write(response.getOutputStream());
    }

}
