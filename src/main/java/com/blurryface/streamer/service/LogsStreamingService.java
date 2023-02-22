package com.blurryface.streamer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LogsStreamingService {

    private static final String TOPIC = "logs";
    private final SseTemplate template;

    private final SseEmitter emitter;
    private static final AtomicLong COUNTER = new AtomicLong(0);

    public LogsStreamingService(SseTemplate template, FileMonitor fileMonitor) {
        this.emitter = new SseEmitter(Long.MAX_VALUE);
        this.template = template;
        fileMonitor.listen(file -> {

            try {
                Files.lines(file)
                        .skip(COUNTER.get())
                        .forEach(line ->
                                template.sendMessage(SseEmitter.event()
                                        .id(String.valueOf(COUNTER.incrementAndGet()))
                                        .data(line),
                                        emitter
                                ));
//                                template.broadcast(TOPIC, SseEmitter.event()
//                                        .id(String.valueOf(COUNTER.incrementAndGet()))
//                                        .data(line)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public SseEmitter streamLogs() {
        return this.emitter;
//        return template.newSseEmitter(TOPIC);
    }
}
