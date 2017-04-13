package com.zhaokxkx13.Bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaokxkx13 on 2017/4/12.
 */
public class ProductDetails implements Serializable {
    @Expose
    private Integer year;
    @Expose
    private Integer month;
    @Expose
    private Double sellSum;//销售额
    @Expose
    private Double sameTime;//同期值
    private Double sameTimeRate;//同期比
    @Expose
    private Double preTime;//前期值
    private Double increaseCircleRate;//增速环比
    private Double percent;//占比
    @Expose
    private Double sumToMonth;//年累计
    @Expose
    private Integer pos;//年排名
    private Date date;
    @Expose
    private String sameTimeRateStr;
    @Expose
    private String increaseCircleRateStr;
    @Expose
    private String percentStr;


    /**
     * Gets percent str.
     *
     * @return the percent str
     */
    public String getPercentStr() {
        return percentStr;
    }

    /**
     * Sets percent str.
     *
     * @param percentStr the percent str
     */
    public void setPercentStr(String percentStr) {
        this.percentStr = percentStr;
    }

    /**
     * Gets same time rate str.
     *
     * @return the same time rate str
     */
    public String getSameTimeRateStr() {
        return sameTimeRateStr;
    }

    /**
     * Sets same time rate str.
     *
     * @param sameTimeRateStr the same time rate str
     */
    public void setSameTimeRateStr(String sameTimeRateStr) {
        this.sameTimeRateStr = sameTimeRateStr;
    }

    /**
     * Gets increase circle rate str.
     *
     * @return the increase circle rate str
     */
    public String getIncreaseCircleRateStr() {
        return increaseCircleRateStr;
    }

    /**
     * Sets increase circle rate str.
     *
     * @param increaseCircleRateStr the increase circle rate str
     */
    public void setIncreaseCircleRateStr(String increaseCircleRateStr) {
        this.increaseCircleRateStr = increaseCircleRateStr;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Gets month.
     *
     * @return the month
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * Sets month.
     *
     * @param month the month
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * Gets sell sum.
     *
     * @return the sell sum
     */
    public Double getSellSum() {
        return sellSum;
    }

    /**
     * Sets sell sum.
     *
     * @param sellSum the sell sum
     */
    public void setSellSum(Double sellSum) {
        this.sellSum = sellSum;
    }

    /**
     * Gets same time.
     *
     * @return the same time
     */
    public Double getSameTime() {
        return sameTime;
    }

    /**
     * Sets same time.
     *
     * @param sameTime the same time
     */
    public void setSameTime(Double sameTime) {
        this.sameTime = sameTime;
    }

    /**
     * Gets same time rate.
     *
     * @return the same time rate
     */
    public Double getSameTimeRate() {
        return sameTimeRate;
    }

    /**
     * Sets same time rate.
     *
     * @param sameTimeRate the same time rate
     */
    public void setSameTimeRate(Double sameTimeRate) {
        this.sameTimeRate = sameTimeRate;
    }

    /**
     * Gets pre time.
     *
     * @return the pre time
     */
    public Double getPreTime() {
        return preTime;
    }

    /**
     * Sets pre time.
     *
     * @param preTime the pre time
     */
    public void setPreTime(Double preTime) {
        this.preTime = preTime;
    }

    /**
     * Gets increase circle rate.
     *
     * @return the increase circle rate
     */
    public Double getIncreaseCircleRate() {
        return increaseCircleRate;
    }

    /**
     * Sets increase circle rate.
     *
     * @param increaseCircleRate the increase circle rate
     */
    public void setIncreaseCircleRate(Double increaseCircleRate) {
        this.increaseCircleRate = increaseCircleRate;
    }

    /**
     * Gets percent.
     *
     * @return the percent
     */
    public Double getPercent() {
        return percent;
    }

    /**
     * Sets percent.
     *
     * @param percent the percent
     */
    public void setPercent(Double percent) {
        this.percent = percent;
    }

    /**
     * Gets sum to month.
     *
     * @return the sum to month
     */
    public Double getSumToMonth() {
        return sumToMonth;
    }

    /**
     * Sets sum to month.
     *
     * @param sumToMonth the sum to month
     */
    public void setSumToMonth(Double sumToMonth) {
        this.sumToMonth = sumToMonth;
    }

    /**
     * Gets pos.
     *
     * @return the pos
     */
    public Integer getPos() {
        return pos;
    }

    /**
     * Sets pos.
     *
     * @param pos the pos
     */
    public void setPos(Integer pos) {
        this.pos = pos;
    }
}
