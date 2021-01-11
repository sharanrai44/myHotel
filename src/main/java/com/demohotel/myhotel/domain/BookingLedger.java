package com.demohotel.myhotel.domain;

import com.demohotel.myhotel.domain.enums.BookStatus;
import com.demohotel.myhotel.domain.enums.RoomType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BookingLedger")
public class BookingLedger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String roomNo;

    private int userId;

    private RoomType roomType;

    private BookStatus bookStatus;

    private LocalDateTime bookedDate;

    private LocalDateTime checkInDate;

    private LocalDateTime checkOutDate;

    private int totalFare;

    public BookingLedger() {
    }

    public Integer getId() {
        return id;
    }

    public BookingLedger setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public BookingLedger setRoomNo(String roomNo) {
        this.roomNo = roomNo;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public BookingLedger setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public BookingLedger setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public BookingLedger setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
        return this;
    }

    public LocalDateTime getBookedDate() {
        return bookedDate;
    }

    public BookingLedger setBookedDate(LocalDateTime bookedDate) {
        this.bookedDate = bookedDate;
        return this;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public BookingLedger setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
        return this;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public BookingLedger setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
        return this;
    }

    public int getTotalFare() {
        return totalFare;
    }

    public BookingLedger setTotalFare(int totalFare) {
        this.totalFare = totalFare;
        return this;
    }
}

