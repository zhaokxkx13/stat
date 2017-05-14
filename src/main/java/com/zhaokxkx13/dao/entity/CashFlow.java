package com.zhaokxkx13.dao.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaokxkx13 on 2017/4/15.
 */
public class CashFlow implements Serializable {
    @Excel(name = "id", orderNum = "1")
    private Integer id;
    @Excel(name = "经营活动产生的现金流量", orderNum = "2")
    private Double operateCash;
    @Excel(name = "投资活动产生的现金流量", orderNum = "3")
    private Double investmentCash;
    @Excel(name = "筹资活动产生的现金流量", orderNum = "4")
    private Double financeCash;
    @Excel(name = "汇率变动产生的现金流量", orderNum = "5")
    private Double exchangeCash;
    @Excel(name = "现金及现金等价物净增加额", orderNum = "6")
    private Double cashIncrease;
    @Excel(name = "日期", orderNum = "7")
    private Date date;

    @Override
    public String toString() {
        return "CashFlow{" +
                "id=" + id +
                ", operateCash=" + operateCash +
                ", investmentCash=" + investmentCash +
                ", financeCash=" + financeCash +
                ", exchangeCash=" + exchangeCash +
                ", cashIncrease=" + cashIncrease +
                ", date=" + date +
                '}';
    }

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

    public Double getOperateCash() {
        return operateCash;
    }

    public void setOperateCash(Double operateCash) {
        this.operateCash = operateCash;
    }

    public Double getinvestmentCash() {
        return investmentCash;
    }

    public void setinvestmentCash(Double investmentCash) {
        this.investmentCash = investmentCash;
    }

    public Double getFinanceCash() {
        return financeCash;
    }

    public void setFinanceCash(Double financeCash) {
        this.financeCash = financeCash;
    }

    public Double getExchangeCash() {
        return exchangeCash;
    }

    public void setExchangeCash(Double exchangeCash) {
        this.exchangeCash = exchangeCash;
    }

    public Double getCashIncrease() {
        return cashIncrease;
    }

    public void setCashIncrease(Double cashIncrease) {
        this.cashIncrease = cashIncrease;
    }
}
