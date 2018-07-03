package com.open.coinnews.app.model;

import javax.persistence.*;

/**
 * 网站配置
 */
@Entity
@Table(name = "t_webconfiginfo")
public class WebConfigInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 文件上传路径
     */
    @Column(name = "upload_path")
    private String uploadPath;

    /**
     * 网站基本URL配置
     */
    @Column(name = "file_baseurl")
    private String fileBaseUrl;

    /**
     * 糖果名字
     */
    @Column(name = "candy_name")
    private String candyName;

    /**
     * 标题cn
     */
    @Column(name = "candy_title_cn")
    private String candyTitleCN;

    /**
     * 标题en
     */
    @Column(name = "candy_title_en")
    private String candyTitleEN;

    /**
     * 自己获得奖励
     */
    @Column(name = "reward_pernum")
    private Integer rewardPerNum;

    /**
     * 邀请人奖励奖励
     */
    @Column(name = "reward_invitenum")
    private Integer rewardInviteNum;

    /**
     * 最大限制
     */
    @Column(name = "reward_maxnum")
    private Integer rewardMaxNum;

    /**
     * telegram推广链接
     */
    @Column(name = "telegram_url")
    private String telegramUrl;

    /**
     * ip限制
     */
    @Column(name = "ip_limitnum")
    private Integer ipLimitNum;

    /**
     * 服务器开关状态 0:正常 1：系统维护 2：活动结束
     */
    @Column(name = "system_status")
    private Integer systemStatus;

    /**
     * 网站logo
     */
    @Column(name = "app_logo")
    private String appLogo;

    private double value;


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
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getFileBaseUrl() {
        return fileBaseUrl;
    }

    public void setFileBaseUrl(String fileBaseUrl) {
        this.fileBaseUrl = fileBaseUrl;
    }

    public Integer getRewardPerNum() {
        return rewardPerNum;
    }

    public void setRewardPerNum(Integer rewardPerNum) {
        this.rewardPerNum = rewardPerNum;
    }

    public Integer getRewardInviteNum() {
        return rewardInviteNum;
    }

    public void setRewardInviteNum(Integer rewardInviteNum) {
        this.rewardInviteNum = rewardInviteNum;
    }

    public Integer getRewardMaxNum() {
        return rewardMaxNum;
    }

    public void setRewardMaxNum(Integer rewardMaxNum) {
        this.rewardMaxNum = rewardMaxNum;
    }

    public String getTelegramUrl() {
        return telegramUrl;
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

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
