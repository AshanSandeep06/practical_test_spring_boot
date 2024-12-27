package com.visionex_digital.spring_boot_test.bean.other;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Title: WeatherSummaryBean Class
 * Description: Weather summary bean class for fetch weather data from external api
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherSummaryBean {
    @JsonProperty("list")
    private List<WeatherEntry> list;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherEntry {
        @JsonProperty("dt_txt")
        private String dateText;

        @JsonProperty("main")
        private Main main;

        public LocalDate getDate() {
            return LocalDate.parse(dateText.split(" ")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        @JsonProperty("temp")
        private double temp;
    }
}
