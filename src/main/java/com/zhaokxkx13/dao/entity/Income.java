package com.zhaokxkx13.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaokxkx13 on 2017/3/19.
 */
public class Income implements Serializable {
    private Long id;
    private Double indusAll;
    private Double indusSellAll;
    private Double incomeAll;
    private Double mainServiceIncome;
    private Double techIncome;
    private Double productSellIncome;
    private Double htProductSellIncome;
    private Double goodsSellIncome;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getIndusAll() {
        return indusAll;
    }

    public void setIndusAll(Double indusAll) {
        this.indusAll = indusAll;
    }

    public Double getIndusSellAll() {
        return indusSellAll;
    }

    public void setIndusSellAll(Double indusSellAll) {
        this.indusSellAll = indusSellAll;
    }

    public Double getIncomeAll() {
        return incomeAll;
    }

    public void setIncomeAll(Double incomeAll) {
        this.incomeAll = incomeAll;
    }

    public Double getMainServiceIncome() {
        return mainServiceIncome;
    }

    public void setMainServiceIncome(Double mainServiceIncome) {
        this.mainServiceIncome = mainServiceIncome;
    }

    public Double getTechIncome() {
        return techIncome;
    }

    public void setTechIncome(Double techIncome) {
        this.techIncome = techIncome;
    }

    public Double getProductSellIncome() {
        return productSellIncome;
    }

    public void setProductSellIncome(Double productSellIncome) {
        this.productSellIncome = productSellIncome;
    }

    public Double getHtProductSellIncome() {
        return htProductSellIncome;
    }

    public void setHtProductSellIncome(Double htProductSellIncome) {
        this.htProductSellIncome = htProductSellIncome;
    }

    public Double getGoodsSellIncome() {
        return goodsSellIncome;
    }

    public void setGoodsSellIncome(Double goodsSellIncome) {
        this.goodsSellIncome = goodsSellIncome;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", indusAll=" + indusAll +
                ", indusSellAll=" + indusSellAll +
                ", incomeAll=" + incomeAll +
                ", mainServiceIncome=" + mainServiceIncome +
                ", techIncome=" + techIncome +
                ", productSellIncome=" + productSellIncome +
                ", htProductSellIncome=" + htProductSellIncome +
                ", goodsSellIncome=" + goodsSellIncome +
                ", date=" + date +
                '}';
    }
}
