package com.ifigeneia.mooncalendar.repository;

import com.ifigeneia.mooncalendar.persistence.entity.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {

    List<EventCategory> findByName(String name);
    Optional<EventCategory> findByCode(String code);
}
