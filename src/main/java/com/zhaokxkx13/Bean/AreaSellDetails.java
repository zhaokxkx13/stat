package com.zhaokxkx13.Bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/4/13.
 */
public class AreaSellDetails implements Serializable {
    private Map<String, Double> areaSells;
    private Map<String, Double> citySells;

    public Map<String, Double> getAreaSells() {
        return areaSells;
    }

    public void setAreaSells(Map<String, Double> areaSells) {
        this.areaSells = areaSells;
    }

    public Map<String, Double> getCitySells() {
        return citySells;
    }

    public void setCitySells(Map<String, Double> citySells) {
        this.citySells = citySells;
    }

    @Override
    public String toString() {
        return "AreaSellDetails{" +
                "areaSells=" + areaSells +
                ", citySells=" + citySells +
                '}';
    }
}
