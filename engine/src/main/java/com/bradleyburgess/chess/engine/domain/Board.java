package com.bradleyburgess.chess.engine.domain;

import com.bradleyburgess.chess.engine.domain.exceptions.InvalidCoordinateException;
import com.bradleyburgess.chess.engine.domain.validators.CoordinateValidator;

import java.util.ArrayList;
import java.util.List;

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

    public List<Square>[] getRankFrom(Coordinate coordinate) {
        List<Square> rightList = new ArrayList<>();
        List<Square> leftList = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<Square>[] result = (List<Square>[]) new List[]{rightList, leftList};

        char file = coordinate.file();
        while (file < 'h') {
            file += 1;
            Square s = this.getSquare(file, coordinate.rank());
            rightList.add(s);
        }

        file = coordinate.file();
        while (file > 'a') {
            file -= 1;
            Square s = this.getSquare(file, coordinate.rank());
            leftList.add(s);
        }

        return result;
    }

    public List<Square>[] getFileFrom(Coordinate coordinate) {
        List<Square> upList = new ArrayList<>();
        List<Square> downList = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<Square>[] result = (List<Square>[]) new List[]{upList, downList};

        int rank = coordinate.rank();
        while (rank < 8) {
            rank += 1;
            Square s = this.getSquare(coordinate.file(), rank);
            upList.add(s);
        }

        rank = coordinate.rank();
        while (rank > 1) {
            rank -= 1;
            Square s = this.getSquare(coordinate.file(), rank);
            downList.add(s);
        }

        return result;
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
