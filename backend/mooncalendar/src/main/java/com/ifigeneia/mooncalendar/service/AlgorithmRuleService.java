package com.ifigeneia.mooncalendar.service;

import com.ifigeneia.mooncalendar.persistence.entity.EventDateRule;
import com.ifigeneia.mooncalendar.repository.EventDateRuleRepository;
import com.ifigeneia.mooncalendar.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.awt.desktop.AboutEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
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
            if (eventDate.getAnchor().equals("Pascha")) {
                LocalDate tempStart = easterDate.plusDays(eventDate.getStartOffsetDays());
                LocalDate tempEnd = easterDate.plusDays(eventDate.getEndOffsetDays());
                // Single Day Event
                if (tempStart.equals(tempEnd) && tempStart.getMonthValue() == month) {
                    eventsbyAlgorithm.computeIfAbsent(tempStart.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                }
                // Event that stretches over a period of time and month falls on start date of event
                else if (tempStart.getMonthValue() == month) {
                    if (tempEnd.getMonthValue() != month) {
                        int daysInmonth = YearMonth.of(year, month).lengthOfMonth();
                        for (int i = tempStart.getDayOfMonth(); i <= daysInmonth; i++) {
                            eventsbyAlgorithm.computeIfAbsent(i, key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                        }
                    } else {
                        for (int i = tempStart.getDayOfMonth(); i <= tempEnd.getDayOfMonth(); i++) {
                            eventsbyAlgorithm.computeIfAbsent(i, key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                        }
                    }
                }
                // Event that stretches over a period of time and month falls on end date of event
                else if (tempEnd.getMonthValue() == month) {
                    for (int i = 1; i <= tempEnd.getDayOfMonth(); i++) {
                        eventsbyAlgorithm.computeIfAbsent(i, key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                    }
                }
            }
            else if (eventDate.getAnchor().equals("Christmas")){
                LocalDate ChristmasDay = LocalDate.of(year,month,25);
                DayOfWeek dayofChristmas = ChristmasDay.getDayOfWeek();
                int daynumber = dayofChristmas.getValue();
                if (eventDate.getEvent().getId()== 35 && month==12 ){
                    DayOfWeek day = LocalDate.of(year,month,11).getDayOfWeek();
                    int Daynumber = day.getValue();
                    if (Daynumber == 7){
                        eventsbyAlgorithm.computeIfAbsent(11, key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                    } else{

                        LocalDate event;
                        switch(daynumber){
                            case 1:event= ChristmasDay.plusDays(-8);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 2:event= ChristmasDay.plusDays(-9);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 3:event= ChristmasDay.plusDays(-10);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 4:event= ChristmasDay.plusDays(-11);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 5:event= ChristmasDay.plusDays(-12);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 6:event= ChristmasDay.plusDays(-13);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 7:event= ChristmasDay.plusDays(-14);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                        }
                    }
                }
                if (eventDate.getEvent().getId()== 40 && month==12 ){
                    DayOfWeek day = LocalDate.of(year,month,18).getDayOfWeek();
                    int Daynumber = day.getValue();
                    if (Daynumber == 7){
                        eventsbyAlgorithm.computeIfAbsent(18, key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                    } else{
                        LocalDate event;
                        switch(daynumber){
                            case 1:event= ChristmasDay.plusDays(-1);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 2:event= ChristmasDay.plusDays(-2);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 3:event= ChristmasDay.plusDays(-3);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 4:event= ChristmasDay.plusDays(-4);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 5:event= ChristmasDay.plusDays(-5);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 6:event= ChristmasDay.plusDays(-6);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 7:event= ChristmasDay.plusDays(-7);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                        }
                    }
                }
                if (eventDate.getEvent().getId()== 48 && month==12 ){
                    DayOfWeek day = LocalDate.of(year,month,26).getDayOfWeek();
                    int Daynumber = day.getValue();
                    if (Daynumber == 7){
                        eventsbyAlgorithm.computeIfAbsent(18, key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                    } else{
                        LocalDate event;
                        switch(daynumber){
                            case 1:event= ChristmasDay.plusDays(6);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 2:event= ChristmasDay.plusDays(5);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 3:event= ChristmasDay.plusDays(4);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 4:event= ChristmasDay.plusDays(3);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 5:event= ChristmasDay.plusDays(2);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                            case 6:event= ChristmasDay.plusDays(1);
                                eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                                break;
                        }
                    }
                }

            }

        }
        return eventsbyAlgorithm;
    }

    // calculate Easter date for given
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
