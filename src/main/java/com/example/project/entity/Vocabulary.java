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
    /**
     *使用频率：1高频，2中高频，3中频，4中低频，5低频
     */
    @TableField("uses")
    private Integer uses;
    /**
     * 考试词汇：1.英语四级词汇；2.六级；3.专4；4专8；5考研；6托福；7雅思；8gre词汇
     */
    @TableField("test")
    private Integer test;
    /**
     * 星级词汇：1.基本词汇；2.核心词汇；3.常用词汇；4.扩展词汇；5.畅通词汇
     */
    @TableField("star")
    private Integer star;
    /**
     * 分类：1.水果；2.蔬菜；3.花；4.哺乳动物；5.家畜家禽；6.爬行两栖；7.禽鸟；8.鱼类；9.壳类动物；10.昆虫；11.树
     */
    @TableField("sort")
    private Integer sort;

    public Integer getUses() {
        return uses;
    }

    public void setUses(Integer uses) {
        this.uses = uses;
    }

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

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