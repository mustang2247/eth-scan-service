package com.open.coinnews.basic.exception;

public class RequestLimitException extends Exception {
    public RequestLimitException() {
        super("HTTP request exceeded the limit");
//        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String message) {
        super(message);
    }
}
