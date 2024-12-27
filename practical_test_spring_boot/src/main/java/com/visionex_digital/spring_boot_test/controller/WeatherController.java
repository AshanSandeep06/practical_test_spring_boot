package com.visionex_digital.spring_boot_test.controller;

import com.visionex_digital.spring_boot_test.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: WeatherController Class
 * Description: This is a class for exposing weather endpoints
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Validated
@Slf4j
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;
}
