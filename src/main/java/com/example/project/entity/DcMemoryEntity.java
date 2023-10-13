package com.example.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
@TableName("dcmemory")
public class DcMemoryEntity implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("danci")
    private String danci;

    @TableField("openid")
    private String openid;

    @TableField("mens")
    private String mens;

    @TableField("ext1")
    private String ext1;

    @TableField("jlrq")
    private Date jlrq;

    @TableField("deletefg")
    private Integer deletefg;

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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMens() {
        return mens;
    }

    public void setMens(String mens) {
        this.mens = mens;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public Date getJlrq() {
        return jlrq;
    }

    public void setJlrq(Date jlrq) {
        this.jlrq = jlrq;
    }

    public Integer getDeletefg() {
        return deletefg;
    }

    public void setDeletefg(Integer deletefg) {
        this.deletefg = deletefg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", danci=").append(danci);
        sb.append(", openid=").append(openid);
        sb.append(", mens=").append(mens);
        sb.append(", ext1=").append(ext1);
        sb.append(", jlrq=").append(jlrq);
        sb.append(", deletefg=").append(deletefg);
        sb.append("]");
        return sb.toString();
    }
}