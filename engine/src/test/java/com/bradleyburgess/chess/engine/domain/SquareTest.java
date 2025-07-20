package com.bradleyburgess.chess.engine.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTest {
    @Test
    void square_is_white() {
        Square s = new Square(Color.WHITE, 'a', 1);
        Assertions.assertEquals(Color.WHITE, s.getColor());
    }

    @Test
    void square_is_unoccupied() {
        Square s = new Square(Color.WHITE, 'a', 1);
        Assertions.assertFalse(s.isOccupied());
    }

    @Test
    void square_is_occupied() {
        Square s = new Square(Color.WHITE, 'a', 1, new Piece(PieceType.PAWN, Color.WHITE));
        Assertions.assertTrue(s.isOccupied());
    }

    @Test
    void square_occupy_adds_piece() {
        Square s = new Square(Color.WHITE, 'a', 1);
        s.occupy(new Piece(PieceType.PAWN, Color.WHITE));
        Assertions.assertTrue(s.isOccupied());
    }

    @Test
    void square_vacate_removes_piece() {
        Square s = new Square(Color.WHITE, 'a', 1, new Piece(PieceType.BISHOP, Color.BLACK));
        Piece p = s.vacate();
        Assertions.assertFalse(s.isOccupied());
        Assertions.assertEquals(PieceType.BISHOP, p.getType());
        Assertions.assertEquals(Color.BLACK, p.getColor());
    }

    @Test
    void rank_is_2() {
        Square s = new Square(Color.WHITE, 'a', 2);
        Assertions.assertEquals(2, s.getRank());
    }

    @Test
    void file_is_g() {
        Square s = new Square(Color.WHITE, 'g', 2);
        Assertions.assertEquals('g', s.getFile());
    }
}
