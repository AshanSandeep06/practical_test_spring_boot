package com.visionex_digital.spring_boot_test.constant;

/**
 * Title: AppConstant Class
 * Description: This is a common constants class
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */
public class AppConstant {
    private AppConstant() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // Status Codes
    public static final String SUCCESS = "00";
    public static final String NOT_FOUND = "01";
    public static final String INVALID_DATA = "02";
    public static final String BAD_REQUEST = "03";
    public static final String INTERNAL_SERVER_ERROR = "04";
    public static final String SERVICE_UNAVAILABLE = "05";
}
