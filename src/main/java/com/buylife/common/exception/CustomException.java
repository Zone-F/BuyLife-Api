package com.buylife.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final Integer code;
    private final String message;

    public CustomException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
