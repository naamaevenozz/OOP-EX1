/**
 * The {@code Tournament} class represents a sequence of games played between two {@link Player} objects.
 * <p>
 * The tournament alternates the starting player in each round to ensure fairness.
 * It runs a specified number of rounds, tracks the number of wins for each player and the number of ties,
 * and prints the final results in a strict format.
 * </p>
 *
 * <p>This class demonstrates the OOP principles of <b>Encapsulation</b> and <b>Composition</b>:
 * it encapsulates the logic for running multiple {@link Game} instances while relying on
 * composition of {@link Player}, {@link Renderer}, and {@link Game} components.</p>
 *
 * @author Naama Even-Oz
 * @see Player
 * @see Game
 * @see Renderer
 */
public class Tournament {
    /** The number of rounds in the tournament. */
    private int rounds;

    /** The renderer used to display each game's board. */
    private Renderer renderer;

    /** The first player. */
    private Player player1;

    /** The second player. */
    private Player player2;

    /**
     * Constructs a new {@code Tournament} with the specified number of rounds and participants.
     *
     * @param rounds   the number of games to play in the tournament.
     * @param renderer the {@link Renderer} to use during games.
     * @param player1  the first {@link Player}.
     * @param player2  the second {@link Player}.
     */
    public Tournament(int rounds, Renderer renderer, Player player1, Player player2) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Runs the tournament between two players.
     * <p>
     * Each round alternates which player starts first:
     * even-numbered rounds → Player 1 starts as X,
     * odd-numbered rounds → Player 2 starts as X.
     * </p>
     *
     * <p>
     * After all games finish, prints the results exactly in the required format:
     * <pre>
     * ######### Results #########
     * Player 1, [player_type] won: _ rounds
     * Player 2, [player_type] won: _ rounds
     * Ties: _
     * </pre>
     * </p>
     *
     * @param size        the board size for each game.
     * @param winStreak   the number of marks needed to win.
     * @param playerName1 the name of the first player.
     * @param playerName2 the name of the second player.
     */
    public void playTournament(int size, int winStreak, String playerName1, String playerName2) {
        int player1Wins = 0;
        int player2Wins = 0;
        int ties = 0;

        for (int i = 0; i < rounds; i++) {
            boolean isEvenRound = (i % 2 == 0);
            Game game;

            if (isEvenRound) {
                // Player 1 starts as X
                game = new Game(player1, player2, size, winStreak, renderer);
            } else {
                // Player 2 starts as X
                game = new Game(player2, player1, size, winStreak, renderer);
            }

            Mark winner = game.run();

            if (winner == Mark.X) {
                if (isEvenRound) {
                    player1Wins++;
                } else {
                    player2Wins++;
                }
            } else if (winner == Mark.O) {
                if (isEvenRound) {
                    player2Wins++;
                } else {
                    player1Wins++;
                }
            } else {
                ties++;
            }
        }

        // Print results exactly as required (no extra newlines or spaces)
        System.out.println("######### Results #########");
        System.out.println("Player 1, " + playerName1 + " won: " + player1Wins + " rounds");
        System.out.println("Player 2, " + playerName2 + " won: " + player2Wins + " rounds");
        System.out.print("Ties: " + ties);
    }

    /**
     * The main method that starts the tournament.
     * <p>
     * The players' types and renderer are read from command-line arguments.
     * Example of expected arguments:
     * <pre>
     * java Tournament [rounds] [size] [winStreak] [rendererType] [playerType1] [playerType2]
     * </pre>
     * </p>
     *
     * @param args command-line arguments used to configure the tournament.
     */
    public static void main(String[] args) {
        if (args.length < 6) {
            System.out.println("Usage: java Tournament <rounds> <size> <winStreak> <rendererType> <player1Type> <player2Type>");
            return;
        }

        int rounds = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        int winStreak = Integer.parseInt(args[2]);
        String rendererType = args[3];
        String playerType1 = args[4];
        String playerType2 = args[5];

        RendererFactory rendererFactory = new RendererFactory();
        PlayerFactory playerFactory = new PlayerFactory();

        Renderer renderer = rendererFactory.buildRenderer(rendererType, size);
        Player player1 = playerFactory.buildPlayer(playerType1);
        Player player2 = playerFactory.buildPlayer(playerType2);

        Tournament tournament = new Tournament(rounds, renderer, player1, player2);
        tournament.playTournament(size, winStreak, playerType1, playerType2);
    }
}
