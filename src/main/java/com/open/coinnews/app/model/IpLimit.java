package com.open.coinnews.app.model;

import javax.persistence.*;
import java.util.Date;

/**
 * ip limit
 */
@Entity
@Table(name = "t_iplimit")
public class IpLimit {

    /**
     * 用户ip
     */
    @Id
    private String ip;

    /** 用户人数 */
    @Column(name = "user_count")
    private Integer userCount = 0;

    /**
     * 是否黑名单
     */
    @Column(name = "is_blacklist")
    private boolean isBlackList = false;

    /**
     * 更新时间
     */
    private Date updatetime;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public boolean isBlackList() {
        return isBlackList;
    }

    public void setBlackList(boolean blackList) {
        isBlackList = blackList;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
