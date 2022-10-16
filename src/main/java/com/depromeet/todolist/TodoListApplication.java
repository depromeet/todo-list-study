package com.depromeet.todolist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.TimeZone;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class TodoListApplication implements ApplicationListener<ApplicationReadyEvent> {
    private final Environment environment;


    public static void main(String[] args) {
        init();
        SpringApplication.run(TodoListApplication.class, args);
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Spring Server Profiles Status : {}", Arrays.toString(environment.getActiveProfiles()));
    }


    public static void init() {
        log.info("Spring Server TimeZone : Asia/Seoul");
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
