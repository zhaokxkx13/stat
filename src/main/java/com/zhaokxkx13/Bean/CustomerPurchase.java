package com.zhaokxkx13.Bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/4/13.
 */
public class CustomerPurchase implements Serializable {
    private Map<String, Double> bigTypeSum;
    private Map<String, Double> smallTypeSum;
    private Map<String, Double> payType;

    @Override
    public String toString() {
        return "CustomerPurchase{" +
                "bigTypeSum=" + bigTypeSum +
                ", smallTypeSum=" + smallTypeSum +
                ", payType=" + payType +
                '}';
    }

    public Map<String, Double> getBigTypeSum() {
        return bigTypeSum;
    }

    public void setBigTypeSum(Map<String, Double> bigTypeSum) {
        this.bigTypeSum = bigTypeSum;
    }

    public Map<String, Double> getSmallTypeSum() {
        return smallTypeSum;
    }

    public void setSmallTypeSum(Map<String, Double> smallTypeSum) {
        this.smallTypeSum = smallTypeSum;
    }

    public Map<String, Double> getPayType() {
        return payType;
    }

    public void setPayType(Map<String, Double> payType) {
        this.payType = payType;
    }
}
