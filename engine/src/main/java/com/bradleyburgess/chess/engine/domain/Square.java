package com.bradleyburgess.chess.engine.domain;

import com.bradleyburgess.chess.engine.domain.exceptions.InvalidCoordinateException;

public class Square {
    private final Color color;
    private final Coordinate coordinate;
    private Piece occupiedBy = null;

    public Square(Color color, char file, int rank) throws InvalidCoordinateException {
        this.color = color;
        this.coordinate = new Coordinate(file, (byte) rank);
    }

    public Square(Color color, char file, int rank, Piece occupiedBy) throws InvalidCoordinateException {
        this.color = color;
        this.coordinate = new Coordinate(file, (byte) rank);
        this.occupiedBy = occupiedBy;
    }

    public Square(Color color, Coordinate coordinate) {
        this.color = color;
        this.coordinate = coordinate;
    }

    public Square(Color color, Coordinate coordinate, Piece occupiedBy) {
        this.color = color;
        this.coordinate = coordinate;
        this.occupiedBy = occupiedBy;
    }

    public Color getColor() {
        return this.color;
    }

    public char getFile() {
        return this.coordinate.file();
    }

    public int getRank() {
        return this.coordinate.rank();
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
