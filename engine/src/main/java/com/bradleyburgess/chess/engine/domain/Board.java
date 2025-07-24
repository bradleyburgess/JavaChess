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

    /**
     * Retrieve a square from the board with the given file and rank
     *
     * @param file
     * @param rank
     * @return The indicated square
     * @throws InvalidCoordinateException
     */
    public Square getSquare(char file, int rank) throws InvalidCoordinateException {
        file = Character.toLowerCase(file);
        CoordinateValidator.validateFileRank(file, rank);
        return squares[rank - 1][file - 'a'];
    }

    /**
     * Retrieve a square from the board with the given coordinate
     *
     * @param coordinate
     * @return The indicated square
     * @throws InvalidCoordinateException
     */
    public Square getSquare(String coordinate) throws InvalidCoordinateException {
        CoordinateValidator.validateCoordinate(coordinate);
        char file = coordinate.charAt(0);
        int rank = (int) (coordinate.charAt(1)) - 48;
        return this.getSquare(file, rank);
    }

    /**
     * Retrieve the rank from a given square coordinate as two lists outward from the square.
     * Lists are in the order: (1) rightward, (2) leftward
     *
     * @param coordinate
     * @return A two-item array of Square Lists
     */
    public List<Square>[] getRankFrom(Coordinate coordinate) {
        List<Square> rightList = new ArrayList<>();
        List<Square> leftList = new ArrayList<>();
        @SuppressWarnings("unchecked") List<Square>[] result = (List<Square>[]) new List[]{rightList, leftList};

        char file = coordinate.getFile();
        while (file < 'h') {
            file += 1;
            Square s = this.getSquare(file, coordinate.getRank());
            rightList.add(s);
        }

        file = coordinate.getFile();
        while (file > 'a') {
            file -= 1;
            Square s = this.getSquare(file, coordinate.getRank());
            leftList.add(s);
        }

        return result;
    }

    /**
     * Retrieve the file from the given square coordinate as two lists outward from the square.
     * Lists are in the order: (1) upward, (2) downward.
     *
     * @param coordinate
     * @return A two-item array of Square Lists
     */
    public List<Square>[] getFileFrom(Coordinate coordinate) {
        List<Square> upList = new ArrayList<>();
        List<Square> downList = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<Square>[] result = (List<Square>[]) new List[]{upList, downList};

        int rank = coordinate.getRank();
        while (rank < 8) {
            rank += 1;
            Square s = this.getSquare(coordinate.getFile(), rank);
            upList.add(s);
        }

        rank = coordinate.getRank();
        while (rank > 1) {
            rank -= 1;
            Square s = this.getSquare(coordinate.getFile(), rank);
            downList.add(s);
        }

        return result;
    }

    /**
     * Retrieve the diagonals from the given square coordinate as four lists outward from the square.
     * Lists are in clockwise order, beginning with up and to the right.
     *
     * @param coordinate
     * @return A four-item array of Square Lists
     */
    public List<Square>[] getDiagonalsFrom(Coordinate coordinate) {
        List<Square> upRightList = new ArrayList<>();
        List<Square> downRightList = new ArrayList<>();
        List<Square> downLeftList = new ArrayList<>();
        List<Square> upLeftList = new ArrayList<>();
        @SuppressWarnings("unchecked") List<Square>[] result = (List<Square>[]) new List[]{upRightList, downRightList, downLeftList, upLeftList};

        char file = coordinate.getFile();
        int rank = coordinate.getRank();
        while (file < 'h' && rank < 8) {
            file += 1;
            rank += 1;
            Square s = this.getSquare(file, rank);
            upRightList.add(s);
        }

        file = coordinate.getFile();
        rank = coordinate.getRank();
        while (file < 'h' && rank > 1) {
            file += 1;
            rank -= 1;
            Square s = this.getSquare(file, rank);
            downRightList.add(s);
        }

        file = coordinate.getFile();
        rank = coordinate.getRank();
        while (file > 'a' && rank > 1) {
            file -= 1;
            rank -= 1;
            Square s = this.getSquare(file, rank);
            downLeftList.add(s);
        }

        file = coordinate.getFile();
        rank = coordinate.getRank();
        while (file > 'a' && rank < 8) {
            file -= 1;
            rank += 1;
            Square s = this.getSquare(file, rank);
            upLeftList.add(s);
        }

        return result;
    }

    /**
     * Retrieve the list of L squares from the given square coordinate.
     * The list is in clockwise order, beginning with the square two spaces right, one space up.
     *
     * @param coordinate
     * @return The list of squares
     */
    public List<Square> getLsFrom(Coordinate coordinate) {
        char file = coordinate.getFile();
        int rank = coordinate.getRank();
        List<Square> result = new ArrayList<>();
        if ((char) (file + 2) <= 'h') {
            if (rank + 1 <= 8) {
                result.add(this.getSquare((char) (file + 2), rank + 1));
            }
            if (rank - 1 >= 1) {
                result.add(this.getSquare((char) (file + 2), rank - 1));
            }
        }
        if (rank - 2 >= 1) {
            if ((char) (file + 1) <= 'h') {
                result.add(this.getSquare((char) (file + 1), rank - 2));
            }
            if ((char) (file - 1) >= 'a') {
                result.add(this.getSquare((char) (file - 1), rank - 2));
            }
        }
        if ((char) (file - 2) >= 'a') {
            if (rank - 1 >= 1) {
                result.add(this.getSquare((char) (file - 2), rank - 1));
            }
            if (rank + 1 <= 8) {
                result.add(this.getSquare((char) (file - 2), rank + 1));
            }
        }
        if (rank + 2 <= 8) {
            if ((char) (file - 1) >= 'a') {
                result.add(this.getSquare((char) (file - 1), rank + 2));
            }
            if ((char) (file + 1) <= 'h') {
                result.add(this.getSquare((char) (file + 1), rank + 2));
            }
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
