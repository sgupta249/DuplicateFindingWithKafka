package com.alive.springboot.servies.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.alive.springboot"})
public class DuplicateApp {

    public static void main(String[] args) {
        SpringApplication.run(DuplicateApp.class, args);
    }

}
