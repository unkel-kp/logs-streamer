package com.blurryface.streamer.controller;

import com.blurryface.streamer.service.LogsStreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/logs/stream")
public class LogsStreamingController {

    @Autowired
    private LogsStreamingService logsStreamingService;

    @GetMapping(path = "/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamLogs() {
        return logsStreamingService.streamLogs();
    }

}
