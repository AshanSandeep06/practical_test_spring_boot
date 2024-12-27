package com.visionex_digital.spring_boot_test.service.impl;

import com.visionex_digital.spring_boot_test.bean.other.WeatherSummaryBean;
import com.visionex_digital.spring_boot_test.bean.response.WeatherSummaryResBean;
import com.visionex_digital.spring_boot_test.exception.ApiUnavailableException;
import com.visionex_digital.spring_boot_test.exception.InvalidCityNameException;
import com.visionex_digital.spring_boot_test.service.WeatherDataAsyncService;
import com.visionex_digital.spring_boot_test.util.ResponseMessageConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Title: WeatherDataAsyncServiceImpl Class
 * Description: WeatherDataAsyncServiceImpl class
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherDataAsyncServiceImpl implements WeatherDataAsyncService {
    private final WebClient webClient;

    @Value("${api_key}")
    private String API_KEY;

    @Override
    @Async
    @Cacheable(value = "weatherSummary", key = "#city")
    public CompletableFuture<WeatherSummaryResBean> getWeatherData(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/forecast")
                        .queryParam("q", city)
                        .queryParam("appid", API_KEY)
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .bodyToMono(WeatherSummaryBean.class)
                .mapNotNull(weatherSummaryBean -> processWeatherSummary(weatherSummaryBean, city))
                .onErrorMap(WebClientResponseException.NotFound.class, ex -> new InvalidCityNameException("Invalid City Name: " + city))
                .onErrorMap(WebClientResponseException.class, ex -> new ApiUnavailableException(ResponseMessageConstant.EXTERNAL_WEATHER_API_UNAVAILABLE))
                .toFuture();
    }

    private WeatherSummaryResBean processWeatherSummary(WeatherSummaryBean data, String city) {
        List<WeatherSummaryBean.WeatherEntry> entries = data.getList();

        // Filter data for the last 7 days
        LocalDate today = LocalDate.now();
        List<WeatherSummaryBean.WeatherEntry> lastSevenDaysEntries = entries.stream()
                .filter(entry -> !entry.getDate().isBefore(today.minusDays(7)))
                .toList();

        LocalDate hottestDay = null;
        LocalDate coldestDay = null;
        double totalTemp = 0;
        double maxTemp = Double.MIN_VALUE;
        double minTemp = Double.MAX_VALUE;

        for (WeatherSummaryBean.WeatherEntry entry : lastSevenDaysEntries) {
            LocalDate date = entry.getDate();
            double temp = entry.getMain().getTemp();
            totalTemp += temp;

            if (temp > maxTemp) {
                maxTemp = temp;
                hottestDay = date;
            }
            if (temp < minTemp) {
                minTemp = temp;
                coldestDay = date;
            }
        }
        return new WeatherSummaryResBean(city, totalTemp / lastSevenDaysEntries.size(), hottestDay, coldestDay);
    }
}
