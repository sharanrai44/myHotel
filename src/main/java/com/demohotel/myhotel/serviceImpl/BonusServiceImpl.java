package com.demohotel.myhotel.serviceImpl;

import com.demohotel.myhotel.CustomException;
import com.demohotel.myhotel.domain.BookingLedger;
import com.demohotel.myhotel.domain.RoomDetail;
import com.demohotel.myhotel.domain.enums.BookStatus;
import com.demohotel.myhotel.dto.BookingDTO;
import com.demohotel.myhotel.repository.*;
import com.demohotel.myhotel.service.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.demohotel.myhotel.domain.enums.BookStatus.*;
import static java.util.stream.Collectors.toList;

@Service
public class BonusServiceImpl implements BonusService {

    private final UserRepository userRepository;

    private final BonusRepository bonusRepository;

    private final BookingLedgerRepository bookingLedgerRepository;

    private final RoomPriceRepository roomPriceRepository;

    private final RoomDetailRepository roomDetailRepository;

    @Autowired
    public BonusServiceImpl(UserRepository userRepository, BonusRepository bonusRepository, BookingLedgerRepository bookingLedgerRepository, RoomPriceRepository roomPriceRepository, RoomDetailRepository roomDetailRepository) {
        this.userRepository = userRepository;
        this.bonusRepository = bonusRepository;
        this.bookingLedgerRepository = bookingLedgerRepository;
        this.roomPriceRepository = roomPriceRepository;
        this.roomDetailRepository = roomDetailRepository;
    }

    @Override
    public BookingLedger bookRoomUsingBonus(BookingDTO dto) {
        List<BookStatus> occupiedStatuses = new ArrayList<>(Arrays.asList(BOOKED, CHECKED_IN));
        int totalAvailableRooms = 10; //  this  should be configurable/from DB for different types of rooms
        List<BookingLedger> bookedRooms = bookingLedgerRepository
                .findAllByRoomTypeAndBookStatusIn(dto.getType(), occupiedStatuses);

        if (bookedRooms.size() >= totalAvailableRooms) {
            throw new CustomException("Sorry All the rooms are Full");
        }
        List<String> bookedRoomNos = bookedRooms.stream().map(BookingLedger::getRoomNo).collect(toList());
        Optional<RoomDetail> mayBeAvailableRoom;
        if (bookedRoomNos.isEmpty()) {
            mayBeAvailableRoom = roomDetailRepository.findFirstByRoomType(dto.getType());
        } else {
            mayBeAvailableRoom = roomDetailRepository.findFirstByRoomNoNotIn(bookedRoomNos);
        }
        return mayBeAvailableRoom.map(availableRoom -> roomPriceRepository.findByRoomType(availableRoom.getRoomType()).map(roomPrice -> {
            return userRepository.findById(dto.getUserId())
                    .map(user ->
                            bonusRepository.findOneByHolderId(user.getId())
                                    .map(bonus -> {
                                        if (bonus.getAvailablePoints() == 0) {
                                            throw new CustomException("You don't have any bonus points");
                                        }
                                        BookingLedger bookingLedger =
                                                new BookingLedger()
                                                        .setRoomNo(availableRoom.getRoomNo())
                                                        .setUserId(dto.getUserId())
                                                        .setRoomType(dto.getType())
                                                        .setBookedDate(LocalDateTime.now())
                                                        .setCheckInDate(dto.getCheckInDateTime())
                                                        .setCheckOutDate(dto.getCheckOutDateTime());

                                        if (bonus.getAvailablePoints() >= roomPrice.getPrice()) {
                                            bookingLedger.setBookStatus(BOOKED).setTotalFare(0);
                                            bonus.setAvailablePoints(bonus.getAvailablePoints() - roomPrice.getPrice());
                                        } else {
                                            bookingLedger.setBookStatus(PENDING_APPROVAL).setTotalFare(0);
                                            bonus.setAvailablePoints(0);
                                        }
                                        bonusRepository.save(bonus);
                                        return bookingLedgerRepository.save(bookingLedger);

                                    }).orElseThrow(()
                                    -> new CustomException("No Bonus point available"))
                    ).orElseThrow(() -> {
                        // LOG HERE
                        return new CustomException("User not Found!!");
                    }); //
        }).orElseThrow(() -> {
            // LOG HERE
            return new CustomException("Oops! Something went wrong");
        })).orElseThrow(() -> new CustomException("Sorry All the rooms are Full"));

    }

}


