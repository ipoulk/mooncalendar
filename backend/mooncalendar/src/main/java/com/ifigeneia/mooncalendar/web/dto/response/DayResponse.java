package com.ifigeneia.mooncalendar.web.dto.response;

import java.time.LocalDate;
import java.util.List;

public class DayResponse {

    private LocalDate date;
    private List<String> eventTitles;

    public LocalDate getDate()
    {
        return date;
    }

    public List<String> getEventTitles(){
        return eventTitles;
    }
}
