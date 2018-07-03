package com.open.coinnews.basic.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 网站配置
 */
@Configuration
@Component
public class ConfigTools { // extends WebMvcConfigurerAdapter

    /**
     * 文件上传路径
     */
    @Value("${web.upload-path}")
    private String uploadPath;

    /**
     * 网站基本URL配置
     */
    @Value("${web.file.base.url:http://candy.block18.io}")
    private String fileBaseUrl;

    /**
     * 糖果名字
     */
    @Value("${candy.token.name:18T}")
    private String candyName;

    /**
     * 标题cn
     */
    @Value("${candy.token.title.cn:全球区块链社区领航者}")
    private String candyTitleCN;

    /**
     * 标题en
     */
    @Value("${candy.token.title.en:The leader of the world blockchain community}")
    private String candyTitleEN;

    /**
     * 自己获得奖励
     */
    @Value("${reward.per.num:30}")
    private Integer rewardPerNum;


    /**
     * 邀请人奖励奖励
     */
    @Value("${reward.invite.num:30}")
    private Integer rewardInviteNum;

    /**
     * 最大限制
     */
    @Value("${reward.max.num:-1}")
    private Integer rewardMaxNum;

    /**
     * telegram推广链接
     */
    @Value("${telegram.url:https://t.me/Block_Chat}")
    private String telegramUrl;

    /**
     * ip限制
     */
    @Value("${ip.limit.num:100}")
    private Integer ipLimitNum;

    /**
     * 服务器开关状态 0:正常 1：系统维护 2：活动结束
     */
    @Value("${invite.system.status:0}")
    private Integer systemStatus;

    /**
     * 起始块
     */
    @Value("${start.block.number:1000}")
    private long startBlockNumber;

    /**
     * rpc地址
     */
    @Value("${eth.node.url:http://127.0.0.1:8546}")
    private String ethNodeUrl;

    /**
     * 合约地址
     */
    @Value("${contract.address}")
    private String contractAddress;

    /**
     * 重复次数
     */
    @Value("${retry.times:3}")
    private Integer retryTimes;

    /**
     * 线程数量
     */
    @Value("${thread.number:5}")
    private Integer threadNumber;

    @Value("100000.0")
    private double value;

    public Integer getThreadNumber() {
        return threadNumber;
    }

    public void setThreadNumber(Integer threadNumber) {
        this.threadNumber = threadNumber;
    }

    public long getStartBlockNumber() {
        return startBlockNumber;
    }

    public void setStartBlockNumber(long startBlockNumber) {
        this.startBlockNumber = startBlockNumber;
    }

    public String getEthNodeUrl() {
        return ethNodeUrl;
    }

    public void setEthNodeUrl(String ethNodeUrl) {
        this.ethNodeUrl = ethNodeUrl;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public String getCandyTitleCN() {
        return candyTitleCN;
    }

    public void setCandyTitleCN(String candyTitleCN) {
        this.candyTitleCN = candyTitleCN;
    }

    public String getCandyTitleEN() {
        return candyTitleEN;
    }

    public void setCandyTitleEN(String candyTitleEN) {
        this.candyTitleEN = candyTitleEN;
    }

    public String getCandyName() {
        return candyName;
    }

    public void setCandyName(String candyName) {
        this.candyName = candyName;
    }

    public String getUploadPath() {
        return getUploadPath("");
    }

    public String getUploadPath(String basePath) {
        File f = new File(uploadPath + basePath);
        if (!f.exists()) {
            f.mkdirs();
        }
        return f.getAbsolutePath();
    }

    public String getTelegramUrl() {
        return telegramUrl;
    }

    public void setFileBaseUrl(String fileBaseUrl) {
        this.fileBaseUrl = fileBaseUrl;
    }

    public void setRewardPerNum(Integer rewardPerNum) {
        this.rewardPerNum = rewardPerNum;
    }

    public void setRewardInviteNum(Integer rewardInviteNum) {
        this.rewardInviteNum = rewardInviteNum;
    }

    public void setRewardMaxNum(Integer rewardMaxNum) {
        this.rewardMaxNum = rewardMaxNum;
    }

    public void setTelegramUrl(String telegramUrl) {
        this.telegramUrl = telegramUrl;
    }

    public Integer getIpLimitNum() {
        return ipLimitNum;
    }

    public void setIpLimitNum(Integer ipLimitNum) {
        this.ipLimitNum = ipLimitNum;
    }

    public Integer getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(Integer systemStatus) {
        this.systemStatus = systemStatus;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;

    }

    public String getFileBaseUrl() {
        return fileBaseUrl;
    }

    public Integer getRewardPerNum() {
        return rewardPerNum;
    }

    public Integer getRewardInviteNum() {
        return rewardInviteNum;
    }

    public Integer getRewardMaxNum() {
        return rewardMaxNum;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
