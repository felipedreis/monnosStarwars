package com.b2w.starwars.infrastructure.exceptions;

public abstract class BaseException extends RuntimeException {

    public BaseException() {
        super();
    }

    public BaseException(final String message){
        super(message);
    }

    public BaseException(final Throwable cause){
        super(cause);
    }

    public BaseException(final String message, Throwable cause){
        super(message, cause);
    }

    public BaseException(final String format, final Object... params){
        super(String.format(format, params));
    }
}
