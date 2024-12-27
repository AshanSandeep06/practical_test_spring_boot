package com.visionex_digital.spring_boot_test.exception;

/**
 * Title: InvalidDataException Class
 * Description: This is a exception class for invalid data
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */
public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}
