package com.example.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("uunknow")
public class UserUnknow implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("openid")
    private String openid;

    @TableField("danci")
    private String danci;

    @TableField("hanyi")
    private String hanyi;

    @TableField("des")
    private String des;

    @TableField("changdu")
    private Integer changdu;
    
    @TableField("date")
    private Date date;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getDanci() {
        return danci;
    }

    public void setDanci(String danci) {
        this.danci = danci;
    }

    public String getHanyi() {
        return hanyi;
    }

    public void setHanyi(String hanyi) {
        this.hanyi = hanyi;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getChangdu() {
        return changdu;
    }

    public void setChangdu(Integer changdu) {
        this.changdu = changdu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", openid=").append(openid);
        sb.append(", danci=").append(danci);
        sb.append(", hanyi=").append(hanyi);
        sb.append(", des=").append(des);
        sb.append(", changdu=").append(changdu);
        sb.append(", date=").append(date);
        sb.append("]");
        return sb.toString();
    }
}