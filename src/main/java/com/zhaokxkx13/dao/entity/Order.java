package com.zhaokxkx13.dao.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
public class Order implements Serializable {
    @Excel(name = "id", orderNum = "1")
    private Integer id;
    @Excel(name = "订单日期", orderNum = "2")
    private Date date;
    @Excel(name = "送货员", orderNum = "3")
    private String sender;
    @Excel(name = "签收人", orderNum = "4")
    private String receiver;
    @Excel(name = "收货地址", orderNum = "5")
    private String address;
    @Excel(name = "运费", orderNum = "6")
    private Double freight;
    private Employee employee;
    private Customer customer;
    private List<ProductOrder> productOrderList;

    @Excel(name = "员工编号", orderNum = "7")
    private Integer employeeId;
    @Excel(name = "顾客编号", orderNum = "8")
    private Integer customerId;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", address='" + address + '\'' +
                ", freight=" + freight +
                ", employee=" + employee +
                ", customer=" + customer +
                ", productOrderList=" + productOrderList +
                '}';
    }

    public List<ProductOrder> getProductOrderList() {
        return productOrderList;
    }

    public void setProductOrderList(List<ProductOrder> productOrderList) {
        this.productOrderList = productOrderList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
