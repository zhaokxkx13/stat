package com.zhaokxkx13.dao.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
public class Product implements Serializable {
    @Excel(name = "id", orderNum = "1")
    private Integer id;
    @Excel(name = "产品名称", orderNum = "2")
    private String name;
    @Excel(name = "产品大分类", orderNum = "3")
    private String typeBig;
    @Excel(name = "产品小分类", orderNum = "4")
    private String typeSmall;
    @Excel(name = "产品成本", orderNum = "5")
    private Double cost;
    @Excel(name = "推荐售价", orderNum = "6")
    private Double price;
    @Excel(name = "库存", orderNum = "6")
    private Integer stock;
    @Excel(name = "规格", orderNum = "7")
    private String spec;
    private List<ProductOrder> productOrderList;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeBig='" + typeBig + '\'' +
                ", typeSmall='" + typeSmall + '\'' +
                ", cost=" + cost +
                ", price=" + price +
                ", stock=" + stock +
                ", spec='" + spec + '\'' +
                ", productOrderList=" + productOrderList +
                '}';
    }

    public List<ProductOrder> getProductOrderList() {
        return productOrderList;
    }

    public void setProductOrderList(List<ProductOrder> productOrderList) {
        this.productOrderList = productOrderList;
    }

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

    public String getTypeSmall() {
        return typeSmall;
    }

    public void setTypeSmall(String typeSmall) {
        this.typeSmall = typeSmall;
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
