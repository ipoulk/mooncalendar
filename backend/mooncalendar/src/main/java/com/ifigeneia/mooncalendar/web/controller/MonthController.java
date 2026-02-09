package com.ifigeneia.mooncalendar.web.controller;

import com.ifigeneia.mooncalendar.service.MonthService;
import com.ifigeneia.mooncalendar.web.dto.response.MonthResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MonthController {


    private final MonthService monthService;
    @Autowired
    public MonthController(MonthService monthService){
        this.monthService = monthService;
    }

    @GetMapping("/month")
    public MonthResponse thesearethemonths( @RequestParam("year") Integer year,  @RequestParam("month") Integer month){

        return monthService.getAllMonthEvents(year,month);
    }
}
