package com.open.coinnews.app.model;

import javax.persistence.*;

/**
 * @author guominghai
 * @Description:
 * @Date Created in 14:20 2018/5/25
 */

@Entity
@Table(name = "t_phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String phone;

    @Lob
    private String token;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
