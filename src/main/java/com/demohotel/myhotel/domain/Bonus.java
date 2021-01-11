package com.demohotel.myhotel.domain;

import com.demohotel.myhotel.domain.enums.RewardType;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "Bonus")
public class Bonus {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "holder")
    private Integer holderId;

    @Column(name = "issued_money")
    private int issuedPoints;

    @Column(name = "available_money")
    private int availablePoints;

    @Column(name = "expiry")
    private ZonedDateTime expiry = ZonedDateTime.now().plusDays(45);

    public Bonus() {
    }

    public Bonus(Integer holderId, int issuedPoints) {
        this.holderId = holderId;
        this.issuedPoints = issuedPoints;
        this.availablePoints = issuedPoints;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public int getIssuedPoints() {
        return issuedPoints;
    }

    public void setIssuedPoints(int issuedPoints) {
        this.issuedPoints = issuedPoints;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(int availablePoints) {
        this.availablePoints = availablePoints;
    }

    public ZonedDateTime getExpiry() {
        return expiry;
    }

    public void setExpiry(ZonedDateTime expiry) {
        this.expiry = expiry;
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "id='" + id + '\'' +
                ", holderId=" + holderId +
                ", issuedPoints=" + issuedPoints +
                ", issuedPoints=" + issuedPoints +
                ", expiry=" + expiry +
                '}';
    }
}