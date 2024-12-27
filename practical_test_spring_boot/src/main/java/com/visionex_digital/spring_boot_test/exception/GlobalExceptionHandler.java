package com.visionex_digital.spring_boot_test.exception;

import com.visionex_digital.spring_boot_test.bean.response.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static com.visionex_digital.spring_boot_test.constant.AppConstant.*;

/**
 * Title: GlobalExceptionHandler Class
 * Description: This is the class for handling global exceptions through whole application
 * Created by Ashan Sandeep on 12/27/2024
 * Email: ashansandeep06@gmail.com
 * Java Version: 17
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<ResponseBean> handleBadRequest(Exception ex) {
        log.error("Bad Request Exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseBean(BAD_REQUEST, ex.getMessage(), null));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ResponseBean> handleGeneralExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseBean(INTERNAL_SERVER_ERROR, "An unexpected error occurred", null));
    }

    @ExceptionHandler(value = NoDataFoundException.class)
    public ResponseEntity<ResponseBean> handleNoDataFoundExceptions(NoDataFoundException ex) {
        log.error("Error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseBean(NOT_FOUND, ex.getMessage(), null));
    }

    @ExceptionHandler(value = InvalidDataException.class)
    public ResponseEntity<ResponseBean> handleInvalidDataExceptions(InvalidDataException ex) {
        log.error("Error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBean(INVALID_DATA, ex.getMessage(), null));
    }

    @ExceptionHandler(value = InvalidCityNameException.class)
    public ResponseEntity<ResponseBean> handleCityNotFoundException(InvalidCityNameException ex) {
        log.error("Error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseBean(INVALID_DATA, ex.getMessage(), null));
    }

    @ExceptionHandler(value = ApiUnavailableException.class)
    public ResponseEntity<ResponseBean> handleApiUnavailableException(ApiUnavailableException ex) {
        log.error("Error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseBean(SERVICE_UNAVAILABLE, ex.getMessage(), null));
    }
}
