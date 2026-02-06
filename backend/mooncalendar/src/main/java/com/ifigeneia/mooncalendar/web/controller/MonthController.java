package com.ifigeneia.mooncalendar.web.controller;

import com.ifigeneia.mooncalendar.web.dto.response.MonthResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonthController {

    public MonthResponse thesearethemonths(Integer year, Integer month){
        return null;
    }
}
