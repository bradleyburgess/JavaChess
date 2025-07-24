package com.bradleyburgess.chess.engine.domain.exceptions;

public class InvalidCoordinateException extends IllegalArgumentException {
    public InvalidCoordinateException(String message) {
        super(message);
    }
}
