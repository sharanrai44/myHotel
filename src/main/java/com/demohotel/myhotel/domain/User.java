package com.demohotel.myhotel.domain;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String name;

    private int bonusPoint;

    private String mobile;

    private String address;


    public User() {
    }

    public User(Integer id, String name, int bonusPoint, String mobile, String address) {
        this.Id = id;
        this.name = name;
        this.bonusPoint = bonusPoint;
        this.mobile = mobile;
        this.address = address;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBonusPoint() {
        return bonusPoint;
    }

    public void setBonusPoint(int bonusPoint) {
        this.bonusPoint = bonusPoint;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
