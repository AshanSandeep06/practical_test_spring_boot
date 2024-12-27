package com.visionex_digital.spring_boot_test.util;

/**
 * Title: ResponseMessageConstant Class
 * Description: ResponseMessageConstant class
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */
public class ResponseMessageConstant {
    private ResponseMessageConstant() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final String CITY_NAME_REQUIRED = "City name is required";
    public static final String WEATHER_DATA_UNAVAILABLE = "Weather data is unavailable";
    public static final String EXTERNAL_WEATHER_API_UNAVAILABLE = "External Weather API is unavailable";
}
