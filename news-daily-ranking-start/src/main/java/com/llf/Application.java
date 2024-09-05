package com.llf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: llf
 * @date: 2024/09/05
 * @description: 核心启动类
 * @version: 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.llf"})
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
