package com.demohotel.myhotel.resource;

import com.demohotel.myhotel.CustomException;
import com.demohotel.myhotel.domain.BookingLedger;
import com.demohotel.myhotel.dto.BookingDTO;
import com.demohotel.myhotel.service.BonusService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bonus")
public class BookingResource {

    private final BonusService bonusService;


    @Autowired
    public BookingResource(BonusService bonusService) {
        this.bonusService = bonusService;
    }

    @ApiOperation(value = "API to book a room using bonus points")
    @PostMapping("/book")
    public ResponseEntity<?> bookRoom(@Validated @RequestBody BookingDTO bookingDTO) throws Exception {
        try {
            BookingLedger bookingLedger = bonusService.bookRoomUsingBonus(bookingDTO);
            return new ResponseEntity<>(bookingLedger, HttpStatus.OK);

        } catch (CustomException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
