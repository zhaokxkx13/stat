package com.zhaokxkx13.dao.entity;

import java.io.Serializable;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
public class Product implements Serializable {
    private Integer id;
    private String name;
    private String typeBig;
    private String tpeSmall;
    private Double cost;
    private Double price;
    private Integer stock;
    private String spec;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeBig() {
        return typeBig;
    }

    public void setTypeBig(String typeBig) {
        this.typeBig = typeBig;
    }

    public String getTpeSmall() {
        return tpeSmall;
    }

    public void setTpeSmall(String tpeSmall) {
        this.tpeSmall = tpeSmall;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
