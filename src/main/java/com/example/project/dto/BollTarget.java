package com.example.project.dto;

public class BollTarget {
    //序号
    private  Integer id ;
    //上限
    private  Double up ;
    //中线
    private  Double mid ;
    //下限
    private  Double down ;
    //标准差
    private  Double stand ;

    public Double getStand() {
        return stand;
    }

    public void setStand(Double stand) {
        this.stand = stand;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getUp() {
        return up;
    }

    public void setUp(Double up) {
        this.up = up;
    }

    public Double getMid() {
        return mid;
    }

    public void setMid(Double mid) {
        this.mid = mid;
    }

    public Double getDown() {
        return down;
    }

    public void setDown(Double down) {
        this.down = down;
    }

    @Override
    public String toString() {
        return "BollTarget{" +
                "id=" + id +
                ", up=" + up +
                ", mid=" + mid +
                ", down=" + down +
                ", stand=" + stand +
                '}';
    }
}

