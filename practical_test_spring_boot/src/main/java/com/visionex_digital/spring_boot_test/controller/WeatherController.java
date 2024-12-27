package com.visionex_digital.spring_boot_test.controller;

import com.visionex_digital.spring_boot_test.bean.response.ResponseBean;
import com.visionex_digital.spring_boot_test.bean.response.WeatherSummaryResBean;
import com.visionex_digital.spring_boot_test.exception.InvalidDataException;
import com.visionex_digital.spring_boot_test.exception.NoDataFoundException;
import com.visionex_digital.spring_boot_test.service.WeatherService;
import com.visionex_digital.spring_boot_test.util.ResponseMessageConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

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

    @GetMapping(value = "/weather", name = "Get Weather Data", params = "city")
    public ResponseEntity<ResponseBean> getWeather(@RequestParam String city) {
        log.debug("Get weather summary request received");
        log.debug("Get weather data for city: {}", city);

        if (city == null || city.trim().isEmpty()) {
            throw new NoDataFoundException(ResponseMessageConstant.CITY_NAME_REQUIRED);
        }

        return weatherService.getWeatherSummary(city);
    }
}
