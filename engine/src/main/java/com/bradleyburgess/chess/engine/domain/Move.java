package com.bradleyburgess.chess.engine.domain;


import com.bradleyburgess.chess.engine.domain.exceptions.InvalidMoveException;

public record Move(Coordinate from, Coordinate to) {
    public Move {
        if (from.equals(to)) {
            throw new InvalidMoveException("TO coordinate cannot be same as FROM coordinate");
        }
    }
}
