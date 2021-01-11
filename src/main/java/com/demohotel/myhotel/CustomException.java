package com.demohotel.myhotel;

public class CustomException extends RuntimeException {
    String message;

    public CustomException(String message) {
        super(message);
    }
}
