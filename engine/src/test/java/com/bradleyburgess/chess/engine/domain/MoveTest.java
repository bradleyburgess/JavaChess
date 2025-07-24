package com.bradleyburgess.chess.engine.domain;

import com.bradleyburgess.chess.engine.domain.exceptions.InvalidMoveException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoveTest {
    @Test
    void from_a1_to_b2() {
        Coordinate cFrom = new Coordinate('a', 1);
        Coordinate cTo = new Coordinate('b', 2);
        Move m = new Move(cFrom, cTo);
        assertEquals(m.from(), cFrom);
        assertEquals(m.to(), cTo);
    }

    @Test
    void throws_for_same_from_and_to() {
        assertThrows(InvalidMoveException.class, () -> {
            new Move(new Coordinate('a', 1), new Coordinate('a', 1));
        });
    }
}
