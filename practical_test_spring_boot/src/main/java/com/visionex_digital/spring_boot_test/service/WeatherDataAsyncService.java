package com.visionex_digital.spring_boot_test.service;

import com.visionex_digital.spring_boot_test.bean.response.WeatherSummaryResBean;
import java.util.concurrent.CompletableFuture;

/**
 * Title: WeatherDataAsyncService Class
 * Description: WeatherDataAsyncService class
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */
public interface WeatherDataAsyncService {
    CompletableFuture<WeatherSummaryResBean> getWeatherData(String city);
}
