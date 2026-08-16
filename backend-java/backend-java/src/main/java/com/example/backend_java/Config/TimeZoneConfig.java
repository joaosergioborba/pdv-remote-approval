package com.example.backend_java.Config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class TimeZoneConfig {

    @PostConstruct
    public void TimeZoneConfig(){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Cuiaba"));
    }
}
