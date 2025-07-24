package com.bradleyburgess.chess.engine.domain;

import com.bradleyburgess.chess.engine.domain.validators.CoordinateValidator;

import java.util.Objects;

public class Coordinate {
    private final char file;
    private final byte rank;

    public Coordinate(char file, int rank) {
        CoordinateValidator.validateFileRank(file, rank);
        this.file = Character.toLowerCase(file);
        this.rank = (byte) rank;
    }

    public char getFile() {
        return this.file;
    }

    public int getRank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return "" + this.file + this.rank;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return this.file == that.getFile() && this.rank == that.getRank();
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
