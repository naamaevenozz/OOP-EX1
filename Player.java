/**
 * Represents a player in the Tic-Tac-Toe game.
 * <p>
 * This interface defines the basic API for all player types,
 * including human players and automated ones (e.g., NaivePlayer,
 * WhateverPlayer, SmartPlayer).
 * </p>
 *
 * <p>
 * It demonstrates the Object-Oriented principles of
 * <b>Abstraction</b> and <b>Polymorphism</b>:
 * all players share the same interface, allowing the {@link Game}
 * class to interact with any implementation uniformly.
 * </p>
 *
 * @author Naama Even-Oz
 * @see Game
 * @see Board
 * @see Mark
 */
public interface Player {

    /**
     * Executes the player's turn.
     * <p>
     * This method should define how the player decides its next move,
     * whether through user input (for a human player) or an algorithmic
     * strategy (for an automatic player).
     * </p>
     *
     * @param board the {@link Board} representing the current game state.
     * @param mark  the {@link Mark} representing the player's symbol (X or O).
     */
    void playTurn(Board board, Mark mark);
}
