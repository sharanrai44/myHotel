package com.demohotel.myhotel.service;

import com.demohotel.myhotel.domain.BookingLedger;
import com.demohotel.myhotel.dto.BookingDTO;

public interface BonusService {
    BookingLedger bookRoomUsingBonus(BookingDTO bookingDTO) throws Exception;

}
