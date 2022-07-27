package ru.is.testcase.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class SubstringException extends BaseException{

    public SubstringException(String message) {
        super(BAD_REQUEST, message);
    }
}
