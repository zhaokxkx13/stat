package com.zhaokxkx13.Bean;

import com.zhaokxkx13.dao.entity.Department;

import java.io.Serializable;

/**
 * Created by zhaokxkx13 on 2017/4/10.
 */
public class DepartmentSellKpi implements Serializable {
    private String departmentName;
    private Double planSell;
    private Double actSell;
    private Double persent;

    public Double getPersent() {
        return persent;
    }

    public void setPersent(Double persent) {
        this.persent = persent;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Double getPlanSell() {
        return planSell;
    }

    public void setPlanSell(Double planSell) {
        this.planSell = planSell;
    }

    public Double getActSell() {
        return actSell;
    }

    public void setActSell(Double actSell) {
        this.actSell = actSell;
    }

    @Override
    public String toString() {
        return "DepartmentSellKpi{" +
                "departmentName='" + departmentName + '\'' +
                ", planSell=" + planSell +
                ", actSell=" + actSell +
                ", persent=" + persent +
                '}';
    }
}
