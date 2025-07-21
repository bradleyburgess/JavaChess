package com.bradleyburgess.chess.engine.domain.validators;

import com.bradleyburgess.chess.engine.domain.exceptions.InvalidCoordinateException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class CoordinateValidatorTest {
    @Nested
    public class RankFileTest {
        @Test
        void does_not_throw_for_valid() {
            try {
                CoordinateValidator.validateRankFile('a', 1);
            } catch (InvalidCoordinateException e) {
                fail(e.getMessage());
            }

        }

        @Test
        void throws_for_bad_file() {
            assertThrows(InvalidCoordinateException.class, () -> {
                CoordinateValidator.validateRankFile('j', 1);
            });
        }

        @Test
        void throws_for_bad_rank() {
            assertThrows(InvalidCoordinateException.class, () -> {
                CoordinateValidator.validateRankFile('a', 9);
            });
        }
    }
}
