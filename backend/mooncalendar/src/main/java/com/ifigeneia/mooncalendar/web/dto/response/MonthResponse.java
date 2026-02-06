package com.ifigeneia.mooncalendar.web.dto.response;

import java.time.LocalDate;
import java.util.List;

public class MonthResponse {

    private Integer year;
    private Integer month;
    private List<DayResponse> days;


    public Integer getYear(){
        return year;
    }

    public Integer getMonth(){
        return month;
    }

    public List<DayResponse> getDays(){
        return days;
    }
}
