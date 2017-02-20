package com.capital.dragon.model;

/**
 * Created by bluser on 1/26/2017.
 */
public class Equity extends SecurityReference {
    private String high_alltime;
    private String low_alltime;

    public String getHigh_alltime() {
        return high_alltime;
    }

    public void setHigh_alltime(String high_alltime) {
        this.high_alltime = high_alltime;
    }

    public String getLow_alltime() {
        return low_alltime;
    }

    public void setLow_alltime(String low_alltime) {
        this.low_alltime = low_alltime;
    }
}
