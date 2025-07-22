package com.bradleyburgess.chess.engine.domain;

import com.bradleyburgess.chess.engine.domain.exceptions.InvalidCoordinateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinateTest {
    @Test
    void throws_for_bad_file() {
        assertThrows(InvalidCoordinateException.class, () -> {
            Coordinate c = new Coordinate('j', (byte) 9);
        });
    }

    @Test
    void coordinate_is_e8() {
        Coordinate c = new Coordinate('e', (byte) 8);
        assertEquals(8, c.rank());
        assertEquals('e', c.file());
    }
}
