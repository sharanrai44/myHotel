package com.demohotel.myhotel.repository;

import com.demohotel.myhotel.domain.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BonusRepository  extends JpaRepository<Bonus,Integer> {
    Optional<Bonus> findOneByHolderId(Integer id);
}
