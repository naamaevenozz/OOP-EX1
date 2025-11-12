/**
 * Represents the possible marks on the Tic-Tac-Toe board.
 * <p>
 * Each cell on the board can contain one of the following values:
 * <ul>
 *     <li><b>BLANK</b> – the cell is empty.</li>
 *     <li><b>X</b> – player X's mark.</li>
 *     <li><b>O</b> – player O's mark.</li>
 * </ul>
 * </p>
 *
 * <p>This enum is used throughout the program to manage board state and player turns.</p>
 *
 * @author Naama Even-Oz
 */
public enum Mark {

    /** Represents an empty cell on the board. */
    BLANK,

    /** Represents the X player's mark. */
    X,

    /** Represents the O player's mark. */
    O;

    /**
     * Returns the string representation of the mark.
     * <p>
     * Used mainly by renderers to display the mark visually.
     * If the mark is BLANK, returns null as per the exercise specification.
     * </p>
     *
     * @return "X" if this mark is X, "O" if this mark is O, or null if BLANK.
     */
    @Override
    public String toString() {
        switch (this) {
            case X:
                return "X";
            case O:
                return "O";
            default:
                return null;
        }
    }
}
