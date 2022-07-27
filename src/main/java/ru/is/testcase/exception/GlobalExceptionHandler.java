package ru.is.testcase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MagicSquareException.class, SubstringException.class})
    public ResponseEntity<ApiError> froudHandle(BaseException exception) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                Collections.singletonList(exception.getMessage()));
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(apiError);
    }
}
