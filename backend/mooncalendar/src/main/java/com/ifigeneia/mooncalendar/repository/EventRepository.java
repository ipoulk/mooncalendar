package com.ifigeneia.mooncalendar.repository;

import com.ifigeneia.mooncalendar.persistence.entity.Event;
import com.ifigeneia.mooncalendar.persistence.entity.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByEventCategory(EventCategory eventCategory);
    List<Event> findByEventCategoryCode(EventCategory eventCategoryCode);
}
