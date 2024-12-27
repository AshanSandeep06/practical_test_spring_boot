package com.visionex_digital.spring_boot_test.exception;

/**
 * Title: InvalidCityNameException Class
 * Description: InvalidCityNameException class
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */
public class InvalidCityNameException extends RuntimeException {
    public InvalidCityNameException(String message) {
        super(message);
    }
}
