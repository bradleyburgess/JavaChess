package com.bradleyburgess.chess.engine.domain;

public enum PieceType {
    PAWN("PAWN", "P"),
    KNIGHT("KNIGHT", "N"),
    BISHOP("BISHOP", "B"),
    ROOK("ROOK", "R"),
    QUEEN("QUEEN", "Q"),
    KING("KING", "K");

    private final String type;
    private final String code;

    PieceType(String type, String code) {
        this.type = type;
        this.code = code;
    }

    public String getType() {
        return this.type;
    }

    public String getCode() {
        return this.code;
    }
}
