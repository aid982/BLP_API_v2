package com.capital.dragon.model;

/**
 * Created by bluser on 1/27/2017.
 */
public class SecurityReference extends Security {
    private String high;
    private String low;

    private String crncy;

    private String isin;
    private String high_52w;
    private String low_52w;


    private String description;
    private String industryGroup;// Industry group

    private String CHG_PCT_1D;
    private String CHG_PCT_5D;
    private String CHG_PCT_1M;

    private String CHG_PCT_HIGH_52WEEK;
    private String CHG_PCT_LOW_52WEEK;

    private String RTG_MOODY;
    private String RTG_FITCH;
    private String RTG_SP; //S&P

    private String volume;

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getCrncy() {
        return crncy;
    }

    public void setCrncy(String crncy) {
        this.crncy = crncy;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getHigh_52w() {
        return high_52w;
    }

    public void setHigh_52w(String high_52w) {
        this.high_52w = high_52w;
    }


    public String getLow_52w() {
        return low_52w;
    }

    public void setLow_52w(String low_52w) {
        this.low_52w = low_52w;
    }

    public String getRTG_MOODY() {
        return RTG_MOODY;
    }

    public void setRTG_MOODY(String RTG_MOODY) {
        this.RTG_MOODY = RTG_MOODY;
    }

    public String getRTG_FITCH() {
        return RTG_FITCH;
    }

    public void setRTG_FITCH(String RTG_FITCH) {
        this.RTG_FITCH = RTG_FITCH;
    }

    public String getRTG_SP() {
        return RTG_SP;
    }

    public void setRTG_SP(String RTG_SP) {
        this.RTG_SP = RTG_SP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndustryGroup() {
        return industryGroup;
    }

    public void setIndustryGroup(String industryGroup) {
        this.industryGroup = industryGroup;
    }

    public String getCHG_PCT_1D() {
        return CHG_PCT_1D;
    }

    public void setCHG_PCT_1D(String CHG_PCT_1D) {
        this.CHG_PCT_1D = CHG_PCT_1D;
    }

    public String getCHG_PCT_5D() {
        return CHG_PCT_5D;
    }

    public void setCHG_PCT_5D(String CHG_PCT_5D) {
        this.CHG_PCT_5D = CHG_PCT_5D;
    }

    public String getCHG_PCT_1M() {
        return CHG_PCT_1M;
    }

    public void setCHG_PCT_1M(String CHG_PCT_1M) {
        this.CHG_PCT_1M = CHG_PCT_1M;
    }

    public String getCHG_PCT_HIGH_52WEEK() {
        return CHG_PCT_HIGH_52WEEK;
    }

    public void setCHG_PCT_HIGH_52WEEK(String CHG_PCT_HIGH_52WEEK) {
        this.CHG_PCT_HIGH_52WEEK = CHG_PCT_HIGH_52WEEK;
    }

    public String getCHG_PCT_LOW_52WEEK() {
        return CHG_PCT_LOW_52WEEK;
    }

    public void setCHG_PCT_LOW_52WEEK(String CHG_PCT_LOW_52WEEK) {
        this.CHG_PCT_LOW_52WEEK = CHG_PCT_LOW_52WEEK;
    }
}
