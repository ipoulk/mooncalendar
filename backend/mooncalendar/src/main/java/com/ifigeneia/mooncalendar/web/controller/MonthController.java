package com.ifigeneia.mooncalendar.web.controller;

import com.ifigeneia.mooncalendar.service.AlgorithmRuleService;
import com.ifigeneia.mooncalendar.service.MonthService;
import com.ifigeneia.mooncalendar.web.dto.response.MonthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MonthController {

    private static final Logger logger = LoggerFactory.getLogger(MonthController.class);


    private final MonthService monthService;
    @Autowired
    public MonthController(MonthService monthService){
        this.monthService = monthService;
    }


    @GetMapping("/month")
    public MonthResponse thesearethemonths( @RequestParam("year") Integer year,  @RequestParam("month") Integer month){
        if(year == null || month == null){
            String result = String.format("%s year or month invalid");
            logger.warn(result);
        } else {
            return monthService.getAllMonthEvents(year,month);
        }

        return monthService.getAllMonthEvents(year,month);
    }
}
