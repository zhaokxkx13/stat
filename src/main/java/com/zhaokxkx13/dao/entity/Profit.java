package com.zhaokxkx13.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaokxkx13 on 2017/4/15.
 */
public class Profit implements Serializable {
    private Integer id;
    private Double income;
    private Double cost;
    private Double sellTax;
    private Double expenses;
    private Double profit;
    private Double incomeTax;
    private Double pureProfit;
    private Date date;

    @Override
    public String toString() {
        return "Profit{" +
                "id=" + id +
                ", income=" + income +
                ", cost=" + cost +
                ", sellTax=" + sellTax +
                ", expenses=" + expenses +
                ", profit=" + profit +
                ", incomeTax=" + incomeTax +
                ", pureProfit=" + pureProfit +
                ", date=" + date +
                '}';
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(Double incomeTax) {
        this.incomeTax = incomeTax;
    }

    public Double getPureProfit() {
        return pureProfit;
    }

    public void setPureProfit(Double pureProfit) {
        this.pureProfit = pureProfit;
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

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getSellTax() {
        return sellTax;
    }

    public void setSellTax(Double sellTax) {
        this.sellTax = sellTax;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }
}
