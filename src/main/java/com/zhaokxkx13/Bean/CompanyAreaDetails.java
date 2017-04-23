package com.zhaokxkx13.Bean;

/**
 * Created by zhaokxkx13 on 2017/4/23.
 */
public class CompanyAreaDetails {
    String city;
    Integer count;
    String averageSalery;

    @Override
    public String toString() {
        return "CompanyAreaDetails{" +
                "city='" + city + '\'' +
                ", count=" + count +
                ", averageSalery='" + averageSalery + '\'' +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getAverageSalery() {
        return averageSalery;
    }

    public void setAverageSalery(String averageSalery) {
        this.averageSalery = averageSalery;
    }
}
