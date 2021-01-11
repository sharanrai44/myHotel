package com.demohotel.myhotel.repository;

import com.demohotel.myhotel.domain.RoomDetail;
import com.demohotel.myhotel.domain.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomDetailRepository extends JpaRepository<RoomDetail, Integer> {
    Optional<RoomDetail> findFirstByRoomNoNotIn(List<String> bookedRoomNos);
    Optional<RoomDetail> findTop1ByRoomNoNotIn(List<String> bookedRoomNos);

    Optional<RoomDetail> findFirstByRoomType(RoomType roomType);
}
