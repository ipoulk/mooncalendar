package com.ifigeneia.mooncalendar;

import com.ifigeneia.mooncalendar.persistence.entity.Event;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@SpringBootApplication
public class MooncalendarApplication {

	public static void main(String[] args) {
		SpringApplication.run(MooncalendarApplication.class, args);
	}


}
