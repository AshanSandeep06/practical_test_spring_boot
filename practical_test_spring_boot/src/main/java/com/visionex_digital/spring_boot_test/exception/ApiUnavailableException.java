package com.visionex_digital.spring_boot_test.exception;

/**
 * Title: ApiUnavailableException Class
 * Description: ApiUnavailableException class
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */
public class ApiUnavailableException extends RuntimeException {
    public ApiUnavailableException(String message) {
        super(message);
    }
}
