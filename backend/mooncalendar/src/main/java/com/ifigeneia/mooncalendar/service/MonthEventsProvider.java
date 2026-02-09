package com.ifigeneia.mooncalendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class MonthEventsProvider {

    private final FixedDayRuleService fixedDay;
    private final FixedRangeRuleService fixedRange;
    @Autowired
    public MonthEventsProvider(FixedDayRuleService fixedDay,FixedRangeRuleService fixedRange){

        this.fixedDay=fixedDay;
        this.fixedRange=fixedRange;
    }


    public Map<Integer, List<String>> aggregateRulesEvents(Integer year, Integer month){
        Map<Integer, List<String>> aggregateResults = new TreeMap<>();
        Map<Integer, List<String>> fixedDayEvents = fixedDay.getFixedDayEvents(year,month);
        Map<Integer, List<String>> fixedRangeEvents = fixedRange.getFixedRangeEvents(year,month);

        for (Integer i : fixedDayEvents.keySet()){
            if (!(fixedRangeEvents.containsKey(i))){
                fixedRangeEvents.put(i,fixedDayEvents.get(i));
            } else {

                List<String> l=fixedDayEvents.get(i);
                for(String s : l){
                    fixedRangeEvents.get(i).add(s);
                }
            }
        }
        aggregateResults.putAll(fixedRangeEvents);

        return aggregateResults;

    }
}
