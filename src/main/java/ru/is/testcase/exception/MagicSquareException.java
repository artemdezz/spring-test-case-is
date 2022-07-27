package ru.is.testcase.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class MagicSquareException extends BaseException{

    public MagicSquareException(String message) {
        super(BAD_REQUEST, message);
    }
}

