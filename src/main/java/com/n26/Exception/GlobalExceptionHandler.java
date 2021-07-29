package com.n26.Exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by oyelakin on 5/1/2021.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public void httpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletResponse response) {
        if (ex.getCause() instanceof InvalidFormatException)
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        else
            response.setStatus(HttpStatus.BAD_REQUEST.value());

    }
}
