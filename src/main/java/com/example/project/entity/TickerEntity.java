package com.example.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
@TableName("ticker")
public class TickerEntity implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("date")
    private String date;
    @TableField("kpj")
    private Double kpj;
    @TableField("zgj")
    private Double zgj;
    @TableField("zdj")
    private Double zdj;
    @TableField("spj")
    private Double spj;
    @TableField("vol")
    private Double vol;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getKpj() {
        return kpj;
    }

    public void setKpj(Double kpj) {
        this.kpj = kpj;
    }

    public Double getZgj() {
        return zgj;
    }

    public void setZgj(Double zgj) {
        this.zgj = zgj;
    }

    public Double getZdj() {
        return zdj;
    }

    public void setZdj(Double zdj) {
        this.zdj = zdj;
    }

    public Double getSpj() {
        return spj;
    }

    public void setSpj(Double spj) {
        this.spj = spj;
    }

    public Double getVol() {
        return vol;
    }

    public void setVol(Double vol) {
        this.vol = vol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", kpj=").append(kpj);
        sb.append(", zgj=").append(zgj);
        sb.append(", zdj=").append(zdj);
        sb.append(", spj=").append(spj);
        sb.append(", vol=").append(vol);
        sb.append("]");
        return sb.toString();
    }
}