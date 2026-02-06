package com.ifigeneia.mooncalendar.repository;

import com.ifigeneia.mooncalendar.persistence.entity.FastingType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FastingTypeRepository extends JpaRepository<FastingType, Long> {

    Optional<FastingType> findByCode(String code);
}
