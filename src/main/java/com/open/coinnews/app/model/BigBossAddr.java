package com.open.coinnews.app.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 大户划地址列表
 */
@Entity
@Table(name = "t_bigBossAddr")
public class BigBossAddr {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 绑定的私有地址  */
    @Lob
    private String privateTokenAdd;

    /**
     * 18T数量
     */
    private double tokenNum = 0;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    public double getTokenNum() {
        return tokenNum;
    }

    public void setTokenNum(double tokenNum) {
        this.tokenNum = tokenNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrivateTokenAdd() {
        return privateTokenAdd;
    }

    public void setPrivateTokenAdd(String privateTokenAdd) {
        this.privateTokenAdd = privateTokenAdd;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
