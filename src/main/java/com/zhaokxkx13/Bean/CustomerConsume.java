package com.zhaokxkx13.Bean;

import com.google.gson.annotations.Expose;
import com.zhaokxkx13.dao.entity.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
public class CustomerConsume implements Serializable {
    private Customer customer;
    private Double moneyAmount;
    private Integer num;
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "CustomerComsume{" +
                "customer=" + customer +
                ", moneyAmount=" + moneyAmount +
                ", num=" + num +
                '}';
    }
}
