package com.bradleyburgess.chess.engine.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoveTest {
    @Test
    void from_a1_to_b2() {
        Coordinate cFrom = new Coordinate('a', 1);
        Coordinate cTo = new Coordinate('b', 2);
        Square from = new Square(Board.getWhiteOrBlack(cFrom.getRank(), cFrom.getFile()), cFrom);
        Square to = new Square(Board.getWhiteOrBlack(cTo.getRank(), cTo.getFile()), cTo);
        Move m = new Move(from, to);
        assertEquals(from, m.from());
        assertEquals(to, m.to());
    }
}
