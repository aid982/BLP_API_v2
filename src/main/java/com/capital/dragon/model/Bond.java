package com.capital.dragon.model;

/**
 * Created by bluser on 1/26/2017.
 */
public class Bond extends SecurityReference {
    private String coupon;
    private String YLD_YTM_BID;
    private String YLD_YTM_ASK;
    private String MATURITY;

    public String getYLD_YTM_BID() {
        return YLD_YTM_BID;
    }


    public void setYLD_YTM_BID(String YLD_YTM_BID) {
        this.YLD_YTM_BID = YLD_YTM_BID;
    }

    public String getYLD_YTM_ASK() {
        return YLD_YTM_ASK;
    }

    public void setYLD_YTM_ASK(String YLD_YTM_ASK) {
        this.YLD_YTM_ASK = YLD_YTM_ASK;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getMATURITY() {
        return MATURITY;
    }

    public void setMATURITY(String MATURITY) {
        this.MATURITY = MATURITY;
    }
}
