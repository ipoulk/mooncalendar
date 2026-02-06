package com.ifigeneia.mooncalendar.repository;

import com.ifigeneia.mooncalendar.persistence.entity.DateRuleType;
import com.ifigeneia.mooncalendar.persistence.entity.EventDateRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventDateRuleRepository extends JpaRepository<EventDateRule, Long> {

    List<EventDateRule> findByDateRuleType(DateRuleType dateRuleType);
    List<EventDateRule> findByStartMonthAndStartDay(Integer startMonth, Integer startDay);
    List<EventDateRule> findByStartMonth(Integer startMonth);
    List<EventDateRule> findByStartMonthAndDateRuleType_Code(Integer startMonth, String dateRuleTypeCode);

}
