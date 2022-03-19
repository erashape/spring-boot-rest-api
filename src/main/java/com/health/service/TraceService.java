package com.health.service;

import brave.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TraceService {
    private final Tracer tracer;

    public String spanId() {
        var span = tracer.currentSpan();
        return span.context().spanIdString();
    }

    public String traceId() {
        var span = tracer.currentSpan();
        return span.context().traceIdString();
    }
}
