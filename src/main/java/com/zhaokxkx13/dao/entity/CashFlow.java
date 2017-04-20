package com.zhaokxkx13.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaokxkx13 on 2017/4/15.
 */
public class CashFlow implements Serializable {
    private Integer id;
    private Double operateCash;
    private Double investmentCash;
    private Double financeCash;
    private Double exchangeCash;
    private Double cashIncrease;
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
