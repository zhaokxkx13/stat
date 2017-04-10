package com.zhaokxkx13.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaokxkx13 on 2017/4/10.
 */
public class DepartmentPlan implements Serializable {
    private Integer id;
    private Double planSell;
    private Date date;
    private Department department;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPlanSell() {
        return planSell;
    }

    public void setPlanSell(Double planSell) {
        this.planSell = planSell;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
