/**
 * An interface representing a rendering strategy for displaying a Tic-Tac-Toe board.
 * <p>
 * Implementations of this interface are responsible for defining how the board is displayed —
 * for example, in the console, or not displayed at all (for automated simulations).
 * </p>
 *
 * <p>This interface follows the principle of <b>Abstraction</b> and <b>Polymorphism</b>,
 * allowing different renderers to be swapped without changing the game logic.</p>
 *
 * @author Naama Even-Oz
 * @see ConsoleRenderer
 * @see VoidRenderer
 * @see Board
 */
public interface Renderer {

    /**
     * Renders the current state of the given board.
     * <p>
     * This method should display the board according to the chosen implementation.
     * For example, {@link ConsoleRenderer} prints the board to the terminal,
     * while {@link VoidRenderer} ignores this action.
     * </p>
     *
     * @param board the {@link Board} object representing the current game state.
     */
    void renderBoard(Board board);
}
