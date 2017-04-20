package com.zhaokxkx13.Bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/4/20.
 */
public class EmployeeFlow implements Serializable {
    Map<Integer, Integer> increase;
    Map<Integer, Integer> decrease;
    Map<Integer, Integer> pureIncrease;

    @Override
    public String toString() {
        return "EmployeeFlow{" +
                "increase=" + increase +
                ", decrease=" + decrease +
                ", pureIncrease=" + pureIncrease +
                '}';
    }

    public Map<Integer, Integer> getIncrease() {
        return increase;
    }

    public void setIncrease(Map<Integer, Integer> increase) {
        this.increase = increase;
    }

    public Map<Integer, Integer> getDecrease() {
        return decrease;
    }

    public void setDecrease(Map<Integer, Integer> decrease) {
        this.decrease = decrease;
    }

    public Map<Integer, Integer> getPureIncrease() {
        return pureIncrease;
    }

    public void setPureIncrease(Map<Integer, Integer> pureIncrease) {
        this.pureIncrease = pureIncrease;
    }
}
