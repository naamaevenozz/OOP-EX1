import java.util.Objects;

/**
 * A simple automatic player implementation for Tic-Tac-Toe.
 * <p>
 * The NaivePlayer iterates through the board from the top-left cell
 * to the bottom-right cell and plays in the first empty position it finds.
 * </p>
 *
 * <p>This class demonstrates a straightforward deterministic strategy,
 * designed mainly for testing and benchmarking smarter players.
 * </p>
 *
 * @author Naama Even-Oz
 * @see Player
 * @see Board
 * @see Mark
 */
public class NaivePlayer implements Player {

    /**
     * Default constructor.
     * Initializes a NaivePlayer instance.
     */
    public NaivePlayer() {}

    /**
     * Plays a single turn for this player.
     * <p>
     * The player scans the board from the first row and column, moving sequentially
     * until it finds the first empty cell (Mark.BLANK), where it places its mark.
     * </p>
     *
     * @param board the {@link Board} representing the current game state.
     * @param mark  the {@link Mark} representing the player's symbol (X or O).
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int boardSize = board.getSize();

        // Iterate through all board positions in row-major order
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (board.getMark(row, col) == Mark.BLANK) {
                    board.putMark(mark, row, col);
                    return; // Turn complete
                }
            }
        }
        // If the board is full, nothing happens (no available moves)
    }
}
