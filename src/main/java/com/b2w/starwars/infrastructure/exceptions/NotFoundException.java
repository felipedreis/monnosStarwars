package com.b2w.starwars.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {

    private static String DEFAULT_MESSAGE = "Resource Not Found.";

    public NotFoundException(String message, Object[] params){
        super(message, params);
    }

    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(){
        super(DEFAULT_MESSAGE);
    }
}
