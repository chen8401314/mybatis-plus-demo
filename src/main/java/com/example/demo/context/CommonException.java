package com.example.demo.context;

public class CommonException extends RuntimeException {
    private static final long serialVersionUID = 3510775765637913007L;
    public CommonException(String message) {
        super(message);
    }

    public CommonException() {
    }
}