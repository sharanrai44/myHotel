package com.demohotel.myhotel.repository;

import com.demohotel.myhotel.domain.RoomPrice;
import com.demohotel.myhotel.domain.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomPriceRepository extends JpaRepository<RoomPrice, Integer> {
    Optional<RoomPrice> findByRoomType(RoomType roomType);
}
