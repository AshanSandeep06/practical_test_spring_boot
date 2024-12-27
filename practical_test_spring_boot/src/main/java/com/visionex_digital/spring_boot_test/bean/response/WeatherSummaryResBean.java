package com.visionex_digital.spring_boot_test.bean.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Title: WeatherSummaryResBean Class
 * Description: This is the response bean class for weather summary
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WeatherSummaryResBean {
    private String city;
    private double averageTemperature;
    private String hottestDay;
    private String coldestDay;

    public WeatherSummaryResBean(String city, double averageTemperature, LocalDate hottestDay, LocalDate coldestDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.city = city;
        this.averageTemperature = averageTemperature;
        this.hottestDay = hottestDay != null ? LocalDate.parse(hottestDay.toString()).format(formatter) : null;
        this.coldestDay = coldestDay != null ? LocalDate.parse(coldestDay.toString()).format(formatter) : null;
    }
}
