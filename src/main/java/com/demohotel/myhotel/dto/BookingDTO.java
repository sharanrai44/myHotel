package com.demohotel.myhotel.dto;

import com.demohotel.myhotel.domain.enums.RoomType;
import com.sun.istack.internal.NotNull;

import java.time.LocalDateTime;

public class BookingDTO {

    @NotNull
    private Integer userId;

    private RoomType type;

    private LocalDateTime checkInDateTime;

    private LocalDateTime checkOutDateTime;

    public Integer getUserId() {
        return userId;
    }

    public BookingDTO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public RoomType getType() {
        return type;
    }

    public BookingDTO setType(RoomType type) {
        this.type = type;
        return this;
    }

    public LocalDateTime getCheckInDateTime() {
        return checkInDateTime;
    }

    public BookingDTO setCheckInDateTime(LocalDateTime checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
        return this;
    }

    public LocalDateTime getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public BookingDTO setCheckOutDateTime(LocalDateTime checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
        return this;
    }
}
