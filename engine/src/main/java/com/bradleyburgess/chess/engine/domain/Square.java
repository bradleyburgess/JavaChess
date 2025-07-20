package com.bradleyburgess.chess.engine.domain;

public class Square {
    private final Color color;
    private final char file;
    private final byte rank;
    private Piece occupiedBy = null;

    public Square(Color color, char file, int rank) {
        this.color = color;
        this.file = file;
        this.rank = (byte) rank;
    }

    public Square(Color color, char file, int rank, Piece occupiedBy) {
        this.color = color;
        this.file = file;
        this.rank = (byte) rank;
        this.occupiedBy = occupiedBy;
    }

    public Color getColor() {
        return this.color;
    }

    public char getFile() {
        return this.file;
    }

    public byte getRank() {
        return this.rank;
    }

    public boolean isOccupied() {
        return this.occupiedBy != null;
    }

    public void occupy(Piece piece) {
        this.occupiedBy = piece;
    }

    public Piece vacate() {
        Piece p = this.occupiedBy;
        this.occupiedBy = null;
        return p;
    }
}
