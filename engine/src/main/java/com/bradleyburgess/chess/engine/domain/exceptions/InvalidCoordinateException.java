package com.bradleyburgess.chess.engine.domain.exceptions;

public class InvalidCoordinateException extends Exception {
    public InvalidCoordinateException(String message) {
        super(message);
    }
}
