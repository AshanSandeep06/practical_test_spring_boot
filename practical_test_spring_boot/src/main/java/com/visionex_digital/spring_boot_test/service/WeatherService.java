package com.visionex_digital.spring_boot_test.service;

import com.visionex_digital.spring_boot_test.bean.response.ResponseBean;
import com.visionex_digital.spring_boot_test.bean.response.WeatherSummaryResBean;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Title: WeatherService Class
 * Description: This is for weather service
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */
public interface WeatherService {
    ResponseEntity<ResponseBean> getWeatherSummary(String city);
}
