package com.epam.izh.rd.online.exception;

public class WrongInputStringExpression extends Exception {
    public WrongInputStringExpression() {
        super();
    }

    public WrongInputStringExpression(String message) {
        super(message);
    }

    public WrongInputStringExpression(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongInputStringExpression(Throwable cause) {
        super(cause);
    }
}
