package com.example.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("shares")
public class SharesEntity implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("gpdate")
    private Date gpdate;

    @TableField("gpdm")
    private String gpdm;

    @TableField("name")
    private String name;

    @TableField("spj")
    private Double spj;

    @TableField("zgj")
    private Double zgj;

    @TableField("zdj")
    private Double zdj;

    @TableField("kpj")
    private Double kpj;

    @TableField("qspj")
    private Double qspj;

    @TableField("pjj")
    private Double pjj;

    @TableField("zde")
    private String zde;

    @TableField("zdf")
    private String zdf;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getGpdate() {
        return gpdate;
    }

    public void setGpdate(Date gpdate) {
        this.gpdate = gpdate;
    }

    public String getGpdm() {
        return gpdm;
    }

    public void setGpdm(String gpdm) {
        this.gpdm = gpdm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSpj() {
        return spj;
    }

    public void setSpj(Double spj) {
        this.spj = spj;
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

    public Double getKpj() {
        return kpj;
    }

    public void setKpj(Double kpj) {
        this.kpj = kpj;
    }

    public Double getQspj() {
        return qspj;
    }

    public void setQspj(Double qspj) {
        this.qspj = qspj;
    }

    public Double getPjj() {
        return pjj;
    }

    public void setPjj(Double pjj) {
        this.pjj = pjj;
    }

    public String getZde() {
        return zde;
    }

    public void setZde(String zde) {
        this.zde = zde;
    }

    public String getZdf() {
        return zdf;
    }

    public void setZdf(String zdf) {
        this.zdf = zdf;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gpdate=").append(gpdate);
        sb.append(", gpdm=").append(gpdm);
        sb.append(", name=").append(name);
        sb.append(", spj=").append(spj);
        sb.append(", zgj=").append(zgj);
        sb.append(", zdj=").append(zdj);
        sb.append(", kpj=").append(kpj);
        sb.append(", qspj=").append(qspj);
        sb.append(", pjj=").append(pjj);
        sb.append(", zde=").append(zde);
        sb.append(", zdf=").append(zdf);
        sb.append("]");
        return sb.toString();
    }
}