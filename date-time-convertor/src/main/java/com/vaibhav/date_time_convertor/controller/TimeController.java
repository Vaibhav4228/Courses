package com.vaibhav.date_time_convertor.controller;


import com.vaibhav.date_time_convertor.service.TimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TimeController {
    @Autowired
    TimeService timeService;

    @GetMapping("/time")
    public LocalDateTime getCurrentTime(@RequestParam(defaultValue = "false") boolean keepMs) {
        log.info("Current time requested with keepMs: {}", keepMs);
        LocalDateTime now = LocalDateTime.now();
        log.info("Current time is: {}", now);
        return timeService.processTime(now, keepMs);
    }

    @PostMapping("/custom-time")
    public LocalDateTime convertCustomTime(@RequestParam String dateTime, @RequestParam(defaultValue = "false") boolean keepMs) {
        log.info("Custom time received: {} with keepMs: {}", dateTime, keepMs);
        return timeService.parseAndProcess(dateTime, keepMs);
    }
}