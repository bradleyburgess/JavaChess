package com.bradleyburgess.chess.engine.domain;

import com.bradleyburgess.chess.engine.domain.exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Square[][] squares;

    public Board() {
        this.squares = makeSquares();
    }

    /**
     * Retrieve a square from the board with the given coordinate
     *
     * @param coordinate
     * @return The indicated square
     */
    public Square getSquare(Coordinate coordinate) {
        return squares[coordinate.getRank() - 1][coordinate.getFile() - 'a'];
    }

    public MoveResult makeMove(Move move) throws InvalidMoveException {
        Square from = this.getSquare(move.from());
        Square to = this.getSquare(move.to());
        if (!from.isOccupied()) {
            throw new InvalidMoveException("FROM Square is not occupied!");
        }
        Piece capturedPiece = to.getPiece();
        MoveResult moveResult = new MoveResult(capturedPiece);
        Piece p = from.vacate();
        to.occupy(p);
        return moveResult;
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
        int rank = coordinate.getRank();
        while (file < 'h') {
            file += 1;
            Square s = this.getSquare(new Coordinate(file, rank));
            rightList.add(s);
        }

        file = coordinate.getFile();
        while (file > 'a') {
            file -= 1;
            Square s = this.getSquare(new Coordinate(file, rank));
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
        @SuppressWarnings("unchecked") //
        List<Square>[] result = (List<Square>[]) new List[]{upList, downList};

        int rank = coordinate.getRank();
        char file = coordinate.getFile();
        while (rank < 8) {
            rank += 1;
            Square s = this.getSquare(new Coordinate(file, rank));
            upList.add(s);
        }

        rank = coordinate.getRank();
        while (rank > 1) {
            rank -= 1;
            Square s = this.getSquare(new Coordinate(file, rank));
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
        @SuppressWarnings("unchecked") //
        List<Square>[] result = (List<Square>[]) new List[]{upRightList, downRightList, downLeftList, upLeftList};

        char file = coordinate.getFile();
        int rank = coordinate.getRank();
        while (file < 'h' && rank < 8) {
            file += 1;
            rank += 1;
            Square s = this.getSquare(new Coordinate(file, rank));
            upRightList.add(s);
        }

        file = coordinate.getFile();
        rank = coordinate.getRank();
        while (file < 'h' && rank > 1) {
            file += 1;
            rank -= 1;
            Square s = this.getSquare(new Coordinate(file, rank));
            downRightList.add(s);
        }

        file = coordinate.getFile();
        rank = coordinate.getRank();
        while (file > 'a' && rank > 1) {
            file -= 1;
            rank -= 1;
            Square s = this.getSquare(new Coordinate(file, rank));
            downLeftList.add(s);
        }

        file = coordinate.getFile();
        rank = coordinate.getRank();
        while (file > 'a' && rank < 8) {
            file -= 1;
            rank += 1;
            Square s = this.getSquare(new Coordinate(file, rank));
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
                result.add(this.getSquare(new Coordinate((char) (file + 2), rank + 1)));
            }
            if (rank - 1 >= 1) {
                result.add(this.getSquare(new Coordinate((char) (file + 2), rank - 1)));
            }
        }
        if (rank - 2 >= 1) {
            if ((char) (file + 1) <= 'h') {
                result.add(this.getSquare(new Coordinate((char) (file + 1), rank - 2)));
            }
            if ((char) (file - 1) >= 'a') {
                result.add(this.getSquare(new Coordinate((char) (file - 1), rank - 2)));
            }
        }
        if ((char) (file - 2) >= 'a') {
            if (rank - 1 >= 1) {
                result.add(this.getSquare(new Coordinate((char) (file - 2), rank - 1)));
            }
            if (rank + 1 <= 8) {
                result.add(this.getSquare(new Coordinate((char) (file - 2), rank + 1)));
            }
        }
        if (rank + 2 <= 8) {
            if ((char) (file - 1) >= 'a') {
                result.add(this.getSquare(new Coordinate((char) (file - 1), rank + 2)));
            }
            if ((char) (file + 1) <= 'h') {
                result.add(this.getSquare(new Coordinate((char) (file + 1), rank + 2)));
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
                Color color = getWhiteOrBlack(rank, file);
                Coordinate coordinate = new Coordinate((char) ('a' + file), 1 + rank);
                Square s = new Square(color, coordinate);
                squares[rank][file] = s;
            }
        }
        return squares;
    }
}
