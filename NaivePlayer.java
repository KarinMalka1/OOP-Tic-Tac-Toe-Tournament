/**
 * Naive player that fills the first available cell.
 */
public class NaivePlayer implements Player{


    /**
     * Plays a turn by placing the mark in the first empty position.
     * @param board current game board
     * @param mark  player's mark
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                boolean ifSuccess = board.putMark(mark, row, col);
                if (ifSuccess) {
                    return;
                }
            }
        }
    }

}
