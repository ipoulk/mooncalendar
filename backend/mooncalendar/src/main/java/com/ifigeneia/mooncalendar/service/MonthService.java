package com.ifigeneia.mooncalendar.service;

import com.ifigeneia.mooncalendar.persistence.entity.EventDateRule;
import com.ifigeneia.mooncalendar.repository.EventDateRuleRepository;
import com.ifigeneia.mooncalendar.web.dto.response.DayResponse;
import com.ifigeneia.mooncalendar.web.dto.response.MonthResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
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



    public MonthResponse getMonth(Integer year, Integer month, String dateRuleTypeCode){
        List<EventDateRule> days = eventDateRuleRepository.findByStartMonthAndDateRuleType_Code(month,dateRuleTypeCode);
        Map<Integer, List<String>> eventsByDay= new TreeMap<>();
        for(EventDateRule d: days)
        {eventsByDay.computeIfAbsent(d.getStartDay(), key ->new ArrayList<>()).add(d.getEvent().getName());
        }
        MonthResponse monthDaysTitles  = new MonthResponse();
        List<DayResponse> dayResponses = new ArrayList<>();

        YearMonth yearMonth = YearMonth.of(year,month);
        for (int i =1; i<=yearMonth.lengthOfMonth(); i++){
            LocalDate date = LocalDate.of(year,month,i);
            DayResponse dateTitle= new DayResponse();
            List<String> l = List.of();
            dateTitle.setDate(date);
            dateTitle.setEventTitles(eventsByDay.getOrDefault(i,l));
            dayResponses.add(dateTitle);


        }
        monthDaysTitles.setDays(dayResponses);
        monthDaysTitles.setYear(year);
        monthDaysTitles.setMonth(month);
        return monthDaysTitles;
    }
}
