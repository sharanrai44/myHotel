package com.demohotel.myhotel.domain;

import com.demohotel.myhotel.domain.enums.RoomType;

import javax.persistence.*;

@Entity
@Table(name = "RoomDetail")
public class RoomDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String roomNo;

    private RoomType roomType;

    public RoomDetail() {
    }

    public RoomDetail(String roomNo, RoomType roomType) {
        this.roomNo = roomNo;
        this.roomType = roomType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
