package com.ifigeneia.mooncalendar.service;

import com.ifigeneia.mooncalendar.persistence.entity.EventDateRule;
import com.ifigeneia.mooncalendar.repository.EventDateRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class FixedRangeRuleService {

    private final EventDateRuleRepository eventDateRuleRepository;
    @Autowired
    public FixedRangeRuleService(EventDateRuleRepository eventDateRuleRepository){

        this.eventDateRuleRepository = eventDateRuleRepository;
    }



    public Map<Integer, List<String>> getFixedRangeEvents(Integer year, Integer month){
         List<EventDateRule> byFixedRange = eventDateRuleRepository.findByDateRuleType_Code("fixed_range");


         Map<Integer, List<String>>  eventsByFixedRangeScattered = new TreeMap<>();

         Integer numberOfDays = YearMonth.of(year,month).lengthOfMonth();

        for (EventDateRule r:byFixedRange){
            if (r.getStartMonth() <=  r.getEndMonth() && month >= r.getStartMonth() && month <= r.getEndMonth()){
                if (r.getEndMonth().equals(r.getStartMonth())){
                    addEventForDayRange(eventsByFixedRangeScattered, r.getStartDay(),r.getEndDay(), r.getEvent().getName());
                }
                else if (month>r.getStartMonth() && month<r.getEndMonth()){
                    addEventForDayRange(eventsByFixedRangeScattered, 1,numberOfDays, r.getEvent().getName());

                }
                else if (month.equals(r.getStartMonth())){
                    addEventForDayRange(eventsByFixedRangeScattered, r.getStartDay(),numberOfDays, r.getEvent().getName());
                }
                else if (month.equals(r.getEndMonth())){
                    addEventForDayRange(eventsByFixedRangeScattered, 1,r.getEndDay(), r.getEvent().getName());
                }

            } else if (r.getStartMonth() >  r.getEndMonth() && (month>=r.getStartMonth() || month<=r.getEndMonth()) ){

                if (month.equals(r.getStartMonth() )){
                    addEventForDayRange(eventsByFixedRangeScattered, r.getStartDay(),numberOfDays, r.getEvent().getName());

                }
                else if (month.equals(r.getEndMonth() )){
                    addEventForDayRange(eventsByFixedRangeScattered, 1,r.getEndDay(), r.getEvent().getName());

                }
                else if (month>r.getStartMonth()){
                    addEventForDayRange(eventsByFixedRangeScattered, 1,numberOfDays, r.getEvent().getName());
                }
                else if ( month<r.getEndMonth()){
                    addEventForDayRange(eventsByFixedRangeScattered, 1,numberOfDays, r.getEvent().getName());
                }
            }
         }

        return eventsByFixedRangeScattered;
    }

    public void addEventForDayRange(Map<Integer,List<String>> m, Integer startDay,Integer  endDay, String eventTitle){
        for (int i = startDay; i<= endDay; i++){
            m.computeIfAbsent(i,k->new ArrayList<>()).add(eventTitle);
        }
    }
}
