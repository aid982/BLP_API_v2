package com.capital.dragon.model;

/**
 * Created by bluser on 1/27/2017.
 */
public class SecurityHistory extends Security {
    private String date;
    private String open;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }
}
