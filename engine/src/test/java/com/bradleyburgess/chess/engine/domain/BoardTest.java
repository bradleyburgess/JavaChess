package com.bradleyburgess.chess.engine.domain;

import com.bradleyburgess.chess.engine.domain.exceptions.InvalidCoordinateException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Nested
    public class MakeSquaresTest {
        @Test
        void a1_is_black() {
            Square[][] squares = Board.makeSquares();
            Square s = squares['a' - 'a'][1 - 1];
            assertEquals(Color.BLACK, s.getColor());
            assertEquals('a', s.getFile());
            assertEquals(1, s.getRank());
        }

        @Test
        void e6_is_white() {
            Square[][] squares = Board.makeSquares();
            Square s = squares[6 - 1]['e' - 'a'];
            assertEquals(Color.WHITE, s.getColor());
            assertEquals('e', s.getFile());
            assertEquals(6, s.getRank());
        }
    }

    @Nested
    public class GetWhiteOrBlackTest {
        @Test
        void a1_is_black() {
            Color c = Board.getWhiteOrBlack('a' - 'a', 1 - 1);
            assertEquals(Color.BLACK, c);
        }

        @Test
        void a2_is_white() {
            Color c = Board.getWhiteOrBlack('a' - 'a', 2 - 1);
            assertEquals(Color.WHITE, c);
        }

        @Test
        void b5_is_white() {
            Color c = Board.getWhiteOrBlack('b' - 'a', 5 - 1);
            assertEquals(Color.WHITE, c);
        }

        @Test
        void e7_is_black() {
            Color c = Board.getWhiteOrBlack('e' - 'a', 7 - 1);
            assertEquals(Color.BLACK, c);
        }

        @Test
        void h4_is_black() {
            Color c = Board.getWhiteOrBlack('h' - 'a', 4 - 1);
            assertEquals(Color.BLACK, c);
        }
    }

    @Nested
    public class GetSquareTest {
        @Test
        void has_a1_coordinate() {
            Board b = new Board();
            try {
                Square s = b.getSquare("a1");
                assertEquals('a', s.getFile());
                assertEquals(1, s.getRank());
                assertEquals(Color.BLACK, s.getColor());
            } catch (InvalidCoordinateException e) {
                fail("Invalid coordinate given");
            }

        }

        @Test
        void has_a1_rank_file() {
            Board b = new Board();
            try {
                Square s = b.getSquare('a', 1);
                assertEquals('a', s.getFile());
                assertEquals(1, s.getRank());
                assertEquals(Color.BLACK, s.getColor());
            } catch (InvalidCoordinateException e) {
                fail("Invalid coordinate given");
            }
        }

        @Test
        void has_c6_coordinate() {
            Board b = new Board();
            try {
                Square s = b.getSquare("c6");
                assertEquals('c', s.getFile());
                assertEquals(6, s.getRank());
            } catch (InvalidCoordinateException e) {
                fail("Invalid coordinate given");
            }

        }

        @Test
        void has_c6_rank_file() {
            Board b = new Board();
            try {
                Square s = b.getSquare('c', 6);
                assertEquals('c', s.getFile());
                assertEquals(6, s.getRank());
            } catch (InvalidCoordinateException e) {
                fail("Invalid coordinate given");
            }
        }

        @Test
        void throws_error_for_bad_file() {
            Board b = new Board();
            assertThrows(InvalidCoordinateException.class, () -> {
                b.getSquare("j8");
            });
        }

        @Test
        void throws_error_for_bad_rank() {
            Board b = new Board();
            assertThrows(InvalidCoordinateException.class, () -> {
                b.getSquare("a0");
            });
        }

        @Test
        void accepts_uppercase() {
            Board b = new Board();
            try {
                Square s = b.getSquare('A', 1);
                assertEquals('a', s.getFile());
            } catch (InvalidCoordinateException e) {
                fail("Invalid coordinate given");
            }
        }

        @Test
        void accepts_uppercase_coordinate() {
            Board b = new Board();
            try {
                Square s = b.getSquare("A1");
                assertEquals('a', s.getFile());
            } catch (InvalidCoordinateException e) {
                fail("Invalid coordinate given");
            }
        }
    }

    @Nested
    public class GetRankFromCoordinateTest {
        //        @Disabled
        @Test
        void returns_b1_through_h1() {
            Board b = new Board();
            Coordinate c = new Coordinate('a', (byte) 1);
            List<Square>[] result = b.getRankFrom(c);
            assertTrue(result[1].isEmpty());
            assertEquals(7, result[0].size());

            assertEquals(new Coordinate('b', (byte) 1), result[0].get(0).getCoordinate());
            assertEquals(new Coordinate('c', (byte) 1), result[0].get(1).getCoordinate());
            assertEquals(new Coordinate('d', (byte) 1), result[0].get(2).getCoordinate());
            assertEquals(new Coordinate('e', (byte) 1), result[0].get(3).getCoordinate());
            assertEquals(new Coordinate('f', (byte) 1), result[0].get(4).getCoordinate());
            assertEquals(new Coordinate('g', (byte) 1), result[0].get(5).getCoordinate());
            assertEquals(new Coordinate('h', (byte) 1), result[0].get(6).getCoordinate());
        }

        @Test
        void returns_d2_through_h2_and_b2_through_a2() {
            Board b = new Board();
            Coordinate c = new Coordinate('c', (byte) 2);
            List<Square>[] result = b.getRankFrom(c);
            assertEquals(5, result[0].size());
            assertEquals(2, result[1].size());

            assertEquals(new Coordinate('d', (byte) 2), result[0].get(0).getCoordinate());
            assertEquals(new Coordinate('e', (byte) 2), result[0].get(1).getCoordinate());
            assertEquals(new Coordinate('f', (byte) 2), result[0].get(2).getCoordinate());
            assertEquals(new Coordinate('g', (byte) 2), result[0].get(3).getCoordinate());
            assertEquals(new Coordinate('h', (byte) 2), result[0].get(4).getCoordinate());

            assertEquals(new Coordinate('b', (byte) 2), result[1].get(0).getCoordinate());
            assertEquals(new Coordinate('a', (byte) 2), result[1].get(1).getCoordinate());
        }
    }
}
