package com.bradleyburgess.chess.engine.domain;

import com.bradleyburgess.chess.engine.domain.exceptions.InvalidCoordinateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Nested
    public class MakeSquaresTest {
        @Test
        void a1_is_black() {
            Square[][] squares = Board.makeSquares();
            Square s = squares['a' - 'a'][1 - 1];
            Assertions.assertEquals(Color.BLACK, s.getColor());
            Assertions.assertEquals('a', s.getFile());
            Assertions.assertEquals(1, s.getRank());
        }

        @Test
        void e6_is_white() {
            Square[][] squares = Board.makeSquares();
            Square s = squares[6 - 1]['e' - 'a'];
            Assertions.assertEquals(Color.WHITE, s.getColor());
            Assertions.assertEquals('e', s.getFile());
            Assertions.assertEquals(6, s.getRank());
        }
    }

    @Nested
    public class GetWhiteOrBlackTest {
        @Test
        void a1_is_black() {
            Color c = Board.getWhiteOrBlack('a' - 'a', 1 - 1);
            Assertions.assertEquals(Color.BLACK, c);
        }

        @Test
        void a2_is_white() {
            Color c = Board.getWhiteOrBlack('a' - 'a', 2 - 1);
            Assertions.assertEquals(Color.WHITE, c);
        }

        @Test
        void b5_is_white() {
            Color c = Board.getWhiteOrBlack('b' - 'a', 5 - 1);
            Assertions.assertEquals(Color.WHITE, c);
        }

        @Test
        void e7_is_black() {
            Color c = Board.getWhiteOrBlack('e' - 'a', 7 - 1);
            Assertions.assertEquals(Color.BLACK, c);
        }

        @Test
        void h4_is_black() {
            Color c = Board.getWhiteOrBlack('h' - 'a', 4 - 1);
            Assertions.assertEquals(Color.BLACK, c);
        }
    }

    @Nested
    public class GetSquareTest {
        @Test
        void has_a1_coordinate() {
            Board b = new Board();
            try {
                Square s = b.getSquare("a1");
                Assertions.assertEquals('a', s.getFile());
                Assertions.assertEquals(1, s.getRank());
                Assertions.assertEquals(Color.BLACK, s.getColor());
            } catch (InvalidCoordinateException e) {
                Assertions.fail("Invalid coordinate given");
            }

        }

        @Test
        void has_a1_rank_file() {
            Board b = new Board();
            try {
                Square s = b.getSquare('a', 1);
                Assertions.assertEquals('a', s.getFile());
                Assertions.assertEquals(1, s.getRank());
                Assertions.assertEquals(Color.BLACK, s.getColor());
            } catch (InvalidCoordinateException e) {
                Assertions.fail("Invalid coordinate given");
            }
        }

        @Test
        void has_c6_coordinate() {
            Board b = new Board();
            try {
                Square s = b.getSquare("c6");
                Assertions.assertEquals('c', s.getFile());
                Assertions.assertEquals(6, s.getRank());
            } catch (InvalidCoordinateException e) {
                Assertions.fail("Invalid coordinate given");
            }

        }

        @Test
        void has_c6_rank_file() {
            Board b = new Board();
            try {
                Square s = b.getSquare('c', 6);
                Assertions.assertEquals('c', s.getFile());
                Assertions.assertEquals(6, s.getRank());
            } catch (InvalidCoordinateException e) {
                Assertions.fail("Invalid coordinate given");
            }
        }

        @Test
        void throws_error_for_bad_file() {
            Board b = new Board();
            Assertions.assertThrows(InvalidCoordinateException.class, () -> {
                b.getSquare("j8");
            });
        }

        @Test
        void throws_error_for_bad_rank() {
            Board b = new Board();
            Assertions.assertThrows(InvalidCoordinateException.class, () -> {
                b.getSquare("a0");
            });
        }

        @Test
        void accepts_uppercase() {
            Board b = new Board();
            try {
                Square s = b.getSquare('A', 1);
                Assertions.assertEquals('a', s.getFile());
            } catch (InvalidCoordinateException e) {
                Assertions.fail("Invalid coordinate given");
            }
        }

        @Test
        void accepts_uppercase_coordinate() {
            Board b = new Board();
            try {
                Square s = b.getSquare("A1");
                Assertions.assertEquals('a', s.getFile());
            } catch (InvalidCoordinateException e) {
                Assertions.fail("Invalid coordinate given");
            }
        }
    }
}
