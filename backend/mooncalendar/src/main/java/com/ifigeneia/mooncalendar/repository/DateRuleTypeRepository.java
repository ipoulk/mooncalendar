package com.ifigeneia.mooncalendar.repository;

import com.ifigeneia.mooncalendar.persistence.entity.DateRuleType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DateRuleTypeRepository extends JpaRepository<DateRuleType, Long> {

    Optional<DateRuleType> findByCode(String code);

}
