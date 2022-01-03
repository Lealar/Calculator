package com.epam.izh.rd.online.exception;

public class EmptyInputStringException extends Exception {
    public EmptyInputStringException() {
        super();
    }

    public EmptyInputStringException(String message) {
        super(message);
    }

    public EmptyInputStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyInputStringException(Throwable cause) {
        super(cause);
    }
}
