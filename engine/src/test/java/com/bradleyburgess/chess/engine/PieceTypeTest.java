package com.bradleyburgess.chess.engine;

import com.bradleyburgess.chess.engine.domain.PieceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PieceTypeTest {
    // PAWN
    @Test
    void pawn_code_is_p() {
        PieceType p = PieceType.PAWN;
        Assertions.assertEquals("P", p.getCode());
    }

    @Test
    void pawn_type_is_pawn() {
        PieceType p = PieceType.PAWN;
        Assertions.assertEquals("PAWN", p.getType());
    }

    // KNIGHT
    @Test
    void knight_code_is_n() {
        PieceType p = PieceType.KNIGHT;
        Assertions.assertEquals("N", p.getCode());
    }

    @Test
    void knight_type_is_knight() {
        PieceType p = PieceType.KNIGHT;
        Assertions.assertEquals("KNIGHT", p.getType());
    }

    // BISHOP
    @Test
    void bishop_code_is_b() {
        PieceType p = PieceType.BISHOP;
        Assertions.assertEquals("B", p.getCode());
    }

    @Test
    void bishop_type_is_bishop() {
        PieceType p = PieceType.BISHOP;
        Assertions.assertEquals("BISHOP", p.getType());
    }

    // ROOK
    @Test
    void rook_code_is_r() {
        PieceType p = PieceType.ROOK;
        Assertions.assertEquals("R", p.getCode());
    }

    @Test
    void rook_type_is_rook() {
        PieceType p = PieceType.ROOK;
        Assertions.assertEquals("ROOK", p.getType());
    }

    // QUEEN
    @Test
    void queen_code_is_q() {
        PieceType p = PieceType.QUEEN;
        Assertions.assertEquals("Q", p.getCode());
    }

    @Test
    void queen_type_is_queen() {
        PieceType p = PieceType.QUEEN;
        Assertions.assertEquals("QUEEN", p.getType());
    }

    // KING
    @Test
    void king_code_is_k() {
        PieceType p = PieceType.KING;
        Assertions.assertEquals("K", p.getCode());
    }

    @Test
    void king_type_is_king() {
        PieceType p = PieceType.KING;
        Assertions.assertEquals("KING", p.getType());
    }
}
