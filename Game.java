/**
 * Represents a Tic-Tac-Toe game between two {@link Player} objects.
 * <p>
 * The {@code Game} class manages the game logic, alternating turns between players,
 * checking for a win condition, and determining when the board is full.
 * </p>
 *
 * <p>
 * This implementation demonstrates OOP principles:
 * <ul>
 *     <li><b>Encapsulation</b> – internal game logic and board handling are hidden from the user.</li>
 *     <li><b>Abstraction</b> – exposes only a simple interface to run a full game.</li>
 *     <li><b>Composition</b> – combines {@link Player}, {@link Renderer}, and {@link Board} components.</li>
 * </ul>
 * </p>
 *
 * @author Naama Even-Oz
 * @see Player
 * @see Renderer
 * @see Board
 * @see Mark
 */
public class Game {

    /** The player using mark X. */
    private Player playerX;

    /** The player using mark O. */
    private Player playerO;

    /** The renderer responsible for displaying the game board. */
    private Renderer renderer;

    /** The board on which the game is played. */
    private Board board;

    /** The number of consecutive marks required to win. */
    private int winStreak;

    /**
     * Constructs a new {@code Game} instance using default board settings.
     * <p>
     * By default, the board size is 4 and the win streak is 3.
     * </p>
     *
     * @param playerX   the first player (X).
     * @param playerO   the second player (O).
     * @param renderer  the {@link Renderer} to visualize the game.
     */
    public Game(Player playerX, Player playerO, Renderer renderer) {
        this(playerX, playerO, 4, 3, renderer);
    }

    /**
     * Constructs a new {@code Game} instance with custom parameters.
     *
     * @param playerX    the first player (X).
     * @param playerO    the second player (O).
     * @param size       the size of the game board.
     * @param winStreak  the number of marks needed in a row to win.
     * @param renderer   the {@link Renderer} to visualize the game.
     */
    public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.winStreak = winStreak;
        this.renderer = renderer;
        this.board = new Board(size);
    }

    /**
     * Returns the number of consecutive marks required to win.
     *
     * @return the win streak length.
     */
    public int getWinStreak() {
        return winStreak;
    }

    /**
     * Returns the size of the current game board.
     *
     * @return the board size.
     */
    int getBoardSize() {
        return board.getSize();
    }

    /**
     * Runs the game until one player wins or the board is full.
     * <p>
     * Alternates turns between {@link Player X} and {@link Player O},
     * renders the board after each move, and returns the winner mark.
     * </p>
     *
     * @return {@link Mark#X} or {@link Mark#O} if a player wins,
     * or {@link Mark#BLANK} if the game ends in a tie.
     */
    public Mark run() {
        Mark current = Mark.X;

        while (true) {
            if (current == Mark.X) {
                playerX.playTurn(board, Mark.X);
            } else {
                playerO.playTurn(board, Mark.O);
            }

            renderer.renderBoard(board);

            if (checkWin(current)) {
                return current;
            }

            if (isBoardFull()) {
                return Mark.BLANK;
            }

            // Switch turns
            current = (current == Mark.X ? Mark.O : Mark.X);
        }
    }

    /**
     * Checks whether the given {@link Mark} has achieved a win condition.
     *
     * @param mark the mark to check (X or O).
     * @return {@code true} if the mark has a winning sequence, otherwise {@code false}.
     */
    public boolean checkWin(Mark mark) {
        int size = board.getSize();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.getMark(row, col) == mark) {
                    if (checkDirection(mark, row, col, 1, 0) || // vertical
                            checkDirection(mark, row, col, 0, 1) || // horizontal
                            checkDirection(mark, row, col, 1, -1) || // diagonal ↙
                            checkDirection(mark, row, col, 1, 1)) {  // diagonal ↘
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks a specific direction on the board to see if there is a winning sequence.
     *
     * @param mark the mark to check.
     * @param row  the starting row.
     * @param col  the starting column.
     * @param dr   the row direction increment.
     * @param dc   the column direction increment.
     * @return {@code true} if a sequence of {@code winStreak} identical marks exists.
     */
    public boolean checkDirection(Mark mark, int row, int col, int dr, int dc) {
        int count = 0;
        int size = board.getSize();

        for (int i = 0; i < winStreak; i++) {
            int r = row + i * dr;
            int c = col + i * dc;

            if (r < 0 || r >= size || c < 0 || c >= size) {
                return false;
            }
            if (board.getMark(r, c) != mark) {
                return false;
            }

            count++;
        }

        return (count == winStreak);
    }

    /**
     * Checks whether the board is full (no empty cells remain).
     *
     * @return {@code true} if the board is full, otherwise {@code false}.
     */
    private boolean isBoardFull() {
        int size = board.getSize();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board.getMark(r, c) == Mark.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }
}
