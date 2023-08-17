package com.example.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
@TableName("vocabulary")
public class Vocabulary implements Serializable {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("danci")
    private String danci;
    @TableField("hanyi")
    private String hanyi;
    @TableField("type")
    private String type;
    @TableField("ext1")
    private String ext1;
    @TableField("status")
    private Integer status;
    @TableField("renshi")
    private Integer renshi;
    @TableField("changdu")
    private Integer changdu;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRenshi() {
        return renshi;
    }

    public void setRenshi(Integer renshi) {
        this.renshi = renshi;
    }

    public Integer getChangdu() {
        return changdu;
    }

    public void setChangdu(Integer changdu) {
        this.changdu = changdu;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", danci=").append(danci);
        sb.append(", hanyi=").append(hanyi);
        sb.append(", type=").append(type);
        sb.append(", ext1=").append(ext1);
        sb.append(", status=").append(status);
        sb.append(", renshi=").append(renshi);
        sb.append(", changdu=").append(changdu);
        sb.append("]");
        return sb.toString();
    }
}