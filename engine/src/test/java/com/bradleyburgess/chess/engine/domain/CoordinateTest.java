package com.bradleyburgess.chess.engine.domain;

import com.bradleyburgess.chess.engine.domain.exceptions.InvalidCoordinateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinateTest {
    @Test
    void throws_for_bad_getFile() {
        assertThrows(InvalidCoordinateException.class, () -> {
            Coordinate c = new Coordinate('j', 9);
        });
    }

    @Test
    void coordinate_is_e8() {
        Coordinate c = new Coordinate('e', 8);
        assertEquals(8, c.getRank());
        assertEquals('e', c.getFile());
    }

    @Test
    void coordinate_is_a1() {
        Coordinate c = new Coordinate('a', 1);
        assertEquals(1, c.getRank());
        assertEquals('a', c.getFile());
    }

    @Test
    void coordinate_is_h8() {
        Coordinate c = new Coordinate('h', 8);
        assertEquals(8, c.getRank());
        assertEquals('h', c.getFile());
    }
}
