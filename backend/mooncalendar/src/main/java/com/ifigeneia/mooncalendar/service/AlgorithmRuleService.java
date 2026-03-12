package com.ifigeneia.mooncalendar.service;

import com.ifigeneia.mooncalendar.persistence.entity.EventDateRule;
import com.ifigeneia.mooncalendar.repository.EventDateRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
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

        for (EventDateRule eventDate: byRelativeAlgorithm) {
            if (eventDate.getEvent().getId() == 76) {
                LocalDate clean_monday = easterDate.plusDays(-48);
                LocalDate event_st_george = LocalDate.of(year, eventDate.getStartMonth(), eventDate.getStartDay());
                if (event_st_george.isBefore(clean_monday)) {
                    eventsbyAlgorithm.computeIfAbsent(eventDate.getStartDay(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                } else {
                    eventsbyAlgorithm.computeIfAbsent(easterDate.plusDays(2).getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                }
            }
            if (eventDate.getAnchor().equals("Pascha")) {
                LocalDate tempStart = easterDate.plusDays(eventDate.getStartOffsetDays());
                LocalDate tempEnd = easterDate.plusDays(eventDate.getEndOffsetDays());
                Integer daysInmonth = YearMonth.of(year, month).lengthOfMonth();
                // Single Day Event
                if (tempStart.equals(tempEnd) && tempStart.getMonthValue() == month) {
                    eventsbyAlgorithm.computeIfAbsent(tempStart.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                }
                // Event that stretches over a period of time and month falls on start date of event
                else if (tempStart.getMonthValue() == month) {
                    if (tempEnd.getMonthValue() != month) {

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
                } else if (tempStart.getMonthValue() < month && month < tempEnd.getMonthValue()) {

                    for (int i = 1; i <= daysInmonth; i++) {
                        eventsbyAlgorithm.computeIfAbsent(i, key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                    }
                }
            } else if (eventDate.getAnchor().equals("Christmas")) {
                LocalDate ChristmasDay = LocalDate.of(year, 12, 25);
                DayOfWeek dayofChristmas = ChristmasDay.getDayOfWeek();

                if (eventDate.getEvent().getCode().equals("feast.sunday_forefathers") && month == 12) {
                    int daystoSub = (dayofChristmas.getValue()==7) ? 14 : dayofChristmas.getValue()+7;
                    LocalDate sundayForefathers = ChristmasDay.minusDays(daystoSub);

                    if (sundayForefathers.getYear() == year && sundayForefathers.getMonthValue() == month){
                        eventsbyAlgorithm.computeIfAbsent(sundayForefathers.getDayOfMonth(),key->new ArrayList<>()).add((eventDate.getEvent().getName()));
                    }
                }
                if (eventDate.getEvent().getCode().equals("feast.sunday_before_nativity") && month == 12) {
                    int daysToSubs = (dayofChristmas.getValue() == 7) ? 7 : (dayofChristmas.getValue());
                    LocalDate sundayBeforeChristmas = ChristmasDay.minusDays(daysToSubs);


                    if (sundayBeforeChristmas.getYear() == year && sundayBeforeChristmas.getMonthValue() == month) {
                        eventsbyAlgorithm.computeIfAbsent(sundayBeforeChristmas.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                        }
                    }
                if (eventDate.getEvent().getCode().equals("feast.sunday_after_nativity")) {
                    Integer anchorYear = year;
                    if (month.equals(12)) {
                        anchorYear = year;
                    } else if (month.equals(1)) {
                        anchorYear = year - 1;
                    } else {
                        continue;
                    }
                    LocalDate NativityS = LocalDate.of(anchorYear, 12, 26);
                    DayOfWeek day = NativityS.getDayOfWeek();
                    int Daynumber = day.getValue();
                    int daysToAdd = (Daynumber == 7) ? 0 : (7 - Daynumber);
                    LocalDate event = NativityS.plusDays(daysToAdd);
                    if (event.getYear() == year && event.getMonthValue() == month) {
                        eventsbyAlgorithm.computeIfAbsent(event.getDayOfMonth(), key -> new ArrayList<>()).add(eventDate.getEvent().getName());
                    }
                }
            }
        }
        return eventsbyAlgorithm;
    }

    // calculate Easter date for given year
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
