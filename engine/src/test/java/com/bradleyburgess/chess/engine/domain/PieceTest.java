package com.bradleyburgess.chess.engine.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {

    @Test
    void piece_is_piece() {
        Piece piece = new Piece();
        assertEquals(Piece.class, piece.getClass());
    }
}
