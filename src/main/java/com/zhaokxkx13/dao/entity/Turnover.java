package com.zhaokxkx13.dao.entity;

import java.util.Date;

/**
 * Created by zhaokxkx13 on 2017/3/19.
 */
public class Turnover {
    private long id;
    private double cost;
    private double tax;
    private double sellCost;
    private double manageCost;
    private double financeCost;
    private double profit;
    private double investIncome;
    private double allowanceIncome;
    private double pureProfit;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getSellCost() {
        return sellCost;
    }

    public void setSellCost(double sellCost) {
        this.sellCost = sellCost;
    }

    public double getManageCost() {
        return manageCost;
    }

    public void setManageCost(double manageCost) {
        this.manageCost = manageCost;
    }

    public double getFinanceCost() {
        return financeCost;
    }

    public void setFinanceCost(double financeCost) {
        this.financeCost = financeCost;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getInvestIncome() {
        return investIncome;
    }

    public void setInvestIncome(double investIncome) {
        this.investIncome = investIncome;
    }

    public double getAllowanceIncome() {
        return allowanceIncome;
    }

    public void setAllowanceIncome(double allowanceIncome) {
        this.allowanceIncome = allowanceIncome;
    }

    public double getPureProfit() {
        return pureProfit;
    }

    public void setPureProfit(double pureProfit) {
        this.pureProfit = pureProfit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Turnover{" +
                "id=" + id +
                ", cost=" + cost +
                ", tax=" + tax +
                ", sellCost=" + sellCost +
                ", manageCost=" + manageCost +
                ", financeCost=" + financeCost +
                ", profit=" + profit +
                ", investIncome=" + investIncome +
                ", allowanceIncome=" + allowanceIncome +
                ", pureProfit=" + pureProfit +
                ", date=" + date +
                '}';
    }
}
