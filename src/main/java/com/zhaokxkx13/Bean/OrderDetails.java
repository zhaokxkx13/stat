package com.zhaokxkx13.Bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaokxkx13 on 2017/4/11.
 */
public class OrderDetails implements Serializable {
    public String productName;
    public String bigType;
    public String smallType;
    public Double price;
    public Integer num;
    public Double sum;
    public String orderDateStr;
    public String sender;
    public String receiver;
    public Double freitght;
    public String address;
    public String spec;
    public Date date;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getProductName() {
        return productName;
    }


    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBigType() {
        return bigType;
    }

    public void setBigType(String bigType) {
        this.bigType = bigType;
    }

    public String getSmallType() {
        return smallType;
    }

    public void setSmallType(String smallType) {
        this.smallType = smallType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getOrderDateStr() {
        return orderDateStr;
    }

    public void setOrderDateStr(String orderDateStr) {
        this.orderDateStr = orderDateStr;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getFreitght() {
        return freitght;
    }

    public void setFreitght(Double freitght) {
        this.freitght = freitght;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "productName='" + productName + '\'' +
                ", bigType='" + bigType + '\'' +
                ", smallType='" + smallType + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", sum=" + sum +
                ", orderDateStr='" + orderDateStr + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", freitght=" + freitght +
                ", address='" + address + '\'' +
                ", spec='" + spec + '\'' +
                ", date=" + date +
                '}';
    }
}
