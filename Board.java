/**
 * Represents the Tic-Tac-Toe game board.
 * <p>
 * The Board class maintains the current state of the game grid,
 * including its size and the marks placed on each cell.
 * It provides methods for placing marks, retrieving marks, and checking board size.
 * </p>
 *
 * <p>Indexing starts at 0 for both rows and columns.</p>
 *
 * @author Naama Even-Oz
 * @see Mark
 */
public class Board {

    /** Default size for a new board (used in the no-arg constructor). */
    private static final int DEFAULT_SIZE = 3;

    /** The smallest valid index on the board. */
    private static final int MIN_INDEX = 0;

    private final int size;
    private final Mark[][] grid;

    /**
     * Default constructor.
     * Initializes a new empty board of default size (3x3).
     */
    public Board() {
        this.size = DEFAULT_SIZE;
        this.grid = new Mark[size][size];
        initializeBoard();
    }

    /**
     * Constructs a new board of a given size.
     *
     * @param size The length/width of the square board.
     */
    public Board(int size) {
        this.size = size;
        this.grid = new Mark[size][size];
        initializeBoard();
    }

    /**
     * Initializes all board cells to BLANK.
     */
    private void initializeBoard() {
        for (int i = MIN_INDEX; i < size; i++) {
            for (int j = MIN_INDEX; j < size; j++) {
                grid[i][j] = Mark.BLANK;
            }
        }
    }

    /**
     * Returns the board size (number of rows/columns).
     *
     * @return the board size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Attempts to place a mark on the board at the specified row and column.
     * <p>
     * If the position is valid and currently blank, the mark is placed and the method returns true.
     * Otherwise, the board remains unchanged and the method returns false.
     * </p>
     *
     * @param mark the mark to place (X or O)
     * @param row  the target row index
     * @param col  the target column index
     * @return true if the mark was successfully placed; false otherwise.
     */
    public boolean putMark(Mark mark, int row, int col) {
        if (!isValidPosition(row, col)) {
            return false;
        }
        if (grid[row][col] != Mark.BLANK) {
            return false;
        }
        grid[row][col] = mark;
        return true;
    }

    /**
     * Retrieves the mark currently stored at the given position.
     * If coordinates are invalid, returns Mark.BLANK.
     *
     * @param row the row index.
     * @param col the column index.
     * @return the mark at the given cell, or Mark.BLANK if out of bounds.
     */
    public Mark getMark(int row, int col) {
        if (!isValidPosition(row, col)) {
            return Mark.BLANK;
        }
        return grid[row][col];
    }

    /**
     * Checks if a position is valid (inside board bounds).
     *
     * @param row the row index.
     * @param col the column index.
     * @return true if the position is within bounds, false otherwise.
     */
    private boolean isValidPosition(int row, int col) {
        return row >= MIN_INDEX && row < size && col >= MIN_INDEX && col < size;
    }
}
