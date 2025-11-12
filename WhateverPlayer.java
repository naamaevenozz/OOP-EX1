import java.util.Random;

/**
 * A simple automatic player that plays random moves on the board.
 * <p>
 * The WhateverPlayer selects a random empty cell each turn using
 * a {@link java.util.Random} generator. It serves as a baseline
 * for comparing smarter strategies such as {@link SmartPlayer}.
 * </p>
 *
 * <p>This player demonstrates the use of randomness and encapsulation:
 * its internal decision process is hidden from external classes.</p>
 *
 * @author Naama Even-Oz
 * @see Player
 * @see SmartPlayer
 * @see Board
 */
public class WhateverPlayer implements Player {

    /** Random number generator used for selecting random moves. */
    private final Random random = new Random();

    /** Default constructor for WhateverPlayer. */
    public WhateverPlayer() {}

    /**
     * Plays one turn by placing a mark in a random empty cell.
     * <p>
     * The method repeatedly generates random coordinates until
     * an empty position is found. This ensures that each move
     * is valid without requiring external validation.
     * </p>
     *
     * @param board the {@link Board} representing the current game state.
     * @param mark  the {@link Mark} representing the player's symbol (X or O).
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int boardSize = board.getSize();

        // Continue until a valid random move is found
        while (true) {
            int randomRow = random.nextInt(boardSize);
            int randomCol = random.nextInt(boardSize);

            // Try placing the mark - if successful, end the turn
            if (board.putMark(mark, randomRow, randomCol)) {
                return;
            }
        }
    }
}
