package com.bradleyburgess.chess.engine.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {
    @Test
    void square_is_white() {
        Square s = new Square(Color.WHITE, new Coordinate('a', 1));
        assertEquals(Color.WHITE, s.getColor());
    }

    @Test
    void square_is_unoccupied() {
        Square s = new Square(Color.WHITE, new Coordinate('a', 1));
        assertFalse(s.isOccupied());
    }

    @Test
    void square_is_occupied() {
        Square s = new Square(Color.WHITE, new Coordinate('a', 1), new Piece(PieceType.PAWN, Color.WHITE));
        assertTrue(s.isOccupied());
    }

    @Test
    void square_occupy_adds_piece() {
        Square s = new Square(Color.WHITE, new Coordinate('a', 1));
        s.occupy(new Piece(PieceType.PAWN, Color.WHITE));
        assertTrue(s.isOccupied());
    }

    @Test
    void square_vacate_removes_piece() {
        Square s = new Square(Color.WHITE, new Coordinate('a', 1), new Piece(PieceType.BISHOP, Color.BLACK));
        Piece p = s.vacate();
        assertFalse(s.isOccupied());
        assertEquals(PieceType.BISHOP, p.getType());
        assertEquals(Color.BLACK, p.getColor());
    }

    @Test
    void rank_is_2() {
        Square s = new Square(Color.WHITE, new Coordinate('a', 2));
        assertEquals(2, s.getRank());
    }

    @Test
    void file_is_g() {
        Square s = new Square(Color.WHITE, new Coordinate('g', 2));
        assertEquals('g', s.getFile());
    }
}
