package com.zhaokxkx13.controller;

import com.zhaokxkx13.dao.entity.*;
import com.zhaokxkx13.service.ImportService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/30.
 */
@Controller
public class UploadController {

    private static final String SUCCESS = "/download";
    @Autowired
    ImportService importService;

    @RequestMapping(path = "/upload/employee", method = RequestMethod.POST)
    public String uploadEmployee(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("uploadExcels/" + file.getOriginalFilename())));
        out.write(file.getBytes());
        out.flush();
        out.close();
        File localFile = new File("uploadExcels/" + file.getOriginalFilename());
        List<Employee> employeeList = ExcelImportUtil.importExcel(localFile, Employee.class, new ImportParams());
        importService.importEmployee(employeeList);
        modelMap.put("employeeStatus", "success");
        return SUCCESS;
    }

    @RequestMapping(path = "/upload/product", method = RequestMethod.POST)
    public String uploadProduct(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("uploadExcels/" + file.getOriginalFilename())));
        out.write(file.getBytes());
        out.flush();
        out.close();
        File localFile = new File("uploadExcels/" + file.getOriginalFilename());
        List<Product> productList = ExcelImportUtil.importExcel(localFile, Product.class, new ImportParams());
        importService.importProduct(productList);
        modelMap.put("productStatus", "success");
        return SUCCESS;
    }

    @RequestMapping(path = "/upload/customer", method = RequestMethod.POST)
    public String uploadCustomer(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("uploadExcels/" + file.getOriginalFilename())));
        out.write(file.getBytes());
        out.flush();
        out.close();
        File localFile = new File("uploadExcels/" + file.getOriginalFilename());
        List<Customer> customerList = ExcelImportUtil.importExcel(localFile, Customer.class, new ImportParams());
        importService.importCustomer(customerList);
        modelMap.put("customerStatus", "success");
        return SUCCESS;
    }

    @RequestMapping(path = "/upload/order", method = RequestMethod.POST)
    public String uploadOrder(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("uploadExcels/" + file.getOriginalFilename())));
        out.write(file.getBytes());
        out.flush();
        out.close();
        File localFile = new File("uploadExcels/" + file.getOriginalFilename());
        List<Order> orderList = ExcelImportUtil.importExcel(localFile, Order.class, new ImportParams());
        importService.importOrders(orderList);
        modelMap.put("orderStatus", "success");
        return SUCCESS;
    }

    @RequestMapping(path = "/upload/department", method = RequestMethod.POST)
    public String uploadDepartment(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("uploadExcels/" + file.getOriginalFilename())));
        out.write(file.getBytes());
        out.flush();
        out.close();
        File localFile = new File("uploadExcels/" + file.getOriginalFilename());
        List<Department> departmentList = ExcelImportUtil.importExcel(localFile, Department.class, new ImportParams());
        importService.importDepartment(departmentList);
        modelMap.put("departmentStatus", "success");
        return SUCCESS;
    }

    @RequestMapping(path = "/upload/balance", method = RequestMethod.POST)
    public String uploadBalance(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("uploadExcels/" + file.getOriginalFilename())));
        out.write(file.getBytes());
        out.flush();
        out.close();
        File localFile = new File("uploadExcels/" + file.getOriginalFilename());
        List<Balance> balanceList = ExcelImportUtil.importExcel(localFile, Balance.class, new ImportParams());
        importService.importBalance(balanceList);
        modelMap.put("balanceStatus", "success");
        return SUCCESS;
    }

    @RequestMapping(path = "/upload/profit", method = RequestMethod.POST)
    public String uploadProfit(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("uploadExcels/" + file.getOriginalFilename())));
        out.write(file.getBytes());
        out.flush();
        out.close();
        File localFile = new File("uploadExcels/" + file.getOriginalFilename());
        List<Profit> profitList = ExcelImportUtil.importExcel(localFile, Profit.class, new ImportParams());
        importService.importProfit(profitList);
        modelMap.put("profitStatus", "success");
        return SUCCESS;
    }

    @RequestMapping(path = "/upload/cashflow", method = RequestMethod.POST)
    public String uploadCashFlow(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("uploadExcels/" + file.getOriginalFilename())));
        out.write(file.getBytes());
        out.flush();
        out.close();
        File localFile = new File("uploadExcels/" + file.getOriginalFilename());
        List<CashFlow> cashFlowList = ExcelImportUtil.importExcel(localFile, CashFlow.class, new ImportParams());
        importService.importCashFlow(cashFlowList);
        modelMap.put("cashflowStatus", "success");
        return SUCCESS;
    }
}
