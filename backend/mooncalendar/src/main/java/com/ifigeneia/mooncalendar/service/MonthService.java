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

    private final MonthEventsProvider monthEventsProvider;
    @Autowired
    public MonthService(MonthEventsProvider monthEventsProvider){
        this.monthEventsProvider = monthEventsProvider;
    }


    public MonthResponse buildMonthSkeleton(Integer year, Integer month){

        MonthResponse monthDaysSkeleton  = new MonthResponse();
        List<DayResponse> dayResponses = new ArrayList<>();

        YearMonth yearMonth = YearMonth.of(year,month);

        for (int i =1; i<=yearMonth.lengthOfMonth(); i++){
            LocalDate date = LocalDate.of(year,month,i);
            DayResponse dateTitle= new DayResponse();
            List<String> l = new ArrayList<>();
            dateTitle.setDate(date);
            dateTitle.setEventTitles(l);

            dayResponses.add(dateTitle);
        }
        monthDaysSkeleton.setDays(dayResponses);
        monthDaysSkeleton.setYear(year);
        monthDaysSkeleton.setMonth(month);
        return monthDaysSkeleton;
    }

    public MonthResponse getAllMonthEvents(Integer year, Integer month){
        MonthResponse skeleton = buildMonthSkeleton(year, month);
        Map<Integer, List<String>> eventsByDay = monthEventsProvider.aggregateRulesEvents(year,month);

            for (DayResponse dayResponse: skeleton.getDays()){
                Integer dayNumber = dayResponse.getDate().getDayOfMonth();
                List<String> titles = eventsByDay.get(dayNumber);
                if ( titles != null ){
                    dayResponse.setEventTitles(titles);
                }
            }

        return skeleton;
    }
}
