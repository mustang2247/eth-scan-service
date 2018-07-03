package com.open.coinnews.app.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 18T变化追踪
 */
@Entity
@Table(name = "t_tokenTranLog")
public class TokenTranLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** from发起地址 */
    @Lob
    private String fromToken;

    /** to目的地址  */
    @Lob
    private String toToken;

    /**
     * 唯一交易单号 去重用
     */
    @Lob
    private String txHash;

    /**
     * 变动18T数量
     */
    private double tranNum = 0;

    /**
     * 拥有18T数量
     */
    private double totalNum = 0;

    /**
     * 交易时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    public TokenTranLog(String fromToken, String toToken, String txHash, double tranNum, double totalNum, Date updateTime, Date createTime) {
        this.fromToken = fromToken;
        this.toToken = toToken;
        this.txHash = txHash;
        this.tranNum = tranNum;
        this.totalNum = totalNum;
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

    public double getTranNum() {
        return tranNum;
    }

    public void setTranNum(double tranNum) {
        this.tranNum = tranNum;
    }

    public double getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(double totalNum) {
        this.totalNum = totalNum;
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

    public TokenTranLog() {
    }
}
