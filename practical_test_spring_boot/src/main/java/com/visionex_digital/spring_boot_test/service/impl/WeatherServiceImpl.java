package com.visionex_digital.spring_boot_test.service.impl;

import com.visionex_digital.spring_boot_test.bean.other.WeatherSummaryBean;
import com.visionex_digital.spring_boot_test.bean.response.ResponseBean;
import com.visionex_digital.spring_boot_test.bean.response.WeatherSummaryResBean;
import com.visionex_digital.spring_boot_test.exception.ApiUnavailableException;
import com.visionex_digital.spring_boot_test.exception.InvalidCityNameException;
import com.visionex_digital.spring_boot_test.exception.NoDataFoundException;
import com.visionex_digital.spring_boot_test.service.WeatherDataAsyncService;
import com.visionex_digital.spring_boot_test.service.WeatherService;
import com.visionex_digital.spring_boot_test.util.GlobalObjectMapper;
import com.visionex_digital.spring_boot_test.util.ResponseMessageConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Title: WeatherServiceImpl Class
 * Description: This is for weather service impl
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {
    private final WeatherDataAsyncService weatherDataAsyncService;

    @Override
    public ResponseEntity<ResponseBean> getWeatherSummary(String city) {
        try {
            // Calling asynchronous method and waiting for the result with a timeout
            WeatherSummaryResBean weatherSummary = weatherDataAsyncService.getWeatherData(city).get(10, TimeUnit.SECONDS);
            log.info("Weather summary for city: {} is: {}", city, GlobalObjectMapper.toJson(weatherSummary));
            return ResponseEntity.ok(ResponseBean.success(weatherSummary));
        } catch (TimeoutException e) {
            log.error("Timeout occurred while fetching weather data for city: {}", city, e);
            throw new RuntimeException("Timeout occurred while fetching weather data for city: " + city);
        } catch (ExecutionException e) {
            log.error("Execution error occurred while fetching weather data for city: {}", city, e);

            if (e.getCause().getMessage().contains("Invalid City Name")) {
                throw new InvalidCityNameException("Invalid city name: " + city);
            } else if (e.getCause().getMessage().contains("External weather API unavailable")) {
                throw new ApiUnavailableException("External weather API unavailable");
            } else {
                throw new NoDataFoundException(ResponseMessageConstant.WEATHER_DATA_UNAVAILABLE);
            }


        } catch (InterruptedException e) {
            log.error("Interrupted while fetching weather data for city: {}", city, e);
            throw new RuntimeException("Interrupted while fetching weather data for city: " + city);
        }
    }

    /*public CompletableFuture<WeatherSummaryResBean> getWeatherData(String city) {
        // First check the cache
        WeatherSummaryResBean cachedData = getCachedWeatherData(city);
        if (cachedData != null) {
            return CompletableFuture.completedFuture(cachedData);
        }

        // If not cached, fetch asynchronously
        return fetchWeatherDataAsync(city).thenApply(weatherSummary -> {
            // Store the result in the cache
            cacheWeatherData(city, weatherSummary);
            return weatherSummary;
        });
    }

    @Cacheable(value = "weatherSummary", key = "#city", sync = true)
    public WeatherSummaryResBean getCachedWeatherData(String city) {
        return null; // Return null if not found in the cache
    }

    @CachePut(value = "weatherSummary", key = "#city")
    public WeatherSummaryResBean cacheWeatherData(String city, WeatherSummaryResBean weatherSummary) {
        return weatherSummary;
    }

    @Async
    public CompletableFuture<WeatherSummaryResBean> fetchWeatherDataAsync(String city) {
        log.debug("Fetching weather data for city: {}", city);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/forecast")
                        .queryParam("q", city)
                        .queryParam("appid", API_KEY)
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .bodyToMono(WeatherSummaryBean.class)
                .mapNotNull(weatherSummaryBean -> processWeatherSummary(weatherSummaryBean, city))
                .toFuture();
    }

    private WeatherSummaryResBean processWeatherSummary(WeatherSummaryBean weatherSummaryBean, String city) {
        log.debug("Processing weather summary for city: {}", city);

        List<WeatherSummaryBean.WeatherEntry> entries = weatherSummaryBean.getList();
        if (entries == null || entries.isEmpty()) {
            log.warn("No weather data found for city: {}", city);
            throw new NoDataFoundException(ResponseMessageConstant.WEATHER_DATA_UNAVAILABLE);
        }

        double totalTemp = 0;
        LocalDate hottestDay = null;
        LocalDate coldestDay = null;
        double maxTemp = Double.MIN_VALUE;
        double minTemp = Double.MAX_VALUE;

        for (WeatherSummaryBean.WeatherEntry entry : entries) {
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

        return WeatherSummaryResBean.customBuilder()
                .city(city)
                .averageTemperature(totalTemp / entries.size())
                .hottestDay(hottestDay)
                .coldestDay(coldestDay)
                .build();
    }*/
}
