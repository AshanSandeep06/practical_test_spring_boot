package com.visionex_digital.spring_boot_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Title: WeatherBackendApplication Class
 * Description: Spring boot main starter application
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */

@SpringBootApplication
@EnableAsync
public class WeatherBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeatherBackendApplication.class, args);
    }
}
