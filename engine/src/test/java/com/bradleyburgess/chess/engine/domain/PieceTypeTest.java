package com.bradleyburgess.chess.engine.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTypeTest {
    // PAWN
    @Test
    void pawn_code_is_p() {
        PieceType p = PieceType.PAWN;
        assertEquals("P", p.getCode());
    }

    @Test
    void pawn_type_is_pawn() {
        PieceType p = PieceType.PAWN;
        assertEquals("PAWN", p.getType());
    }

    // KNIGHT
    @Test
    void knight_code_is_n() {
        PieceType p = PieceType.KNIGHT;
        assertEquals("N", p.getCode());
    }

    @Test
    void knight_type_is_knight() {
        PieceType p = PieceType.KNIGHT;
        assertEquals("KNIGHT", p.getType());
    }

    // BISHOP
    @Test
    void bishop_code_is_b() {
        PieceType p = PieceType.BISHOP;
        assertEquals("B", p.getCode());
    }

    @Test
    void bishop_type_is_bishop() {
        PieceType p = PieceType.BISHOP;
        assertEquals("BISHOP", p.getType());
    }

    // ROOK
    @Test
    void rook_code_is_r() {
        PieceType p = PieceType.ROOK;
        assertEquals("R", p.getCode());
    }

    @Test
    void rook_type_is_rook() {
        PieceType p = PieceType.ROOK;
        assertEquals("ROOK", p.getType());
    }

    // QUEEN
    @Test
    void queen_code_is_q() {
        PieceType p = PieceType.QUEEN;
        assertEquals("Q", p.getCode());
    }

    @Test
    void queen_type_is_queen() {
        PieceType p = PieceType.QUEEN;
        assertEquals("QUEEN", p.getType());
    }

    // KING
    @Test
    void king_code_is_k() {
        PieceType p = PieceType.KING;
        assertEquals("K", p.getCode());
    }

    @Test
    void king_type_is_king() {
        PieceType p = PieceType.KING;
        assertEquals("KING", p.getType());
    }
}
