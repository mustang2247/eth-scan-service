package com.open.coinnews.app.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 锁仓计操作记录
 */
@Entity
@Table(name = "t_lockPlanOpLog")
public class LockPlanOperationsLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 参加锁仓 eth 地址 */
    @Lob
    private String fromToken;

    /** 绑定的私有地址  */
    @Lob
    private String toToken;

    /**
     * 唯一交易单号
     */
    @Lob
    private String txHash;

    /**
     * 18T数量
     */
    private double opNum = 0;


    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    public LockPlanOperationsLog() {
    }

    public LockPlanOperationsLog(String fromToken, String toToken, String txHash, double opNum, Date updateTime, Date createTime) {
        this.fromToken = fromToken;
        this.toToken = toToken;
        this.txHash = txHash;
        this.opNum = opNum;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromToken() {
        return fromToken;
    }

    public void setFromToken(String fromToken) {
        this.fromToken = fromToken;
    }

    public String getToToken() {
        return toToken;
    }

    public void setToToken(String toToken) {
        this.toToken = toToken;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public double getOpNum() {
        return opNum;
    }

    public void setOpNum(double opNum) {
        this.opNum = opNum;
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
