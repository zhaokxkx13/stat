package com.zhaokxkx13.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaokxkx13 on 2017/4/15.
 */
public class Balance implements Serializable {
    private Integer id;
    private Double currentAssets;
    private Double fixAssets;
    private Double currentLiabilities;
    private Double fixLiabilities;
    private Double shareholders;
    private Double securities;
    private Double receivable;
    private Double stock;
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
