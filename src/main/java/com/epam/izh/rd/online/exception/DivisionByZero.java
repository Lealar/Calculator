package com.epam.izh.rd.online.exception;

public class DivisionByZero extends Exception {
    public DivisionByZero() {
        super();
    }

    public DivisionByZero(String message) {
        super(message);
    }

    public DivisionByZero(String message, Throwable cause) {
        super(message, cause);
    }

    public DivisionByZero(Throwable cause) {
        super(cause);
    }
}
