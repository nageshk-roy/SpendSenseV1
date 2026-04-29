package com.spendsens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpendsensApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpendsensApplication.class, args);
    }
}
