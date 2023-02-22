package com.blurryface.streamer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(LogsStreamingController.class);

    @GetMapping
    public String testLogs(@RequestParam Integer count) {
        for(int i = 0; i < count; i++)
            logger.info("This is just a test log.");
        return "Logged Successfully!";
    }
}
