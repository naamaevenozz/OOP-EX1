/**
 * A renderer that performs no visual output.
 * <p>
 * This class implements the {@link Renderer} interface but does not
 * display the board in any form. It is mainly used for automated
 * tournaments or performance testing where rendering is unnecessary.
 * </p>
 *
 * <p>This implementation still maintains compliance with the rendering API
 * to ensure modularity and compatibility across different game modes.</p>
 *
 * @author Naama Even-Oz
 * @see Renderer
 * @see Board
 */
public class VoidRenderer implements Renderer {

    /**
     * Default constructor for VoidRenderer.
     * <p>
     * Initializes a new renderer that performs no output.
     * </p>
     */
    public VoidRenderer() {}

    /**
     * Renders the board without producing any output.
     * <p>
     * This method exists to maintain a unified interface across
     * all renderers but intentionally does nothing.
     * </p>
     *
     * @param board the current game board.
     */
    @Override
    public void renderBoard(Board board) {
        // Intentionally left empty — this renderer does not display anything.
    }
}
