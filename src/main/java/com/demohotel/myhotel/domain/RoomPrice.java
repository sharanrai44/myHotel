package com.demohotel.myhotel.domain;

import com.demohotel.myhotel.domain.enums.RoomType;

import javax.persistence.*;


@Entity
@Table(name = "RoomPrice")
public class RoomPrice {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Integer id;

    private RoomType roomType;

    private int price;

    public RoomPrice() {
    }

    public RoomPrice(RoomType roomType, int price) {
        this.roomType = roomType;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public RoomPrice setId(Integer id) {
        this.id = id;
        return this;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
