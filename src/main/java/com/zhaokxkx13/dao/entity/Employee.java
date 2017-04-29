package com.zhaokxkx13.dao.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
public class Employee implements Serializable {
    @Excel(name = "id", orderNum = "1")
    private Integer id;
    @Excel(name = "姓名", orderNum = "2")
    private String name;
    @Excel(name = "性别", orderNum = "3")
    private String sex;
    @Excel(name = "出生日期", orderNum = "4")
    private Date birth;
    @Excel(name = "职称", orderNum = "5")
    private String rank;
    @Excel(name = "教育水平", orderNum = "6")
    private String education;
    @Excel(name = "员工类型", orderNum = "7")
    private String type;
    @Excel(name = "薪资", orderNum = "8")
    private Double salery;
    @Excel(name = "电话号码", orderNum = "9")
    private String tel;

    private Department department;

    private Company company;
    @Excel(name = "部门编号", orderNum = "10")
    private Integer departmentId;
    @Excel(name = "公司编号", orderNum = "11")
    private Integer companyId;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                ", rank='" + rank + '\'' +
                ", education='" + education + '\'' +
                ", type='" + type + '\'' +
                ", salery=" + salery +
                ", tel='" + tel + '\'' +
                ", department=" + department +
                ", company=" + company +
                ", departmentId=" + departmentId +
                ", companyId=" + companyId +
                '}';
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getSalery() {
        return salery;
    }

    public void setSalery(Double salery) {
        this.salery = salery;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
