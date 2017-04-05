package com.zhaokxkx13.dao.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaokxkx13 on 2017/3/19.
 */
public class Income implements Serializable {
    @Excel(name="id",orderNum = "1")
    private Long id;
    @Excel(name = "工业总产值",orderNum="2")
    private Double indusAll;
    @Excel(name = "工业销售产值",orderNum="3")
    private Double indusSellAll;
    @Excel(name = "总收入",orderNum="4")
    private Double incomeAll;
    @Excel(name = "主营业务收入",orderNum="5")
    private Double mainServiceIncome;
    @Excel(name = "技术收入",orderNum="6")
    private Double techIncome;
    @Excel(name = "产品销售收入",orderNum="7")
    private Double productSellIncome;
    @Excel(name = "高新技术产品",orderNum="8")
    private Double htProductSellIncome;
    @Excel(name = "商品销售收入",orderNum="9")
    private Double goodsSellIncome;
    @Excel(name = "日期",orderNum="10")
    private Date date;
    @Excel(name = "类型",orderNum="11")
    private String type;
    private String dateStr;

    public String getDateStr() {
        return dateStr;
    }

    public Long getId() {
        return id;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
                ", type='" + type + '\'' +
                '}';
    }
}
