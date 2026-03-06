package com.ifigeneia.mooncalendar.service;

import com.ifigeneia.mooncalendar.persistence.entity.EventDateRule;
import com.ifigeneia.mooncalendar.repository.EventDateRuleRepository;
import com.ifigeneia.mooncalendar.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.awt.desktop.AboutEvent;
import java.time.LocalDate;
import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class AlgorithmRuleService {

    private final EventDateRuleRepository eventsByRelativeAlgorithm;
    @Autowired
    public AlgorithmRuleService(EventDateRuleRepository eventsByRelativeAlgorithm){
        this.eventsByRelativeAlgorithm = eventsByRelativeAlgorithm;
    }

    public Map<Integer, List<String>> getRelativeAlgorithmEvents(Integer year, Integer month){
        List<EventDateRule> byRelativeAlgorithm = eventsByRelativeAlgorithm.findByDateRuleType_Code("relative_algorithm");

        Map<Integer, List<String>> eventsbyAlgorithm = new TreeMap<>();

        LocalDate easterDate = calculateEaster(year);
        Integer m = easterDate.getMonthValue();
        for (EventDateRule eventDate: byRelativeAlgorithm){
            if (m !=month){
                LocalDate tempStart = easterDate.plusDays(eventDate.getEndOffsetDays());
                LocalDate tempEnd = easterDate.plusDays(eventDate.getEndOffsetDays());
                if (tempStart.getMonthValue()==month && tempStart.equals(tempEnd))  {
                    eventsbyAlgorithm.computeIfAbsent(tempStart.getDayOfMonth(),key->new ArrayList<>()).add(eventDate.getEvent().getName());
                }
                else if (tempStart.getMonthValue()==month){
                    for (int i = tempStart.getDayOfMonth(); i<=tempEnd.getDayOfMonth();i++){
                        eventsbyAlgorithm.computeIfAbsent(i,key->new ArrayList<>()).add(eventDate.getEvent().getName());
                    }
                }
            }

        }
        return eventsbyAlgorithm;
    }

    // calculate Easter
    private LocalDate calculateEaster(Integer year){

        // using Meeus-style
        // first calculate a few remainders from the input year
        int remainder1 = year%4;
        int remainder2 = year%7;
        int remainder3 = year%19;

        int paschalFullMoonOffset = (19*remainder3 + 15)%30;
        int sundayOffset = (2*remainder1 + 4*remainder2  + 6 * paschalFullMoonOffset + 6)%7;
        int paschaDay = 22 + paschalFullMoonOffset + sundayOffset;

        int julianDay = paschaDay;
        int julianMonth = 3;

        if (paschaDay > 31){
            julianDay = paschaDay - 31;
            julianMonth = 4;
        }

        long jdn = jdnFromJulianDate(year, julianMonth,julianDay);
        return LocalDate.of(1970,1,1).with(JulianFields.JULIAN_DAY,jdn);
    }

    // JDN for a date in the JULIAN calendar
    private long jdnFromJulianDate(int y, int m, int d) {
        int a = (14 - m) / 12;
        int y2 = y + 4800 - a;
        int m2 = m + 12 * a - 3;

        // Julian calendar: no Gregorian century correction term
        return d + (153L * m2 + 2) / 5 + 365L * y2 + y2 / 4 - 32083;
    }

}
