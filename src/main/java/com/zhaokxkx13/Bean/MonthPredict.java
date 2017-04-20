package com.zhaokxkx13.Bean;

import java.io.Serializable;

/**
 * Created by zhaokxkx13 on 2017/4/14.
 */
public class MonthPredict implements Serializable {
    private Integer month;
    private Double sells;

    @Override
    public String toString() {
        return "MonthPredict{" +
                "month=" + month +
                ", sells=" + sells +
                '}';
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getSells() {
        return sells;
    }

    public void setSells(Double sells) {
        this.sells = sells;
    }
}
