package com.open.coinnews.app.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 邀请
 */
@Entity
@Table(name = "t_invite")
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** eth token */
    @Lob
    private String token;

    /** 引荐代码  */
    @Column(name = "referer_code")
    @Lob
    private String refererCode;

    /** 邀请码 */
    @Column(name = "invite_code")
    private String inviteCode;

    /** 邀请人数 */
    @Column(name = "invite_count")
    private Integer inviteCount = 0;

    /** 邀请奖励 */
    @Column(name = "invite_reward")
    private Integer inviteReward = 0;

    /** 是否第一次领取 */
    @Column(name = "is_firstreward")
    private boolean isFirstReward = false;

    /**
     * 语言
     */
    @Column(name = "l")
    private String l;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 更新时间
     */

    private Date updatetime;

    /**
     * 用户ip
     */
    private String ip;

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public boolean isFirstReward() {
        return isFirstReward;
    }

    public void setFirstReward(boolean firstReward) {
        isFirstReward = firstReward;
    }

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

    public String getRefererCode() {
        return refererCode;
    }

    public void setRefererCode(String refererCode) {
        this.refererCode = refererCode;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Integer getInviteCount() {
        return inviteCount;
    }

    public void setInviteCount(Integer inviteCount) {
        this.inviteCount = inviteCount;
    }

    public Integer getInviteReward() {
        return inviteReward;
    }

    public void setInviteReward(Integer inviteReward) {
        this.inviteReward = inviteReward;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }
}
