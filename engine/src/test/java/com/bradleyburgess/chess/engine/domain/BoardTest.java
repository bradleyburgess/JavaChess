package com.bradleyburgess.chess.engine.domain;

import com.bradleyburgess.chess.engine.domain.exceptions.InvalidCoordinateException;
import com.bradleyburgess.chess.engine.domain.exceptions.InvalidMoveException;
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
                Square s = b.getSquare(new Coordinate('a', 1));
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
                Square s = b.getSquare(new Coordinate('c', 6));
                assertEquals('c', s.getFile());
                assertEquals(6, s.getRank());
            } catch (InvalidCoordinateException e) {
                fail("Invalid coordinate given");
            }

        }
    }

    @Nested
    public class MakeMoveTest {
        @Test
        void returns_null_result() {
            Board b = new Board();
            b.getSquare(new Coordinate('a', 1)).occupy(new Piece(PieceType.ROOK, Color.WHITE));
            MoveResult result = b.makeMove(new Move(new Coordinate('a', 1), new Coordinate('a', 2)));
            assertNull(result.capturedPiece());
        }

        @Test
        void invalid_move_if_unoccupied() {
            Board b = new Board();
            Coordinate cFrom = new Coordinate('a', 1);
            Coordinate cTo = new Coordinate('a', 2);
            assertThrows(InvalidMoveException.class, () -> {
                b.makeMove(new Move(cFrom, cTo));
            });
        }

        @Test
        void a1_moves_to_a2() {
            Board b = new Board();
            Piece p = new Piece(PieceType.KING, Color.WHITE);
            Coordinate cFrom = new Coordinate('a', 1);
            Coordinate cTo = new Coordinate('a', 2);
            b.getSquare(cFrom).occupy(p);
            b.makeMove(new Move(cFrom, cTo));
            Piece a2Piece = b.getSquare(cTo).getPiece();
            assertEquals(p, a2Piece);
            assertFalse(b.getSquare(cFrom).isOccupied());
        }

        @Test
        void returns_captured_piece() {
            Board b = new Board();
            Piece pFrom = new Piece(PieceType.KING, Color.WHITE);
            Piece pTo = new Piece(PieceType.PAWN, Color.BLACK);
            Coordinate cFrom = new Coordinate('a', 1);
            Coordinate cTo = new Coordinate('a', 2);
            b.getSquare(cFrom).occupy(pFrom);
            b.getSquare(cTo).occupy(pTo);
            MoveResult result = b.makeMove(new Move(cFrom, cTo));
            Piece a1Piece = b.getSquare(cFrom).getPiece();
            Piece a2Piece = b.getSquare(cTo).getPiece();

            assertEquals(pFrom, b.getSquare(cTo).getPiece());
            assertNull(b.getSquare(cFrom).getPiece());
            assertFalse(b.getSquare(cFrom).isOccupied());
            assertTrue(b.getSquare(cTo).isOccupied());
            assertEquals(pTo, result.capturedPiece());
            assertNull(a1Piece);
            assertEquals(pFrom, a2Piece);
        }
    }

    @Nested
    public class GetRankFromCoordinateTest {
        @Test
        void returns_a_2_item_array() {
            Board b = new Board();
            Coordinate c = new Coordinate('a', 1);
            List<Square>[] result = b.getRankFrom(c);
            assertEquals(2, result.length);
        }

        @Test
        void returns_2_lists_in_array() {
            Board b = new Board();
            Coordinate c = new Coordinate('a', 1);
            List<Square>[] result = b.getRankFrom(c);
            for (int i = 0; i < result.length; i++) {
                assertInstanceOf(List.class, result[i]);
            }
        }

        @Test
        void from_a1_returns_b1_through_h1() {
            Board b = new Board();
            Coordinate c = new Coordinate('a', 1);
            List<Square>[] result = b.getRankFrom(c);
            assertTrue(result[1].isEmpty());
            assertEquals(7, result[0].size());

            assertEquals(new Coordinate('b', 1), result[0].get(0).getCoordinate());
            assertEquals(new Coordinate('c', 1), result[0].get(1).getCoordinate());
            assertEquals(new Coordinate('d', 1), result[0].get(2).getCoordinate());
            assertEquals(new Coordinate('e', 1), result[0].get(3).getCoordinate());
            assertEquals(new Coordinate('f', 1), result[0].get(4).getCoordinate());
            assertEquals(new Coordinate('g', 1), result[0].get(5).getCoordinate());
            assertEquals(new Coordinate('h', 1), result[0].get(6).getCoordinate());
        }

        @Test
        void from_h2_returns_g2_through_a2() {
            Board b = new Board();
            Coordinate c = new Coordinate('h', 2);
            List<Square>[] result = b.getRankFrom(c);
            assertTrue(result[0].isEmpty());
            assertEquals(7, result[1].size());

            assertEquals(new Coordinate('g', 2), result[1].get(0).getCoordinate());
            assertEquals(new Coordinate('f', 2), result[1].get(1).getCoordinate());
            assertEquals(new Coordinate('e', 2), result[1].get(2).getCoordinate());
            assertEquals(new Coordinate('d', 2), result[1].get(3).getCoordinate());
            assertEquals(new Coordinate('c', 2), result[1].get(4).getCoordinate());
            assertEquals(new Coordinate('b', 2), result[1].get(5).getCoordinate());
            assertEquals(new Coordinate('a', 2), result[1].get(6).getCoordinate());
        }

        @Test
        void from_c2_returns_d2_through_h2_and_b2_through_a2() {
            Board b = new Board();
            Coordinate c = new Coordinate('c', 2);
            List<Square>[] result = b.getRankFrom(c);
            assertEquals(5, result[0].size());
            assertEquals(2, result[1].size());

            assertEquals(new Coordinate('d', 2), result[0].get(0).getCoordinate());
            assertEquals(new Coordinate('e', 2), result[0].get(1).getCoordinate());
            assertEquals(new Coordinate('f', 2), result[0].get(2).getCoordinate());
            assertEquals(new Coordinate('g', 2), result[0].get(3).getCoordinate());
            assertEquals(new Coordinate('h', 2), result[0].get(4).getCoordinate());

            assertEquals(new Coordinate('b', 2), result[1].get(0).getCoordinate());
            assertEquals(new Coordinate('a', 2), result[1].get(1).getCoordinate());
        }
    }

    @Nested
    public class GetFileFromCoordinate {
        @Test
        void returns_a_2_item_array() {
            Board b = new Board();
            Coordinate c = new Coordinate('a', 1);
            List<Square>[] result = b.getFileFrom(c);
            assertEquals(2, result.length);
        }

        @Test
        void returns_2_lists_in_array() {
            Board b = new Board();
            Coordinate c = new Coordinate('a', 1);
            List<Square>[] result = b.getFileFrom(c);
            for (int i = 0; i < result.length; i++) {
                assertInstanceOf(List.class, result[i]);
            }
        }

        @Test
        void from_c1_returns_c2_through_c8() {
            Board b = new Board();
            Coordinate c = new Coordinate('c', 1);
            List<Square>[] result = b.getFileFrom(c);
            assertTrue(result[1].isEmpty());
            assertEquals(7, result[0].size());

            assertEquals(new Coordinate('c', 2), result[0].get(0).getCoordinate());
            assertEquals(new Coordinate('c', 3), result[0].get(1).getCoordinate());
            assertEquals(new Coordinate('c', 4), result[0].get(2).getCoordinate());
            assertEquals(new Coordinate('c', 5), result[0].get(3).getCoordinate());
            assertEquals(new Coordinate('c', 6), result[0].get(4).getCoordinate());
            assertEquals(new Coordinate('c', 7), result[0].get(5).getCoordinate());
            assertEquals(new Coordinate('c', 8), result[0].get(6).getCoordinate());
        }

        @Test
        void from_c8_returns_c7_through_c1() {
            Board b = new Board();
            Coordinate c = new Coordinate('c', 8);
            List<Square>[] result = b.getFileFrom(c);
            assertTrue(result[0].isEmpty());
            assertEquals(7, result[1].size());

            assertEquals(new Coordinate('c', 7), result[1].get(0).getCoordinate());
            assertEquals(new Coordinate('c', 6), result[1].get(1).getCoordinate());
            assertEquals(new Coordinate('c', 5), result[1].get(2).getCoordinate());
            assertEquals(new Coordinate('c', 4), result[1].get(3).getCoordinate());
            assertEquals(new Coordinate('c', 3), result[1].get(4).getCoordinate());
            assertEquals(new Coordinate('c', 2), result[1].get(5).getCoordinate());
            assertEquals(new Coordinate('c', 1), result[1].get(6).getCoordinate());
        }

        @Test
        void from_d4_returns_d5_through_d8_and_d3_through_d1() {
            Board b = new Board();
            Coordinate c = new Coordinate('d', 4);
            List<Square>[] result = b.getFileFrom(c);
            assertEquals(4, result[0].size());
            assertEquals(3, result[1].size());

            assertEquals(new Coordinate('d', 5), result[0].get(0).getCoordinate());
            assertEquals(new Coordinate('d', 6), result[0].get(1).getCoordinate());
            assertEquals(new Coordinate('d', 7), result[0].get(2).getCoordinate());
            assertEquals(new Coordinate('d', 8), result[0].get(3).getCoordinate());

            assertEquals(new Coordinate('d', 3), result[1].get(0).getCoordinate());
            assertEquals(new Coordinate('d', 2), result[1].get(1).getCoordinate());
            assertEquals(new Coordinate('d', 1), result[1].get(2).getCoordinate());
        }
    }

    @Nested
    public class GetDiagonalsFromTest {
        @Test
        void returns_a_4_item_array() {
            Board b = new Board();
            Coordinate c = new Coordinate('a', 1);
            List<Square>[] result = b.getDiagonalsFrom(c);
            assertEquals(4, result.length);
        }

        @Test
        void returns_4_lists_in_array() {
            Board b = new Board();
            Coordinate c = new Coordinate('a', 1);
            List<Square>[] result = b.getDiagonalsFrom(c);
            for (int i = 0; i < result.length; i++) {
                assertInstanceOf(List.class, result[i]);
            }
        }

        @Test
        void returns_upRight_diagonal_and_3_empty_lists() {
            Board b = new Board();
            Coordinate c = new Coordinate('a', 1);
            List<Square>[] result = b.getDiagonalsFrom(c);

            assertEquals(7, result[0].size());
            assertTrue(result[1].isEmpty());
            assertTrue(result[2].isEmpty());
            assertTrue(result[3].isEmpty());

            List<Square> upRight = result[0];
            for (int i = 0; i < upRight.size(); i++) {
                assertEquals(new Coordinate((char) ('b' + i), 2 + i), upRight.get(i).getCoordinate());
            }
        }

        @Test
        void returns_4_lists_from_d4() {
            Board b = new Board();
            Coordinate c = new Coordinate('d', 4);
            List<Square>[] result = b.getDiagonalsFrom(c);
            List<Square> upRight = result[0];
            List<Square> downRight = result[1];
            List<Square> downLeft = result[2];
            List<Square> upLeft = result[3];

            assertEquals(4, upRight.size());
            assertEquals(3, downRight.size());
            assertEquals(3, downLeft.size());
            assertEquals(3, upLeft.size());

            for (int i = 0; i < upRight.size(); i++) {
                assertEquals(new Coordinate((char) ('e' + i), 5 + i), upRight.get(i).getCoordinate());
            }

            for (int i = 0; i < downRight.size(); i++) {
                assertEquals(new Coordinate((char) ('e' + i), 3 - i), downRight.get(i).getCoordinate());
            }

            for (int i = 0; i < downLeft.size(); i++) {
                assertEquals(new Coordinate((char) ('c' - i), 3 - i), downLeft.get(i).getCoordinate());
            }

            for (int i = 0; i < upLeft.size(); i++) {
                assertEquals(new Coordinate((char) ('c' - i), 5 + i), upLeft.get(i).getCoordinate());
            }
        }
    }

    @Nested
    public class GetLsFromTest {
        @Test
        void returns_a_list() {
            Board b = new Board();
            Coordinate c = new Coordinate('d', 4);
            List<Square> result = b.getLsFrom(c);
            assertInstanceOf(List.class, result);
        }

        @Test
        void returns_a_list_of_squares() {
            Board b = new Board();
            Coordinate c = new Coordinate('d', 4);
            List<Square> result = b.getLsFrom(c);
            for (Object o : result) {
                assertInstanceOf(Square.class, o);
            }
        }

        @Test
        void returns_8_squares_from_d4() {
            Board b = new Board();
            Coordinate c = new Coordinate('d', 4);
            List<Square> result = b.getLsFrom(c);
            assertEquals(8, result.size());
        }

        @Test
        void from_d4_returns_f5_f3_e2_c2_b3_b5_c6_e6() {
            Board b = new Board();
            Coordinate c = new Coordinate('d', 4);
            List<Square> result = b.getLsFrom(c);

            assertEquals(8, result.size());
            assertEquals(new Coordinate('f', 5), result.get(0).getCoordinate());
            assertEquals(new Coordinate('f', 3), result.get(1).getCoordinate());
            assertEquals(new Coordinate('e', 2), result.get(2).getCoordinate());
            assertEquals(new Coordinate('c', 2), result.get(3).getCoordinate());
            assertEquals(new Coordinate('b', 3), result.get(4).getCoordinate());
            assertEquals(new Coordinate('b', 5), result.get(5).getCoordinate());
            assertEquals(new Coordinate('c', 6), result.get(6).getCoordinate());
            assertEquals(new Coordinate('e', 6), result.get(7).getCoordinate());
        }

        @Test
        void from_a1_returns_c2_b3() {
            Board b = new Board();
            Coordinate c = new Coordinate('a', 1);
            List<Square> result = b.getLsFrom(c);

            assertEquals(2, result.size());
            assertEquals(new Coordinate('c', 2), result.get(0).getCoordinate());
            assertEquals(new Coordinate('b', 3), result.get(1).getCoordinate());
        }

        @Test
        void from_h4_returns_g2_f3_f5_g6() {
            Board b = new Board();
            Coordinate c = new Coordinate('h', 4);
            List<Square> result = b.getLsFrom(c);

            assertEquals(4, result.size());
            assertEquals(new Coordinate('g', 2), result.get(0).getCoordinate());
            assertEquals(new Coordinate('f', 3), result.get(1).getCoordinate());
            assertEquals(new Coordinate('f', 5), result.get(2).getCoordinate());
            assertEquals(new Coordinate('g', 6), result.get(3).getCoordinate());
        }
    }
}
