package com.bradleyburgess.chess.engine.domain;

public class Piece {
    private Color color;
    private PieceType type;
    private int moveCount = 0;

    public Piece(PieceType type, Color color) {
        this.type = type;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public PieceType getType() {
        return this.type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void incrementMoveCount() {
        this.moveCount += 1;
    }
}
