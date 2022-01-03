package com.epam.izh.rd.online.exception;

public class MathematicSymbolException extends Exception {
    public MathematicSymbolException() {
        super();
    }

    public MathematicSymbolException(String message) {
        super(message);
    }

    public MathematicSymbolException(String message, Throwable cause) {
        super(message, cause);
    }

    public MathematicSymbolException(Throwable cause) {
        super(cause);
    }
}
