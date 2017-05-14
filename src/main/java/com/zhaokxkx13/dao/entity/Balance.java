package com.zhaokxkx13.dao.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaokxkx13 on 2017/4/15.
 */
public class Balance implements Serializable {
    @Excel(name = "id", orderNum = "1")
    private Integer id;
    @Excel(name = "流动资产", orderNum = "2")
    private Double currentAssets;
    @Excel(name = "固定资产", orderNum = "3")
    private Double fixAssets;
    @Excel(name = "流动负债", orderNum = "4")
    private Double currentLiabilities;
    @Excel(name = "固定负债", orderNum = "5")
    private Double fixLiabilities;
    @Excel(name = "股东权益", orderNum = "6")
    private Double shareholders;
    @Excel(name = "现金有价证券", orderNum = "7")
    private Double securities;
    @Excel(name = "应收账款", orderNum = "8")
    private Double receivable;
    @Excel(name = "存货", orderNum = "9")
    private Double stock;
    @Excel(name = "日期", orderNum = "10")
    private Date date;

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", currentAssets=" + currentAssets +
                ", fixAssets=" + fixAssets +
                ", currentLiabilities=" + currentLiabilities +
                ", fixLiabilities=" + fixLiabilities +
                ", shareholders=" + shareholders +
                ", securities=" + securities +
                ", receivable=" + receivable +
                ", stock=" + stock +
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


    public Double getFixAssets() {
        return fixAssets;
    }

    public void setFixAssets(Double fixAssets) {
        this.fixAssets = fixAssets;
    }

    public Double getCurrentLiabilities() {
        return currentLiabilities;
    }

    public void setCurrentLiabilities(Double currentLiabilities) {
        this.currentLiabilities = currentLiabilities;
    }

    public Double getFixLiabilities() {
        return fixLiabilities;
    }

    public void setFixLiabilities(Double fixLiabilities) {
        this.fixLiabilities = fixLiabilities;
    }

    public Double getShareholders() {
        return shareholders;
    }

    public void setShareholders(Double shareholders) {
        this.shareholders = shareholders;
    }

    public Double getCurrentAssets() {
        return currentAssets;
    }

    public void setCurrentAssets(Double currentAssets) {
        this.currentAssets = currentAssets;
    }

    public Double getSecurities() {
        return securities;
    }

    public void setSecurities(Double securities) {
        this.securities = securities;
    }

    public Double getReceivable() {
        return receivable;
    }

    public void setReceivable(Double receivable) {
        this.receivable = receivable;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }
}
