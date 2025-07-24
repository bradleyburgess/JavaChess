package com.bradleyburgess.chess.engine.domain.validators;

import com.bradleyburgess.chess.engine.domain.exceptions.InvalidCoordinateException;

public class CoordinateValidator {
    public static void validateFileRank(char file, int rank) throws InvalidCoordinateException {
        if (file < 'a' || file > 'h')
            throw new InvalidCoordinateException("File must be between 'a' to 'h'");
        if (rank < 1 || rank > 8)
            throw new InvalidCoordinateException("Rank must be between 1 and 8");
    }

    public static void validateCoordinate(String coordinate) throws InvalidCoordinateException {
        if (coordinate.isBlank() || coordinate.length() != 2)
            throw new InvalidCoordinateException("Coordinate must be two characters");

    }
}
