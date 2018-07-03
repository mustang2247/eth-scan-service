package com.open.coinnews.app.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 锁仓计划表
 */
@Entity
@Table(name = "t_lockPlan")
public class LockPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 参加锁仓 eth 地址 */
    @Lob
    private String token;

    /** 绑定的私有地址  */
    @Lob
    private String privateTokenAdd;

    /**
     * 参与18T数量
     */
    private double participateNum = 0;

    /** 已赚取18T数量 */
    private double earnedReward = 0;

    /** 是否第一次 */
    private boolean isFirst = false;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户ip
     */
    private String ip;

    /**
     * 收益率
     *  6个月 5%
     *  1年   7%
     *  1年   10%
     */
    private String rate;

    /**
     * 语言
     */
    @Column(name = "l")
    private String l;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPrivateTokenAdd() {
        return privateTokenAdd;
    }

    public void setPrivateTokenAdd(String privateTokenAdd) {
        this.privateTokenAdd = privateTokenAdd;
    }

    public double getParticipateNum() {
        return participateNum;
    }

    public void setParticipateNum(double participateNum) {
        this.participateNum = participateNum;
    }

    public double getEarnedReward() {
        return earnedReward;
    }

    public void setEarnedReward(double earnedReward) {
        this.earnedReward = earnedReward;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

}
