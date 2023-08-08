package com.example.project.dto;

public class KdjTarget {
    //序号
    private  Integer id ;
    //DIFF
    private  Double k ;
    //DEA
    private  Double d ;
    //MACD
    private  Double j ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getK() {
        return k;
    }

    public void setK(Double k) {
        this.k = k;
    }

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public Double getJ() {
        return j;
    }

    public void setJ(Double j) {
        this.j = j;
    }

    @Override
    public String toString() {
        return "KdjTarget{" +
                "id=" + id +
                ", k=" + k +
                ", d=" + d +
                ", j=" + j +
                '}';
    }
}

