import java.util.Random;

/**
 * A smart automatic player for Tic-Tac-Toe.
 * <p>
 * The SmartPlayer implements a prioritized strategy to maximize its chances of winning.
 * It first attempts to find a winning move, then tries to block the opponent's winning move,
 * and finally selects strategic or random positions when no immediate win is available.
 * </p>
 *
 * <p>This implementation is designed for the default configuration
 * (board size = 4, winStreak = 3) as specified in the assignment instructions.
 * For non-default sizes, the player gracefully falls back to random moves.</p>
 *
 * <p>Demonstrates advanced OOP concepts such as <b>Encapsulation</b>,
 * <b>Abstraction</b>, and <b>Polymorphism</b>, while maintaining clean
 * and readable design.</p>
 *
 * @author Naama Even-Oz
 * @see Player
 * @see Board
 * @see Mark
 */
public class SmartPlayer implements Player {

    /** The default board size supported by this player. */
    private static final int DEFAULT_SIZE = 4;

    /** The winning streak length supported by this player. */
    private static final int WIN_STREAK = 3;

    /** Random number generator for fallback moves. */
    private final Random random = new Random();

    /** Default constructor for SmartPlayer. */
    public SmartPlayer() {}

    /**
     * Plays a single turn according to the smart player's strategy.
     * <p>
     * Strategy order:
     * <ol>
     *     <li>Try to win immediately.</li>
     *     <li>Try to block the opponent's winning move.</li>
     *     <li>Play in the center, if available.</li>
     *     <li>Play in one of the corners.</li>
     *     <li>Otherwise, choose a random available cell.</li>
     * </ol>
     * </p>
     *
     * @param board the current {@link Board} instance.
     * @param mark  the {@link Mark} representing this player's symbol.
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int size = board.getSize();
        Mark opponent = (mark == Mark.X) ? Mark.O : Mark.X;

        // Fallback to random play if board size is not default
        if (size != DEFAULT_SIZE) {
            randomFallback(board, mark);
            return;
        }

        // 1. Try to win
        int[] move = findWinningMove(board, mark);
        if (move != null) {
            board.putMark(mark, move[0], move[1]);
            return;
        }

        // 2. Try to block opponent
        move = findWinningMove(board, opponent);
        if (move != null) {
            board.putMark(mark, move[0], move[1]);
            return;
        }

        // 3. Try center
        int mid = size / 2;
        if (board.putMark(mark, mid, mid)) {
            return;
        }

        // 4. Try corners
        int[][] corners = {
                {0, 0},
                {0, size - 1},
                {size - 1, 0},
                {size - 1, size - 1}
        };

        for (int[] corner : corners) {
            if (board.putMark(mark, corner[0], corner[1])) {
                return;
            }
        }

        // 5. Random move if all else fails
        randomFallback(board, mark);
    }

    /**
     * Searches for a move that would either win the game
     * for the given mark or block the opponent’s win.
     * <p>
     * This method checks all possible rows, columns, and diagonals
     * for a near-complete sequence of WIN_STREAK - 1 marks
     * with one remaining blank cell.
     * </p>
     *
     * @param board the current {@link Board}.
     * @param mark  the {@link Mark} to check (X or O).
     * @return an array containing the winning move coordinates [row, col], or null if none found.
     */
    private int[] findWinningMove(Board board, Mark mark) {
        int size = board.getSize();

        /* Check rows */
        for (int row = 0; row < size; row++) {
            for (int col = 0; col <= size - WIN_STREAK; col++) {
                int countMark = 0, countEmpty = 0, emptyCol = -1;
                for (int k = 0; k < WIN_STREAK; k++) {
                    Mark cell = board.getMark(row, col + k);
                    if (cell == mark) {
                        countMark++;
                    } else if (cell == Mark.BLANK) {
                        countEmpty++;
                        emptyCol = col + k;
                    }
                }
                if (countMark == WIN_STREAK - 1 && countEmpty == 1) {
                    return new int[]{row, emptyCol};
                }
            }
        }

        /* Check columns */
        for (int col = 0; col < size; col++) {
            for (int row = 0; row <= size - WIN_STREAK; row++) {
                int countMark = 0, countEmpty = 0, emptyRow = -1;
                for (int k = 0; k < WIN_STREAK; k++) {
                    Mark cell = board.getMark(row + k, col);
                    if (cell == mark) {
                        countMark++;
                    } else if (cell == Mark.BLANK) {
                        countEmpty++;
                        emptyRow = row + k;
                    }
                }
                if (countMark == WIN_STREAK - 1 && countEmpty == 1) {
                    return new int[]{emptyRow, col};
                }
            }
        }

        /* Check diagonals ↘ */
        for (int row = 0; row <= size - WIN_STREAK; row++) {
            for (int col = 0; col <= size - WIN_STREAK; col++) {
                int countMark = 0, countEmpty = 0, empty = -1;
                for (int k = 0; k < WIN_STREAK; k++) {
                    Mark cell = board.getMark(row + k, col + k);
                    if (cell == mark) {
                        countMark++;
                    } else if (cell == Mark.BLANK) {
                        countEmpty++;
                        empty = k;
                    }
                }
                if (countMark == WIN_STREAK - 1 && countEmpty == 1) {
                    return new int[]{row + empty, col + empty};
                }
            }
        }

        /* Check diagonals ↙ */
        for (int row = 0; row <= size - WIN_STREAK; row++) {
            for (int col = WIN_STREAK - 1; col < size; col++) {
                int countMark = 0, countEmpty = 0, empty = -1;
                for (int k = 0; k < WIN_STREAK; k++) {
                    Mark cell = board.getMark(row + k, col - k);
                    if (cell == mark) {
                        countMark++;
                    } else if (cell == Mark.BLANK) {
                        countEmpty++;
                        empty = k;
                    }
                }
                if (countMark == WIN_STREAK - 1 && countEmpty == 1) {
                    return new int[]{row + empty, col - empty};
                }
            }
        }

        return null; // No winning move found
    }

    /**
     * Plays a random legal move on the board.
     * <p>
     * Used as a fallback for non-default board sizes
     * or when no better strategic move is available.
     * </p>
     *
     * @param board the {@link Board} object representing the current game state.
     * @param mark  the {@link Mark} to place on the board.
     */
    private void randomFallback(Board board, Mark mark) {
        int size = board.getSize();

        // Attempt random positions until a valid move is found
        while (true) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (board.putMark(mark, row, col)) {
                return;
            }
        }
    }
}
