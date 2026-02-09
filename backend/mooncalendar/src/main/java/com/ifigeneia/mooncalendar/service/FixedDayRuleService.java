package com.ifigeneia.mooncalendar.service;

import com.ifigeneia.mooncalendar.persistence.entity.EventDateRule;
import com.ifigeneia.mooncalendar.repository.EventDateRuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class FixedDayRuleService {

    private final EventDateRuleRepository eventDateRuleRepository;
    @Autowired
    public FixedDayRuleService(EventDateRuleRepository eventDateRuleRepository){
        this.eventDateRuleRepository = eventDateRuleRepository;
    }


    public Map<Integer, List<String>> getFixedDayEvents(Integer year, Integer month){
        List<EventDateRule> days = eventDateRuleRepository.findByStartMonthAndDateRuleType_Code(month,"fixed_day");
        Map<Integer, List<String>> eventsByDay= new TreeMap<>();
        for(EventDateRule d: days)
        {
            eventsByDay.computeIfAbsent(d.getStartDay(), key ->new ArrayList<>()).add(d.getEvent().getName());
        }
        return eventsByDay;
    }
}
