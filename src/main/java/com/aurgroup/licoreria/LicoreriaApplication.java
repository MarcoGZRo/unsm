package com.aurgroup.licoreria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LicoreriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicoreriaApplication.class, args);
        System.out.println("running: http://localhost:8080");
    }
}
