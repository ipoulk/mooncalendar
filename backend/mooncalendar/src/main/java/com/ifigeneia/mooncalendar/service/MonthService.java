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
public class MonthService {

    private final EventDateRuleRepository eventDateRuleRepository;
    @Autowired
    public MonthService(EventDateRuleRepository eventDateRuleRepository){
        this.eventDateRuleRepository = eventDateRuleRepository;
    }

    public Map<Integer,List<String>> getMonth(Integer year, Integer month, String dateRuleTypeCode){
        List<EventDateRule> days = eventDateRuleRepository.findByStartMonthAndDateRuleType_Code(month,dateRuleTypeCode);
        Map<Integer, List<String>> events = new TreeMap<>();
        for(EventDateRule d: days)
        {events.computeIfAbsent(d.getStartDay(), key ->new ArrayList<>()).add(d.getEvent().getName());
        }
        return events;
    }
}
