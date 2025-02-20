package com.vaibhav.date_time_convertor.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class TimeService {

    public LocalDateTime processTime(LocalDateTime dateTime, boolean keepMilliseconds) {
        if(keepMilliseconds) {
            log.info("Returning time with milliseconds: {}", dateTime);
            return dateTime;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = dateTime.format(formatter);
        LocalDateTime result = LocalDateTime.parse(formattedTime, formatter);
        log.info("Processed time without milliseconds: {}", result);
        return result;
    }

    public LocalDateTime parseAndProcess(String dateTimeStr, boolean keepMilliseconds) {
        LocalDateTime parsedTime = LocalDateTime.parse(dateTimeStr);
        log.info("Parsed time: {}", parsedTime);
        return processTime(parsedTime, keepMilliseconds);
    }
}

