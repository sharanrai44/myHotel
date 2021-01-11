package com.demohotel.myhotel.prototypes;

import com.demohotel.myhotel.domain.*;
import com.demohotel.myhotel.domain.enums.BookStatus;
import com.demohotel.myhotel.domain.enums.RoomType;
import com.demohotel.myhotel.dto.BookingDTO;

import java.time.LocalDateTime;

import static com.demohotel.myhotel.domain.enums.RoomType.DELUXE;

public class BookingPrototypes {
    public static BookingLedger aBookingLedger() {
        return new BookingLedger()
                .setUserId(1)
                .setRoomNo("10")
                .setRoomType(RoomType.DELUXE)
                .setBookedDate(LocalDateTime.now().minusDays(1))
                .setBookStatus(BookStatus.BOOKED)
                .setCheckInDate(LocalDateTime.now())
                .setCheckOutDate(LocalDateTime.now().plusDays(1))
                .setTotalFare(0);
    }

    public static BookingDTO aBookingDTO() {
        return new BookingDTO().setUserId(1)
                .setType(RoomType.DELUXE)
                .setCheckInDateTime(LocalDateTime.now())
                .setCheckOutDateTime(LocalDateTime.now().plusDays(1));


    }

    public static RoomDetail aRoomDetail() {
        return new RoomDetail("10", DELUXE);
    }

    public static RoomPrice aRoomPrice() {
        return new RoomPrice(DELUXE, 2000);
    }
    public static User aUser() {
        return  new User(1, "sharan", 2000, "9123456789", "test");
    }

}
