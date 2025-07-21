package com.bradleyburgess.chess.engine.domain;

import com.bradleyburgess.chess.engine.domain.exceptions.InvalidCoordinateException;
import com.bradleyburgess.chess.engine.domain.validators.CoordinateValidator;

public class Board {
    private final Square[][] squares;

    public Board() {
        this.squares = makeSquares();
    }

    public Square getSquare(char file, int rank) throws InvalidCoordinateException {
        file = Character.toLowerCase(file);
        CoordinateValidator.validateRankFile(file, rank);
        return squares[rank - 1][file - 'a'];
    }

    public Square getSquare(String coordinate) throws InvalidCoordinateException {
        CoordinateValidator.validateCoordinate(coordinate);
        char file = coordinate.charAt(0);
        int rank = (int) (coordinate.charAt(1)) - 48;
        return this.getSquare(file, rank);
    }

    /**
     * Calculate whether a square should be black or white, based on
     * zero-indexed rank and file
     *
     * @param rank
     * @param file
     * @return The square's color (black or white)
     */
    static Color getWhiteOrBlack(int rank, int file) {
        if ((rank + file) % 2 == 1) return Color.WHITE;
        return Color.BLACK;
    }

    static Square[][] makeSquares() {
        Square[][] squares = new Square[8][8];
        // start with rank 8 (like FEN)
        for (int rank = 7; rank >= 0; rank--) {
            // start with file a
            for (int file = 0; file <= 7; file++) {
                Color c = getWhiteOrBlack(rank, file);
                Square s = new Square(c, (char) ('a' + file), 1 + rank);
                squares[rank][file] = s;
            }
        }
        return squares;
    }
}
