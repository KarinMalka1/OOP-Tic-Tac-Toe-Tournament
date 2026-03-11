/**
 * Defines a common interface for all player types.
 */
public interface Player {

    /**
     * Executes one turn of play.
     * @param board current game board
     * @param mark  player's mark
     */
    void playTurn(Board board, Mark mark);
}
