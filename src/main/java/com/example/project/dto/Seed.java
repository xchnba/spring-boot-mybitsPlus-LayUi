package com.example.project.dto;

import com.example.project.entity.TickerEntity;

public class Seed {
    private TickerEntity ticker;
    private BollTarget boll;
    private KdjTarget kdj;
    private MacdTarget macd;
    private String time;

    public TickerEntity getTicker() {
        return ticker;
    }

    public void setTicker(TickerEntity ticker) {
        this.ticker = ticker;
    }

    public BollTarget getBoll() {
        return boll;
    }

    public void setBoll(BollTarget boll) {
        this.boll = boll;
    }

    public KdjTarget getKdj() {
        return kdj;
    }

    public void setKdj(KdjTarget kdj) {
        this.kdj = kdj;
    }

    public MacdTarget getMacd() {
        return macd;
    }

    public void setMacd(MacdTarget macd) {
        this.macd = macd;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
