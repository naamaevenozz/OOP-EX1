/**
 * A factory class responsible for creating {@link Player} objects based on a given type string.
 * <p>
 * Implements the <b>Factory Design Pattern</b>, encapsulating player creation logic
 * and supporting various player strategies dynamically.
 * </p>
 *
 * @author Naama Even-Oz
 * @see Player
 * @see HumanPlayer
 * @see WhateverPlayer
 * @see NaivePlayer
 * @see SmartPlayer
 */
public class PlayerFactory {

    /** Identifier for human player type. */
    private static final String HUMAN_TYPE = "human";

    /** Identifier for random player type. */
    private static final String WHATEVER_TYPE = "whatever";

    /** Identifier for naive player type. */
    private static final String NAIVE_TYPE = "naive";

    /** Identifier for smart player type. */
    private static final String SMART_TYPE = "smart";

    /** Default constructor for PlayerFactory. */
    public PlayerFactory() {}

    /**
     * Builds and returns a {@link Player} instance according to the given type string.
     * Returns {@code null} if the type is invalid.
     *
     * @param type the player type identifier (human, whatever, naive, smart).
     * @return a new {@link Player} instance, or {@code null} if invalid.
     */
    public Player buildPlayer(String type) {
        if (type == null) {
            return null;
        }

        // Normalize for consistent matching
        String normalizedType = type.trim().toLowerCase();

        switch (normalizedType) {
            case HUMAN_TYPE:
                return new HumanPlayer();
            case WHATEVER_TYPE:
                return new WhateverPlayer();
            case NAIVE_TYPE:
                return new NaivePlayer();
            case SMART_TYPE:
                return new SmartPlayer();
            default:
                return null;
        }
    }
}
