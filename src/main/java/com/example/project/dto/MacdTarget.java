package com.example.project.dto;

import java.util.Date;

public class MacdTarget {
    //序号
    private  Integer id ;
    //DIFF
    private  Double diff ;
    //DEA
    private  Double dea ;
    //MACD
    private  Double macd ;
    //状态 1——空心阳柱；2——实心阳柱；3——空心阴柱；4——实心阴柱
    private  Integer status ;

    //开盘价
    private Double open;
    //最高价
    private Double high;
    //最低价
    private Double low;
    //收盘价
    private Double close;
    //收盘价是靠近最低价还是最高价  0先计算最高价，1先计算最低价
    private Integer hl;
    //收盘时间 --开始时间其实是开始时间
    private String closeTime;
    //开盘时间
    private Date opentime;

    public Date getOpentime() {
        return opentime;
    }

    public void setOpentime(Date opentime) {
        this.opentime = opentime;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Integer getHl() {
        return hl;
    }

    public void setHl(Integer hl) {
        this.hl = hl;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDiff() {
        return diff;
    }

    public void setDiff(Double diff) {
        this.diff = diff;
    }

    public Double getDea() {
        return dea;
    }

    public void setDea(Double dea) {
        this.dea = dea;
    }

    public Double getMacd() {
        return macd;
    }

    public void setMacd(Double macd) {
        this.macd = macd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MacdTarget{" +
                "id=" + id +
                ", diff=" + diff +
                ", dea=" + dea +
                ", macd=" + macd +
                ", status=" + status +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", hl=" + hl +
                ", closeTime='" + closeTime + '\'' +
                ", opentime=" + opentime +
                '}';
    }
}

