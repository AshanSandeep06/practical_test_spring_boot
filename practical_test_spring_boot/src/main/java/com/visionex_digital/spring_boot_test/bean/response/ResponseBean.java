package com.visionex_digital.spring_boot_test.bean.response;

import java.io.Serializable;

import static com.visionex_digital.spring_boot_test.constant.AppConstant.*;

/**
 * Title: ResponseBean Class
 * Description: This is the main response bean class
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */

public record ResponseBean(String status, String message, Object content) implements Serializable {
    public static ResponseBean createWithOutContent(String status, String message) {
        return new ResponseBean(status, message, null);
    }

    public static ResponseBean success(Object content) {
        return new ResponseBean(SUCCESS, "Success", content);
    }

    public static ResponseBean notfound(String message) {
        return new ResponseBean(NOT_FOUND, message, null);
    }
}
