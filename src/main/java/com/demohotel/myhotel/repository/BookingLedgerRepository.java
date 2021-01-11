package com.demohotel.myhotel.repository;

import com.demohotel.myhotel.domain.BookingLedger;
import com.demohotel.myhotel.domain.enums.BookStatus;
import com.demohotel.myhotel.domain.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookingLedgerRepository extends JpaRepository<BookingLedger, Integer> {
    boolean existsByIdAndBookStatusIn(Integer roomId, List<BookStatus> occupiedStatuses);

//    @Query("select count(e) from BookingLedger e where e.bookStatus in( ?1)")
//    long countByBookStatusIn(List<BookStatus> occupiedStatuses);
//
//    @Query("select count(e) from BookingLedger e where e.roomType and e.bookStatus in( ?1)")
//    long countByRoomTypeBookStatusIn(RoomType roomType,List<BookStatus> occupiedStatuses);

    List<BookingLedger> findAllByRoomTypeAndBookStatusIn(RoomType roomType, List<BookStatus> occupiedStatuses);
}
