package com.bradleyburgess.chess.engine.domain;

import com.bradleyburgess.chess.engine.domain.validators.CoordinateValidator;

public record Coordinate(char file, byte rank) {
    public Coordinate {
        CoordinateValidator.validateRankFile(file, rank);
    }
}
