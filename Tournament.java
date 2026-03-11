/**
 * Runs a series of games between two players.
 */
public class Tournament {
    private static final int ARG_0 = 0;
    private static final int ARG_1 = 1;
    private static final int ARG_2 = 2;
    private static final int ARG_3 = 3;
    private static final int ARG_4 = 4;
    private static final int ARG_5 = 5;
    private static final int CHECK_EVENNESS = 2;
    private static final String START_MSG_TO_PRINT = "######### Results #########";
    private static final String PLAYER1_WIN_MSG = "Player1, %s won: %d rounds";
    private static final String PLAYER2_WIN_MSG = "Player2, %s won: %d rounds";
    private static final String TIES_MSG = "Ties: %d";


    private int rounds;
    private Renderer renderer;
    private Player player1;
    private Player player2;

    /**
     * Constructs a tournament session.
     * @param rounds   number of games to play
     * @param renderer renderer used for display
     * @param player1  first player
     * @param player2  second player
     */
    public Tournament(int rounds, Renderer renderer,
               Player player1, Player player2) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Plays all tournament rounds and prints results.
     * @param size        board side length
     * @param winStreak   consecutive marks required for victory
     * @param playerName1 name of first player
     * @param playerName2 name of second player
     */
    public void playTournament(int size, int winStreak,
                               String playerName1, String playerName2) {
        int player1Wins = 0;
        int player2Wins = 0;
        int tiesGames = 0;

        for (int i = 0; i < rounds; i++) {
            Player playerX;
            Player playerO;

            if (i % CHECK_EVENNESS == 0) {
                playerX = player1;
                playerO = player2;
            } else {
                playerX = player2;
                playerO = player1;
            }
            Game game = new Game(playerX, playerO, size, winStreak, renderer);
            Mark winner = game.run();
            if (winner == Mark.X) {
                if (i % CHECK_EVENNESS == 0) {
                    player1Wins++;
                }
                else {
                    player2Wins++;
                }
            }

            else if (winner == Mark.O) {
                if (i % CHECK_EVENNESS == 0) {
                    player2Wins++;
                }
                else {
                    player1Wins++;
                }
            }

            else {
                tiesGames++;
            }

        }
        System.out.println(START_MSG_TO_PRINT);
        System.out.println(String.format(PLAYER1_WIN_MSG, playerName1, player1Wins));
        System.out.println(String.format(PLAYER2_WIN_MSG, playerName2, player2Wins));
        System.out.println(String.format(TIES_MSG, tiesGames));
    }

    /**
     * Application entry point.
     *
     * @param args command-line arguments:
     *             0 – rounds,
     *             1 – board size,
     *             2 – win streak,
     *             3 – renderer type,
     *             4 – player1 type,
     *             5 – player2 type
     */
    public static void main(String[] args) {
        String playerName1 = args[ARG_4].toLowerCase();
        String playerName2 = args[ARG_5].toLowerCase();
        int rounds = Integer.parseInt(args[ARG_0]);
        int size = Integer.parseInt(args[ARG_1]);
        int winStreak = Integer.parseInt(args[ARG_2]);
        String renderTarget = args[ARG_3].toLowerCase();

        RendererFactory rendererFactory = new RendererFactory();
        Renderer renderer = rendererFactory.buildRenderer(renderTarget, size);

        PlayerFactory playerFactory = new PlayerFactory();
        Player player1 = playerFactory.buildPlayer(playerName1);
        Player player2 = playerFactory.buildPlayer(playerName2);

        Tournament tournament = new Tournament(rounds, renderer, player1, player2);
        tournament.playTournament(size, winStreak, playerName1, playerName2);
    }
}