package com.bradleyburgess.chess.engine.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {
    @Test
    void piece_is_white_pawn() {
        Piece p = new Piece(PieceType.PAWN, Color.WHITE);
        assertEquals(Color.WHITE, p.getColor());
        assertEquals(PieceType.PAWN, p.getType());
    }

    @Test
    void piece_has_zero_moves() {
        Piece p = new Piece(PieceType.PAWN, Color.WHITE);
        assertEquals(0, p.getMoveCount());
    }

    @Test
    void piece_has_two_move() {
        Piece p = new Piece(PieceType.PAWN, Color.WHITE);
        p.incrementMoveCount();
        p.incrementMoveCount();
        assertEquals(2, p.getMoveCount());
    }
}
