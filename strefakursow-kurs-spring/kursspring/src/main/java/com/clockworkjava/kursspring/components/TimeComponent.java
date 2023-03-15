package com.clockworkjava.kursspring.components;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TimeComponent {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private LocalDateTime time = LocalDateTime.now();

    public String getTime() {
        return time.format(formatter);
    }

    public void setTime(String time) {
        this.time = LocalDateTime.parse(time, formatter);
    }
}
