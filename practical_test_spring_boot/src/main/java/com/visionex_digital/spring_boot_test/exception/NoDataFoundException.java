package com.visionex_digital.spring_boot_test.exception;

/**
 * Title: NoDataFoundException Class
 * Description: No data found exceptions class
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */
public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super(message);
    }
}
